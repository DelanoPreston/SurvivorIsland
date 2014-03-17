package Animals;

import Main.Location;
import NoGoodAnymore.Entityz;

public class Animal extends Entityz {
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
	public Animal(String inName, Location inLocation, int inHealth, double inWeight) {
		super(inName, inLocation, inWeight);
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
	public Animal(String inName, Location inLocation, MovementType inMoveType, int inHealth, double inWeight) {
		super(inName, inLocation, inWeight);
		health = inHealth;
		movementType = inMoveType;
	}

}
