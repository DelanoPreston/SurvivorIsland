package Items;

import Main.Entity;

public class Item extends Entity{
	Condition condition;
	
	public Item(String inName, Condition inCond, double[] inLocation, int inWeight, boolean inSolid) {
		super(inName, inLocation, inWeight, inSolid);
		condition = inCond;
	}


}
