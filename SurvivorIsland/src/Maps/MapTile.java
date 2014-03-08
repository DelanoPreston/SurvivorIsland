package Maps;

import java.awt.Graphics2D;

import javax.swing.JComponent;

import Entity.Entity;
import Entity.StructureEntity;

public class MapTile extends JComponent {

	private static final long serialVersionUID = -2832950817061632470L;
	TileType type;
	Covering covering;
	double landTileCost;
	double seaTileCost;
	StructureEntity entity;
	int imageKey;
	double[] tileLocation;

	public void setEntity(StructureEntity inEntity){
		System.out.println("MapTile: EntityLocation: " + inEntity.getX() + "," + inEntity.getY());
		entity = inEntity;
	}
	
	public Entity getEntity(){
		return entity;
	}
	
	public MapTile(char inType, double[] inLocation, double inLandCost, double inSeaCost) {
		setType(inType);
			
		tileLocation = inLocation;
		landTileCost = inLandCost;
		seaTileCost = inSeaCost;
	}
	
	public void paintComponent(Graphics2D g2D){
		
	}
	
	public TileType getType() {
		return type;
	}

	public void setType(char inchar) {
		switch (inchar) {
		case 's':
			imageKey = 0;
			type = TileType.SEA;
			covering = Covering.NONE;
			break;
		case 'b':
			imageKey = 1;
			type = TileType.BEACH;
			covering = Covering.NONE;
			break;
		case 'f':
			imageKey = 2;
			type = TileType.FOREST;
			covering = Covering.FOREST;
			break;
		case 'j':
			imageKey = 3;
			type = TileType.JUNGLE;
			covering = Covering.JUNGLE;
			break;
		default:
			imageKey = 0;
			type = TileType.UNKNOWN;
			covering = Covering.NONE;
			break;
		}
	}
}
