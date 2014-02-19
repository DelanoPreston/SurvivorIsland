package Maps;

public class MapTile {
	TileType type;
	Covering covering;
	
	public MapTile(){
		
	}
	
	public MapTile(char inType){
		setType(inType);
		if(inType == 'j')
			covering = Covering.JUNGLE;
		else if(inType == 'f')
			covering = Covering.FOREST;
		else
			covering = Covering.NONE;
		
		
	}
	
	public TileType getType(){
		return type;
	}
	
	public void setType(char inchar){
		switch(inchar){
		case 's':
			type = TileType.SEA;
			break;
		case 'b':
			type = TileType.BEACH;
			break;
		case 'f':
			type = TileType.FOREST;
			break;
		case 'j':
			type = TileType.JUNGLE;
			break;
		default:
			type = TileType.UNKNOWN;
			break;
		}
	}
}
