package NoGoodAnymore;

import java.awt.Graphics2D;
import java.awt.Image;

import Main.Location;

public class StructureEntity extends PlaceableEntity {
	private static final long serialVersionUID = 641474344878015532L;
	int timer;
	
	public StructureEntity(String inName, Location inLocation, double inWeight, boolean inSolid) {
		super(inName, inLocation, inWeight, inSolid);
		imageKey = 0;
	}
	
	public void updateKey(){
		
	}
	
	public void paintComponent(Graphics2D g2D){
		
	}
	
	@Override
	public Image getImage(){
		return null;
	}
}
