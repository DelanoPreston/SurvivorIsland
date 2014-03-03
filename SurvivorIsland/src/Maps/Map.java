package Maps;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.JComponent;

import Entity.Entity;
import Main.ContentBank;

@SuppressWarnings("serial")
public class Map extends JComponent implements TileBasedMap {
	static MapTile[][] map;
	Random random;
	int[][] intMap;
	// /** The terrain settings for each tile in the map */
	// private int[][] terrain = new int[map.length][map[0].length];
	// /** The unit in each tile of the map */
	// private int[][] units = new int[map.length][map[0].length];
	/** Indicator if a given tile has been visited during the search */
	private boolean[][] visited;// = new boolean[map.length][map[0].length];

	public Map(char[][] mapKey) {
		map = createMap(mapKey);
		visited = new boolean[map.length * 4][map[0].length * 4];
		intMap = new int[map.length * 4][map[0].length * 4];
		for (int y = 0; y < mapKey.length; y++) {
			for (int x = 0; x < mapKey[0].length; x++) {

				for (int iy = y * 4; iy < 4 * (y + 1); iy++) {
					for (int ix = x * 4; ix < 4 * (x + 1); ix++) {

						if (mapKey[x][y] == 's')
							intMap[iy][ix] = 0;
						else
							intMap[iy][ix] = 1;

					}
				}
			}
		}

		for (int y = 0; y < intMap.length; y++) {
			for (int x = 0; x < intMap[0].length; x++) {
				System.out.print(intMap[x][y]);
			}
			System.out.print("\r\n");
		}
	}

	public void update() {

	}

	@Override
	public void paintComponent(Graphics g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				// map[i][j].
				drawTile(g, map[i][j].getType(), i * 64, j * 64);
			}
		}
	}

	private void drawTile(Graphics g, TileType tileType, int xPos, int yPos) {
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

		for (int i = 0; i < mapKey.length; i++) {
			for (int j = 0; j < mapKey[i].length; j++) {
				temp[i][j] = new MapTile(mapKey[i][j]);
			}
		}
		return temp;
	}

	@Override
	public int getWidthInTiles() {
		// TODO Auto-generated method stub
		return map[0].length;
	}

	@Override
	public int getHeightInTiles() {
		// TODO Auto-generated method stub
		return map.length;
	}

	@Override
	public void pathFinderVisited(int x, int y) {
		// TODO Auto-generated method stub
		visited[x][y] = true;
	}

	@Override
	public boolean blocked(Entity entity, int x, int y) {
		// TODO Auto-generated method stub
		switch (entity.type) {
		case LAND:
			if (map[x][y].equals('s'))
				return true;
			else
				return false;
		case SEA:
			if (map[x][y].equals('s'))
				return false;
			else
				return true;
		case AIR:
			return false;
		}
		return false;
	}

	@Override
	public float getCost(Entity entity, int sx, int sy, int tx, int ty) {
		if (Math.abs(sx - tx) == 1 && Math.abs(sy - ty) == 1)
			return (float) Math.sqrt(1 + 1);
		return 1;
	}
}
