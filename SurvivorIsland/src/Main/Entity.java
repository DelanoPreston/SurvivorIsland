package Main;

public class Entity {
	String name;
	double[] location;
	boolean solid;
	int weight;
	
	public Entity(String inName, double[] inLocation, int inWeight, boolean inSolid){
		name = inName;
		location = inLocation;
		weight = inWeight;
		solid = inSolid;
	}
}
