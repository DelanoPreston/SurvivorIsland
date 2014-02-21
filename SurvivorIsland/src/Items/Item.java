package Items;

import Main.Entity;

public class Item extends Entity{
	public int condition; 
	
	/**
	 * 
	 * @param inName
	 * @param inWeight
	 * @param inSolid
	 */
	public Item(String inName, double inWeight, boolean inSolid){
		super(inName, inWeight, inSolid);
	}
	
	/**
	 * 
	 * @param inName
	 * @param inCond
	 * @param inWeight
	 * @param inSolid
	 */
	public Item(String inName, int inCond, double inWeight, boolean inSolid) {
		super(inName, inWeight, inSolid);
		condition = inCond;
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
