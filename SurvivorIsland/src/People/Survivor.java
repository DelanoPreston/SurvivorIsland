package People;

public class Survivor extends Human{
	int happiness;
	WorkingStats wStats;
	
	public Survivor(String inName, double[] inLocation, int inWeight, boolean inSolid) {
		super(inName, inLocation, inWeight, inSolid);
		wStats = new WorkingStats();
	}

	

}
