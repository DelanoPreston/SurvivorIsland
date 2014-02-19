package Maps;

public class MapTile {
	TileType type;
	String imagePath = "";
	
	public MapTile(){
		
	}
	
	public MapTile(char inType){
		type = getType(inType);
	}
	
	public TileType getType(char inchar){
		switch(inchar){
		case 's':
			imagePath = "";
			return TileType.SEA;
		case 'b':
			imagePath = "";
			return TileType.BEACH;
		case 'f':
			imagePath = "";
			return TileType.FOREST;
		case 'j':
			imagePath = "";
			return TileType.JUNGLE;
		default:
			imagePath = "";
			return TileType.UNKNOWN;
		}
	}
}
