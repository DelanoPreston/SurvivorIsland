package Items;

public class Tool extends Item{
	ToolType toolType;
	
	
	public Tool(String inName, Condition inCond, ToolType inToolType, double[] inLocation, int inWeight, boolean inSolid) {
		super(inName, inCond, inLocation, inWeight, inSolid);
		toolType = inToolType;
	}

	

}
