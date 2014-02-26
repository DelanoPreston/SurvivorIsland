package Items;

public class Food extends Item {
	private static final long serialVersionUID = 8274457008892709616L;
	public int replenishment;

	public Food(String inName, double inWeight, boolean inSolid, int inCondition, int inReplenish) {
		super(inName, inWeight, inSolid, inCondition);
		replenishment = inReplenish;
	}
}
