package NoGoodAnymore;

import java.awt.Graphics2D;

import Main.ContentBank;
import Main.Location;

public class ItemEntity extends Entityz {
	private static final long serialVersionUID = -1163587124918848417L;
	public Item item;
	public boolean targetted;

	// MyEventSource source;

	public ItemEntity(Item inItem, Location inLocation) {
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
	public void paintComponent(Graphics2D g) {

		g.drawImage(ContentBank.woodenAxe, location.getMapX(), location.getMapY(), null);
	}
}
