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
		try{
			return tiles[y][x];	
		}
		catch(Exception e){
			int i = 0;
			
			if(i == 0){
				
			}
		}
		return null;
	}

	public TileBoard(MapTile[][] inMap) {
		tiles = inMap;
	}
}
