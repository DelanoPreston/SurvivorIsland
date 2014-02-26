package Animals;

public class TameAnimal extends Animal {

	private static final long serialVersionUID = 1343032400512437855L;

	public TameAnimal(String inName, int inHealth, double inWeight, boolean inSolid) {
		super(inName, inHealth, inWeight, inSolid);
		// TODO Auto-generated constructor stub
	}

	public TameAnimal(String inName, double[] inLocation, MovementType inMoveType, int inHealth, double inWeight, boolean inSolid) {
		super(inName, inLocation, inMoveType, inHealth, inWeight, inSolid);
		// TODO Auto-generated constructor stub
	}

}
