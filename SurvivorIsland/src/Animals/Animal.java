package Animals;

import Main.Entity;

public class Animal extends Entity {
	private static final long serialVersionUID = 481638741773743044L;
	int health;
	MovementType movementType;

	/**
	 * 
	 * @param inName
	 * @param inHealth
	 * @param inWeight
	 * @param inSolid
	 */
	public Animal(String inName, int inHealth, double inWeight, boolean inSolid) {
		super(inName, inWeight, inSolid);
		health = inHealth;
	}

	/**
	 * 
	 * @param inName
	 * @param inLocation
	 * @param inMoveType
	 * @param inHealth
	 * @param inWeight
	 * @param inSolid
	 */
	public Animal(String inName, double[] inLocation, MovementType inMoveType, int inHealth, double inWeight, boolean inSolid) {
		super(inName, inLocation, inWeight, inSolid);
		health = inHealth;
		movementType = inMoveType;
	}

}
