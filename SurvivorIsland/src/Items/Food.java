package Items;

public class Food extends Item{
  	int replenishment;
  	
	public Food(String inName, double inWeight, boolean inSolid, int inReplenish) {
 		super(inName, inWeight, inSolid);
  		replenishment = inReplenish;
  	}
}
