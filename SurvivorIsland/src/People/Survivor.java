package People;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Items.Item;
import Items.ItemEntity;
import Main.ContentBank;
import Main.Level;
import Main.MyEventSource;

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

	public Survivor(String inName, double[] inLocation, int inWeight, boolean inSolid, MyEventSource inSource) {
		super(inName, inLocation, inWeight, inSolid, inSource);
		wStats = new WorkingStats();
	}

	public Survivor(Survivor dad, Survivor mom, MyEventSource inSource) {
		super(dad.name, dad.location, 3.6, false, inSource);

	}

	@Override
	public void update() {
		double dist = bgf.getDistance(location, destination);
		switch (job) {
		case GATHER:
			if (targetItem == null && Level.itemEntities.size() > 0) {
				destination = source.fireEntityEvent(this);
				System.out.println(destination[0] + "," + destination [1]);/////////////////almost!!!!
				ItemEntity closestItem = null;
				for (int i = 0; i < Main.Level.itemEntities.size(); i++) {
					Level.itemEntities.get(i);
					if (closestItem == null || bgf.getDistance(Level.itemEntities.get(i).location, location) < bgf.getDistance(closestItem.location, location)) {
						closestItem = Level.itemEntities.get(i);
					}
				}
				targetItem = closestItem;
				targetItem.targetted = true;
				break;
			} else if (targetItem != null) {
				destination = targetItem.location;
				if (bgf.getDistance(location, destination) < 5) {
					inventory.add(targetItem.item);
					Level.itemEntities.remove(targetItem);
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
