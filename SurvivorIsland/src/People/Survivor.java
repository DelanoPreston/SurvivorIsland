package People;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Entity.ItemEntity;
import Event.CustomEventSource;
import Items.Item;
import Main.ContentBank;

public class Survivor extends Human {
	private static final long serialVersionUID = 8276603702773051758L;
	int timer = 0;
	int happiness;
	WorkingStats wStats;
	ItemEntity targetItem;
	Job job = Job.GATHER;
	List<Item> inventory = new ArrayList<>();

	public Survivor(String inName, double[] inLocation, int inWeight, boolean inSolid, CustomEventSource inSource) {
		super(inName, inLocation, inWeight, inSolid, inSource);
		wStats = new WorkingStats();
	}

	public Survivor(Survivor dad, Survivor mom, CustomEventSource inSource) {
		super(dad.name, dad.location, 3.6, false, inSource);

	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(new Color(0, 0, 0, 96));
		for (int i = 0; i < destination.size(); i++) {
			if (i == 0)
				g.drawLine((int) location[0], (int) location[1], (int) destination.get(i)[0], (int) destination.get(i)[1]);
			else
				g.drawLine((int) destination.get(i - 1)[0], (int) destination.get(i - 1)[1], (int) destination.get(i)[0], (int) destination.get(i)[1]);
		}
		g.setColor(new Color(0, 0, 0, 255));
		g.drawImage(ContentBank.survivorM1, (int) location[0], (int) location[1], null);
	}

	@Override
	public void update() {
		moveToAndRemoveDest();

		switch (job) {
		case GATHER:
			gather();
			break;
		case NONE:
			wander();
			break;
		default:
			break;
		}

	}
	
	public void gather() {
		if (targetItem == null && source.getEntityCountEvent("itementities", "targettable") > 0) {
			targetItem = (ItemEntity) source.findEntityEvent(this, "item:itementities");
			targetItem.targetted = true;
			destination.add(targetItem.location);
		} else if (targetItem != null) {
			double tempdist = bgf.getDistance(location, targetItem.location);
			if (tempdist < 5) {
				inventory.add(targetItem.item);
				source.removeEntityEvent(targetItem, "itementities");
				targetItem = null;
			}
		}
	}

	public void wander() {
		timer++;
		if (timer >= 100) {
			setDestination(32);
			timer = 0;
		}
	}
}
