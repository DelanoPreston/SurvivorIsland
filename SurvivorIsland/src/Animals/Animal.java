package Animals;

import Main.Entity;

public class Animal extends Entity{
	int health;
	MovementType movementType;
	
	public Animal(String inName, double[] inLocation, MovementType inMoveType, int inHealth, int inWeight, boolean inSolid) {
		super(inName, inLocation, inWeight, inSolid);
		health = inHealth;
		movementType = inMoveType;
	}

}
