package People;

import Main.Entity;

public class Human extends Entity{
	CommonStats cStats;
	
	
	public Human(String inName, double[] inLocation, int inWeight, boolean inSolid) {
		super(inName, inLocation, inWeight, inSolid);
		cStats = new CommonStats();
	}

	

}
//////////////////////event object, when you need to call event manually