package Entity;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import Main.ContentBank;
import Main.Location;

public class StructureEntity extends Entity {
	private static final long serialVersionUID = 641474344878015532L;
//	int imageKey;
	int timer;
	
	public StructureEntity(String inName, Location inLocation, double inWeight, boolean inSolid) {
		super(inName, inLocation, inWeight, inSolid);
		imageKey = 6;
	}
	
	public void updateKey(){
		
	}
	
	public void paintComponent(Graphics2D g2D){
		AffineTransform at = g2D.getTransform();
		at.translate(-8, -8);
		g2D.setTransform(at);
		g2D.drawImage(ContentBank.woodenWalls[imageKey], location.getMapX(), location.getMapY(), null);
		at.translate(8, 8);
		g2D.setTransform(at);
	}
}
