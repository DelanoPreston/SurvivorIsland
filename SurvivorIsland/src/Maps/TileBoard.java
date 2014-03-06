package Maps;

public class TileBoard {
	MapTile[][] tiles;

	public int getWidth() {
		return tiles[0].length;
	}

	public int getHeight() {
		return tiles.length;
	}

	public MapTile getTile(int x, int y) {
		return tiles[y][x];
	}

	public TileBoard(MapTile[][] inMap) {
		tiles = inMap;
	}
}
