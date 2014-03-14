package Entity;

import java.awt.Image;

import Main.Location;

public class PlaceableEntity extends Entityz{
	
	public PlaceableEntity(String inName, Location inLocation, double inWeight) {
		super(inName, inLocation, inWeight);
		// TODO Auto-generated constructor stub
	}
	
	public PlaceableEntity(String inName, Location inLocation, double inWeight, boolean inSolid) {
		super(inName, inLocation, inWeight, inSolid);
		// TODO Auto-generated constructor stub
	}
	
	public Image getImage(){
		return null;
	}
	
}
