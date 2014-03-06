package Entity;

import Main.Location;

public class ConstructionEntity extends Entity {
	private static final long serialVersionUID = 6870326360861517120L;
	private StructureEntity structure;
	private double progress;
	//needed materials
	
	public double getProgress(){
		return progress;
	}
	
	public ConstructionEntity(String inName, StructureEntity inStructure, Location inLocation, double inWeight, boolean inSolid) {
		super(inName, inLocation, inWeight, inSolid);
		structure = inStructure;
		progress = 0;
	}

	@Override
	public void update() {
		if (progress >= 100)
			buildStructure();
	}

	private void buildStructure() {
		//some event placing the structure here
		
	}
}
