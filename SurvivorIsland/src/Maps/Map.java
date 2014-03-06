package Maps;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JComponent;

import Entity.Entity;
import Main.ContentBank;
import Main.Location;

@SuppressWarnings("serial")
public class Map extends JComponent implements TileBasedMap {
//	MapTile[][] map;
	TileBoard map;
	int tileSize;
	private boolean[][] visited;
	
	public Map(char[][] mapKey) {
		tileSize = 16;
		map = createMap(mapKey);
		visited = new boolean[map.getHeight()][map.getWidth()];
	}

	public void update() {

	}

	public void paintComponent(Graphics2D g) {
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				// map[i][j].
				// drawTile(g, map[i][j].getType(), i * 64, j * 64);
				drawTile(g, map.getTile(x, y).getType(), x * tileSize, y * tileSize);
				int[] loc = getLocationAtTile(x, y);
				g.drawRect(loc[0] - 8, loc[1] - 8, 16, 16);
			}
		}
	}

	private void drawTile(Graphics2D g, TileType tileType, int xPos, int yPos) {
		Image temp = null;
		switch (tileType) {
		case SEA:
			temp = ContentBank.sea;
			break;
		case BEACH:
			temp = ContentBank.beach;
			break;
		case FOREST:
			temp = ContentBank.forest;
			break;
		case JUNGLE:
			temp = ContentBank.jungle;
			break;
		default:
			temp = ContentBank.sea;
			break;
		}
		g.drawImage(temp, xPos, yPos, null);
	}

	private TileBoard createMap(char[][] mapKey) {
		MapTile[][] temp = new MapTile[mapKey.length][mapKey[0].length];
		double[] tempLocation = new double[2];
		double seaCost = 1000;
		double landCost = 15;
		for (int y = 0; y < mapKey.length; y++) {
			for (int x = 0; x < mapKey[0].length; x++) {
				tempLocation[0] = (x * tileSize) + 8;
				tempLocation[1] = (y * tileSize) + 8;
				if(mapKey[y][x] == 's')
					seaCost = 1;
				else
					seaCost = 1000;
				switch(mapKey[y][x]){
				case 's':
					landCost = 15.0;
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
		
		for(int y = 0; y < temp.length; y++){
			for(int x = 0; x < temp[0].length; x++){
				System.out.print(mapKey[y][x]);
			}
			System.out.println();
		}
		TileBoard tempOut = new TileBoard(temp);
		return tempOut;
	}

	public int[] getLocationAtTile(int x, int y) {
		int[] temp = new int[2];

		temp[0] = (x * tileSize) + (tileSize / 2);
		temp[1] = (y * tileSize) + (tileSize / 2);

		return temp;
	}
	
	public int[] getTileAtLocation(int[] inLoc){
		return getTileAtLocation(inLoc[0], inLoc[1]);
	}
	
	public int[] getTileAtLocation(Location inLoc){
		return getTileAtLocation(inLoc.getMapX(), inLoc.getMapY());
	}
	
	public int[] getTileAtLocation(double[] inLoc){
		return getTileAtLocation((int)inLoc[0], (int)inLoc[1]);
	}
	
	public int[] getTileAtLocation(int x, int y) {
		int[] temp = new int[2];
		
		if(x % tileSize > 8)
			temp[0] = x - 8;
		if(y % tileSize > 8)
			temp[1] = y - 8;
		
		temp[0] = x / tileSize;
		temp[1] = y / tileSize;

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
		return (float)tempCost;
	}
}
