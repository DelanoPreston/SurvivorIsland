package Entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.image.BufferedImageOp;

import Main.ContentBank;
import Main.Location;

public class ConstructionEntity extends Entity {
	private static final long serialVersionUID = 6870326360861517120L;
	private PlaceableEntity structure;
	private double progress;
	private boolean placed;

	// needed materials

	public double getProgress() {
		return progress;
	}

	public void addProgress(double work) {
		progress += work;
	}

	public void setPlaced() {
		placed = true;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public ConstructionEntity(ConstructionEntity e){
		super(e.name, e.location, false);
		structure = e.structure;
		progress = 0;//some centity value
		placed = true;
	}
	
	public ConstructionEntity(String inName, PlaceableEntity inStructure, boolean inSolid) {
		super(inName, inStructure.location, inSolid);
		structure = inStructure;
		progress = 0;// probably have work amount passed in
		placed = false;
	}

	public ConstructionEntity(String inName, PlaceableEntity inStructure, Location inLocation, boolean inSolid) {
		super(inName, inLocation, inSolid);
		structure = inStructure;
		progress = 0;// probably have work amount passed in
		placed = false;
	}

	@Override
	public void update() {
		if (placed){
			if(progress >= 100) {
				buildStructure();
			}
		} else
			location.setLocationAtTile();
	}

	@Override
	public void paintComponent(Graphics2D g2D) {
		if (placed) {
			g2D.drawImage(structure.getImage(), location.getMapX(), location.getMapY(), null);
		} else {

			// BufferedImage image = ...;
			// BufferedImageOp imageFilter = new ColorTintFilter(Color.RED, .5f);
			// Image image = imageFilter.filter(image, null);

			float alpha = 0.5f;
			int type = AlphaComposite.SRC_OVER;
			AlphaComposite composite = AlphaComposite.getInstance(type, alpha);
			g2D.setComposite(composite);
			g2D.drawImage(structure.getImage(), location.getMapX(), location.getMapY(), null);
		}
	}

	private void buildStructure() {
		// some event placing the structure here
		if (structure != null) {
			//also set the structures location to the construction entities location
		}
	}
}
