package Items;

import Main.Entity;

public class Item extends Entity{
	public int condition;
	public ToolType toolType;
	public int replen;
	
	public Item(String inName,double inWeight, boolean inSolid, int inCond, ToolType inToolType, int inReplen) {
		super(inName, inWeight, inSolid);
		condition = inCond;
		toolType = inToolType;
		replen = inReplen;
	}
	
	/**
	 * 
	 * @param inName
	 * @param inCond
	 * @param inLocation
	 * @param inWeight
	 * @param inSolid
	 */
	public Item(String inName, int inCond, double[] inLocation, double inWeight, boolean inSolid) {
		super(inName, inLocation, inWeight, inSolid);
		condition = inCond;
	}


}
