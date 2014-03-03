package Entity;

import java.awt.Graphics;

import javax.swing.JComponent;

import Main.Level;
import Maps.AStarPathFinder;
import Maps.Path;

public class Entity extends JComponent{
	private static final long serialVersionUID = 723472537018845637L;
	public String name;
	public double[] location;
	public boolean solid;
	public double weight;
	public EntityType type;
	
	
	public Entity(String inName, double[] inLocation, double inWeight, boolean inSolid){
		name = inName;
		location = inLocation;
		weight = inWeight;
		solid = inSolid;
		type = EntityType.LAND;
	}
	
	public Entity(String inName, double[] inLocation, double inWeight){
		name = inName;
		location = inLocation;
		weight = inWeight;
		type = EntityType.LAND;
	}
	
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	public Path findClosestPath(int[] destination){
		Path path;
		AStarPathFinder finder = new AStarPathFinder(Level.map, 5000, true);
		//the locations need to be changed so that the entities walk on tiles, not pixels
		path = finder.findPath(this, (int)location[0], (int)location[1], destination[0], destination[1]);
		return path;
	}
}
