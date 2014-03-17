package NoGoodAnymore;

import java.awt.Graphics2D;
import java.awt.MouseInfo;

import Main.Location;

public class ConstructionEntity extends Entityz {
	private static final long serialVersionUID = 6870326360861517120L;
	private PlaceableEntity structure;
	private double progress;
	private boolean placed;
	//needed materials
	
	public double getProgress(){
		return progress;
	}
	
	public void addProgress(double work){
		progress += work;
	}
	
	public ConstructionEntity(String inName, PlaceableEntity inStructure, Location inLocation, double inWeight, boolean inSolid) {
		super(inName, inLocation, inWeight, inSolid);
		structure = inStructure;
		progress = 0;
		placed = false;
	}

	@Override
	public void update() {
		if(!placed){
			location = new Location(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y);
		}
		if (progress >= 100)
			buildStructure();
	}
	
	@Override
	public void paintComponent(Graphics2D g2D){
		if(placed){
			g2D.drawImage(structure.getImage(), location.getTileX(), location.getTileY(), null);
		}else{
			g2D.drawImage(structure.getImage(), location.getTileX(), location.getTileY(), null);
		}
	}
	
	private void buildStructure() {
		//some event placing the structure here
		if(structure != null){
			
		}
	}
}
