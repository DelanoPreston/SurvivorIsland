package Maps;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JComponent;

import Entity.Entity;
import Entity.StructureEntity;
import Main.ContentBank;
import Main.Location;

public class Map extends JComponent implements TileBasedMap {
	private static final long serialVersionUID = -1719918279039504911L;
	char[][] charMap;
	TileBoard map;
	private boolean[][] visited;

	public void placeWall(StructureEntity structure) {
		map.tiles[structure.getTileX()][structure.getTileY()].entity = structure;
	}

	public Map(char[][] mapKey) {
//		map = createMap(mapKey);
		map = createMap(createLargeTestMap());
		visited = new boolean[map.getHeight()][map.getWidth()];
	}

	public void update() {

	}

	public void paintComponent(Graphics2D g2D) {
		int imageKey = 0;
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				switch(charMap[y][x]){
				case 's':
					imageKey = 0;
					break;
				case 'b':
					imageKey = 1;
					break;
				case 'f':
					imageKey = 2;
					break;
				case 'j':
					imageKey = 3;
					break;
				}
				
				g2D.drawImage(ContentBank.landTiles[imageKey], x * 16, y * 16, null);
				
//				map.getTile(x, y).paintComponent(g2D);
			}
		}
	}

	// private void drawTile(Graphics2D g, TileType tileType, int xPos, int yPos) {
	// Image temp = null;
	// switch (tileType) {
	// case SEA:
	// temp = ContentBank.sea;
	// break;
	// case BEACH:
	// temp = ContentBank.beach;
	// break;
	// case FOREST:
	// temp = ContentBank.forest;
	// break;
	// case JUNGLE:
	// temp = ContentBank.jungle;
	// break;
	// default:
	// temp = ContentBank.sea;
	// break;
	// }
	// g.drawImage(temp, xPos, yPos, null);
	// }

	private char[][] createLargeTestMap() {
		char[][] temp = new char[256][256];

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp.length; j++) {
				if (i > 64 && i < 196 && j > 64 && j < 196)
					temp[i][j] = 'b';
				else
					temp[i][j] = 's';
			}
		}
		charMap = temp;
		return temp;
	}

	private TileBoard createMap(char[][] mapKey) {
		charMap = mapKey;
		MapTile[][] temp = new MapTile[mapKey.length][mapKey[0].length];

		double seaCost = 1000;
		double landCost = 15;
		for (int y = 0; y < mapKey.length; y++) {
			for (int x = 0; x < mapKey[0].length; x++) {
				double[] tempLocation = new double[2];
				tempLocation[0] = (x * ContentBank.tileSize);
				tempLocation[1] = (y * ContentBank.tileSize);
				if (mapKey[y][x] == 's')
					seaCost = 1;
				else
					seaCost = 1000;
				switch (mapKey[y][x]) {
				case 's':
					landCost = 35.0;
					break;
				case 'j':
					landCost = 2.0;
					break;
				case 'f':
					landCost = 1.0;
					break;
				case 'b':
					landCost = 1.2;
					break;
				}

				temp[y][x] = new MapTile(mapKey[y][x], tempLocation, landCost, seaCost);
			}
		}

		for (int y = 0; y < temp.length; y++) {
			for (int x = 0; x < temp[0].length; x++) {
				System.out.print(mapKey[y][x]);
			}
			System.out.println();
		}
		TileBoard tempOut = new TileBoard(temp);
		return tempOut;
	}

	public int[] getLocationAtTile(int x, int y) {
		int[] temp = new int[2];

		temp[0] = (x * ContentBank.tileSize) + (ContentBank.tileSize / 2);
		temp[1] = (y * ContentBank.tileSize) + (ContentBank.tileSize / 2);

		return temp;
	}

	public int[] getTileAtLocation(int[] inLoc) {
		return getTileAtLocation(inLoc[0], inLoc[1]);
	}

	public int[] getTileAtLocation(Location inLoc) {
		return getTileAtLocation(inLoc.getMapX(), inLoc.getMapY());
	}

	public int[] getTileAtLocation(double[] inLoc) {
		return getTileAtLocation((int) inLoc[0], (int) inLoc[1]);
	}

	public int[] getTileAtLocation(int x, int y) {
		int[] temp = new int[2];

		if (x % ContentBank.tileSize > 8)
			temp[0] = x - 8;
		if (y % ContentBank.tileSize > 8)
			temp[1] = y - 8;

		temp[0] = x / ContentBank.tileSize;
		temp[1] = y / ContentBank.tileSize;

		return temp;
	}

	@Override
	public int getWidthInTiles() {
		// TODO Auto-generated method stub
		int temp = map.getWidth();
		return temp;
	}

	@Override
	public int getHeightInTiles() {
		// TODO Auto-generated method stub
		return map.getHeight();
	}

	@Override
	public void pathFinderVisited(int x, int y) {
		// TODO Auto-generated method stub
		visited[x][y] = true;
	}

	@Override
	public boolean blocked(Entity entity, int x, int y) {
		if (map.getTile(x, y).entity != null && map.getTile(x, y).entity.solid)
			return true;
		return false;
	}

	@Override
	public float getCost(Entity entity, int sx, int sy, int tx, int ty) {
		double tempCost = Math.max(map.getTile(tx, ty).landTileCost, map.getTile(sx, sy).landTileCost);
		if (Math.abs(sx - tx) == 1 && Math.abs(sy - ty) == 1)
			return (float) Math.sqrt(tempCost + tempCost);
		return (float) tempCost;
	}
}
