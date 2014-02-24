package Items;


public class Tool extends Item {
	ToolType toolType;

	public Tool(String inName, ToolType inToolType, int inWeight, boolean inSolid) {
		super(inName, inWeight, inSolid);
		toolType = inToolType;
	}
}