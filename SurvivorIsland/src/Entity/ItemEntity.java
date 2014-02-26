package Entity;

import java.awt.Graphics;

import Items.Item;
import Main.ContentBank;

public class ItemEntity extends Entity {
	private static final long serialVersionUID = -1163587124918848417L;
	public Item item;
	public boolean targetted;

	// MyEventSource source;

	public ItemEntity(Item inItem, double[] inLocation) {
		super(inItem.name, inLocation, inItem.weight);
		targetted = false;
		item = inItem;
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

		g.drawImage(ContentBank.woodenAxe, (int) location[0], (int) location[1], null);
	}
}
