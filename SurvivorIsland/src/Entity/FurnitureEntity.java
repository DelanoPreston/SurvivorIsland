package Entity;

import java.awt.Graphics;

import Items.Furniture;
import Main.ContentBank;

public class FurnitureEntity extends Entity {

	private static final long serialVersionUID = -8112519755819574716L;
	public Furniture furniture;
	public boolean targetted;

	// MyEventSource source;

	public FurnitureEntity(Furniture inFurniture, double[] inLocation) {
		super(inFurniture.name, inLocation, inFurniture.weight);
		targetted = false;
		furniture = inFurniture;
		// source = new MyEventSource();
		// source.addEventListener(new EventListener());
	}

	@Override
	public void update() {
		// if(targetted)
		// source.fireEvent(item);
	}

	@Override
	public void paintComponent(Graphics g) {

		g.drawImage(ContentBank.survivorW3, (int) location[0], (int) location[1], null);
	}
}
