package Entity;

import java.awt.Graphics2D;

import javax.swing.JComponent;

import Main.Location;

public class Entity extends JComponent{
	private static final long serialVersionUID = 723472537018845637L;
	public String name;
	protected Location location;
	public boolean solid;
	public double weight;
	public EntityType type;
	protected int imageKey = 0;;
	
	public int getX(){
		return location.getMapX();
	}
	
	public int getY(){
		return location.getMapY();
	}
	
	public int getTileX(){
		return location.getTileX();
	}
	
	public int getTileY(){
		return location.getTileY();
	}
	
	public Location getMapLocation(){
		return location;
	}
	
	public String getName(){
		return name;
	}
	
	public int getImageKey(){
		return imageKey;
	}
	
	public Entity(String inName, Location inLocation, double inWeight, boolean inSolid){
		name = inName;
		location = inLocation;
		weight = inWeight;
		solid = inSolid;
		type = EntityType.LAND;
	}
	
	public Entity(String inName, Location inLocation, double inWeight){
		name = inName;
		location = inLocation;
		weight = inWeight;
		type = EntityType.LAND;
	}
	
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void paintComponent(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
	
	
}
