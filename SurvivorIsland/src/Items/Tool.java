package Items;


public class Tool extends Item {
	public ToolType toolType;
	public int durability;
	
	public Tool(String inName,  double inWeight, boolean inSolid, int inCond, ToolType inToolType, int inDur) {
		super(inName, inWeight, inSolid, inCond);
		toolType = inToolType;
		durability = inDur;
	}
}