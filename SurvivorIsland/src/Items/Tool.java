package Items;

public class Tool extends Item {
	private static final long serialVersionUID = 1116585432319003338L;
	public ToolType toolType;
	public int durability;

	public Tool(String inName, double inWeight, int inCond, ToolType inToolType, int inDur) {
		super(inName, inWeight, inCond);
		toolType = inToolType;
		durability = inDur;
	}
}