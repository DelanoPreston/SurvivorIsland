package Items;

public class Food extends Item{
	int replenishment;
	
	public Food(String inName, int inReplenish, double[] inLocation, int inWeight, boolean inSolid) {
		super(inName, inLocation, inWeight, inSolid);
		replenishment = inReplenish;
	}

	
}
