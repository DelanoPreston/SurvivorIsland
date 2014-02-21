package Items;

public class Food extends Item{
	int replenishment;
	
	/**
	 * 
	 * @param inName
	 * @param inReplenish
	 * @param inWeight
	 * @param inSolid
	 */
	public Food(String inName, int inReplenish, double inWeight, boolean inSolid) {
		super(inName, inWeight, inSolid);
		replenishment = inReplenish;
	}
	
	/**
	 * 
	 * @param inName - 
	 * @param inReplenish - 
	 * @param inCond - 
	 * @param inLocation - 
	 * @param inWeight - 
	 * @param inSolid - 
	 */
	public Food(String inName, int inReplenish, int inCond, double[] inLocation, double inWeight, boolean inSolid) {
		super(inName, inCond, inLocation, inWeight, inSolid);
		replenishment = inReplenish;
	}

	
}
