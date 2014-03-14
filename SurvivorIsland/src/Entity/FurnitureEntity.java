package Entity;

import java.awt.Graphics;

import Items.Furniture;
import Main.ContentBank;
import Main.Location;

public class FurnitureEntity extends Entityz {

	private static final long serialVersionUID = -8112519755819574716L;
	public Furniture furniture;
	public boolean targetted;

	// MyEventSource source;

	public FurnitureEntity(Furniture inFurniture, Location inLocation) {
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

		g.drawImage(ContentBank.survivorW3, location.getMapX(), location.getMapY(), null);
	}
}
