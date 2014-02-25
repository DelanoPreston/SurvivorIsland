package Items;

public class Food extends Item {
	public int replenishment;

	public Food(String inName, double inWeight, boolean inSolid, int inCondition, int inReplenish) {
		super(inName, inWeight, inSolid, inCondition);
		replenishment = inReplenish;
	}
}
