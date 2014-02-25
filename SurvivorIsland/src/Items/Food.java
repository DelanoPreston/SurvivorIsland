package Items;

public class Food extends Item{
  	public int replenishment;
  	
	public Food(String inName, double inWeight, boolean inSolid, int inReplenish, int inCondition) {
 		super(inName, inWeight, inSolid, inCondition);
  		replenishment = inReplenish;
  	}
}
