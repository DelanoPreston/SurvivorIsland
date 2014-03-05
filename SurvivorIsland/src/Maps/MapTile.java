package Maps;

import javax.swing.JComponent;

public class MapTile extends JComponent {

	private static final long serialVersionUID = -2832950817061632470L;
	TileType type;
	Covering covering;
	double[] tileLocation;

	public MapTile() {

	}

	public MapTile(char inType, double[] inLocation) {
		setType(inType);
		if (inType == 'j')
			covering = Covering.JUNGLE;
		else if (inType == 'f')
			covering = Covering.FOREST;
		else
			covering = Covering.NONE;
		tileLocation = inLocation;
	}

	public TileType getType() {
		return type;
	}

	public void setType(char inchar) {
		switch (inchar) {
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
