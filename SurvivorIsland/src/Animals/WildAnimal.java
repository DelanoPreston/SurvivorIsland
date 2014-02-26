package Animals;

public class WildAnimal extends Animal {

	private static final long serialVersionUID = -4611394495288938183L;

	public WildAnimal(String inName, int inHealth, double inWeight, boolean inSolid) {
		super(inName, inHealth, inWeight, inSolid);
		// TODO Auto-generated constructor stub
	}

	public WildAnimal(String inName, double[] inLocation, MovementType inMoveType, int inHealth, double inWeight, boolean inSolid) {
		super(inName, inLocation, inMoveType, inHealth, inWeight, inSolid);
		// TODO Auto-generated constructor stub
	}

}
