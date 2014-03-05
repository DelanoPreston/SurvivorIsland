package Maps;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.JComponent;

import Entity.Entity;
import Main.ContentBank;

@SuppressWarnings("serial")
public class Map extends JComponent implements TileBasedMap {
	MapTile[][] map;
	Random random;
	public int[][] intMap;
	int tileSize;
	// /** The terrain settings for each tile in the map */
	// private int[][] terrain = new int[map.length][map[0].length];
	// /** The unit in each tile of the map */
	// private int[][] units = new int[map.length][map[0].length];
	/** Indicator if a given tile has been visited during the search */
	private boolean[][] visited;// = new boolean[map.length][map[0].length];
	
	public Map(char[][] mapKey) {
		tileSize = 16;
		map = createMap(mapKey);
		visited = new boolean[map.length * 4][map[0].length * 4];
		intMap = new int[map.length * 4][map[0].length * 4];
		for (int y = 0; y < mapKey.length; y++) {
			for (int x = 0; x < mapKey[0].length; x++) {
				if (mapKey[x][y] == 's')
					intMap[x][y] = 0;
				else
					intMap[x][y] = 1;
			}
		}
		for (int y = 0; y < mapKey.length; y++) {
			for (int x = 0; x < mapKey[0].length; x++) {
				System.out.print(intMap[x][y]);
			}
			System.out.print("\r\n");
		}
	}

	public void update() {

	}

	public void paintComponent(Graphics2D g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				// map[i][j].
				// drawTile(g, map[i][j].getType(), i * 64, j * 64);
				drawTile(g, map[i][j].getType(), i * tileSize, j * tileSize);
				int[] loc = getLocationAtTile(i, j);
				g.drawRect(loc[0] - 1, loc[1] - 1, 2, 2);
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

	private MapTile[][] createMap(char[][] mapKey) {
		MapTile[][] temp = new MapTile[mapKey.length][mapKey[0].length];
		double[] tempLocation = new double[2];
		for (int y = 0; y < mapKey.length; y++) {
			for (int x = 0; x < mapKey[y].length; x++) {
				tempLocation[0] = (x * tileSize) + 8;
				tempLocation[1] = (y * tileSize) + 8;
				temp[y][x] = new MapTile(mapKey[y][x], tempLocation);
			}
		}
		return temp;
	}

	public int[] getLocationAtTile(int x, int y) {
		int[] temp = new int[2];

		temp[0] = (x * tileSize) + (tileSize / 2);
		temp[1] = (y * tileSize) + (tileSize / 2);

		return temp;
	}

	public int[] getTileAtLocation(int x, int y) {
		int[] temp = new int[2];

		temp[0] = (x - 8) / tileSize;
		temp[1] = (y - 8) / tileSize;

		return temp;
	}

	@Override
	public int getWidthInTiles() {
		// TODO Auto-generated method stub
		return intMap[0].length;
	}

	@Override
	public int getHeightInTiles() {
		// TODO Auto-generated method stub
		return intMap.length;
	}

	@Override
	public void pathFinderVisited(int x, int y) {
		// TODO Auto-generated method stub
		visited[x][y] = true;
	}

	@Override
	public boolean blocked(Entity entity, int x, int y) {

		if (intMap[x][y] == 1)
			return false;
		return true;

		// // TODO Auto-generated method stub
		// switch (entity.type) {
		// case LAND:
		// if (map[x][y].equals('s'))
		// return true;
		// else
		// return false;
		// case SEA:
		// if (map[x][y].equals('s'))
		// return false;
		// else
		// return true;
		// case AIR:
		// return false;
		// }
		// return false;
	}

	@Override
	public float getCost(Entity entity, int sx, int sy, int tx, int ty) {
		if (Math.abs(sx - tx) == 1 && Math.abs(sy - ty) == 1)
			return (float) Math.sqrt(1 + 1);
		return 1;
	}
}
