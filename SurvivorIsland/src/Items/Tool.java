package Items;

public class Tool extends Item{
	ToolType toolType;
	
	/**
	 * 
	 * @param inName
	 * @param inCond
	 * @param inToolType
	 * @param inWeight
	 * @param inSolid
	 */
	public Tool(String inName, int inCond, ToolType inToolType, double inWeight, boolean inSolid) {
		super(inName, inCond, inWeight, inSolid);
		toolType = inToolType;
	}
	
	/**
	 * 
	 * @param inName
	 * @param inCond
	 * @param inToolType
	 * @param inLocation
	 * @param inWeight
	 * @param inSolid
	 */
	public Tool(String inName, int inCond, ToolType inToolType, double[] inLocation, double inWeight, boolean inSolid) {
		super(inName, inCond, inLocation, inWeight, inSolid);
		toolType = inToolType;
	}

	

}
