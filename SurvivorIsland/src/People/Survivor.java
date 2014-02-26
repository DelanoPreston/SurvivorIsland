package People;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Event.CustomEventSource;
import Items.Item;
import Items.ItemEntity;
import Main.ContentBank;

public class Survivor extends Human {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8276603702773051758L;
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
	public void update() {
		double dist = bgf.getDistance(location, destination);
		switch (job) {
		case GATHER:
			if (targetItem == null && source.getEntityCountEvent("itementities") > 0) {
				targetItem = (ItemEntity)source.findEntityEvent(this, "itementities");
				targetItem.targetted = true;
			} else if (targetItem != null) {
				destination = targetItem.location;
				if (bgf.getDistance(location, destination) < 5) {
					inventory.add(targetItem.item);
					source.removeEntityEvent(targetItem, "itementities");
					targetItem = null;
				}
				break;
			}
		case NONE:
			// wander
			if (dist < 5) {
				setDestination(32);
			}
			break;
		default:
			break;
		}

		location[0] -= bgf.getComponentLengths(location, destination, cStats.stats.get("speed").level)[0];
		location[1] -= bgf.getComponentLengths(location, destination, cStats.stats.get("speed").level)[1];
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(ContentBank.survivorM1, (int) location[0], (int) location[1], null);
	}

}
