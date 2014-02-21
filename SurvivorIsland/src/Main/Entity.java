package Main;

public class Entity {
	public String name;
	public double[] location;
	public boolean solid;
	public double weight;
	
	public Entity(String inName, double inWeight, boolean inSolid){
		name = inName;
		weight = inWeight;
		solid = inSolid;
	}
	
	public Entity(String inName, double[] inLocation, double inWeight, boolean inSolid){
		name = inName;
		location = inLocation;
		weight = inWeight;
		solid = inSolid;
	}
}
