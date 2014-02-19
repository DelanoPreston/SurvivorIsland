package Items;

public class Food extends Item{
	int replenishment;
	
	public Food(String inName, int inReplenish, Condition inCond, double[] inLocation, int inWeight, boolean inSolid) {
		super(inName, inCond, inLocation, inWeight, inSolid);
		replenishment = inReplenish;
	}

	
}
