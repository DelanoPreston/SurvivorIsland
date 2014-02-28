package People;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Entity.ItemEntity;
import Event.CustomEventSource;
import Items.Item;
import Main.ContentBank;

public class Survivor extends Human {
	/**
	 * 
	 */
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
	public void update() {
		try {
			double dist = 0.0;
			if (destination.size() > 0)
				dist = bgf.getDistance(location, destination.get(destinationIndex));
			if (dist > 5) {
				double tempVal = bgf.getComponentLengths(location, destination.get(destinationIndex), cStats.stats.get("speed").level)[0];
				location[0] -= tempVal;
				tempVal = bgf.getComponentLengths(location, destination.get(destinationIndex), cStats.stats.get("speed").level)[1];
				location[1] -= tempVal;
			}
			switch (job) {
			case GATHER:
				/*
				 * if(targetItem == null && inventory.size() > 1){ targetItem =
				 * (ItemEntity)source.findEntityEvent(this,
				 * "item:furnitureEntities"); // targetItem.targetted = true;
				 * }else
				 */
				if (targetItem == null && source.getEntityCountEvent("itementities") > 0) {
					targetItem = (ItemEntity) source.findEntityEvent(this, "item:itementities");
					targetItem.targetted = true;
					destination.add(targetItem.location);
					break;
				} else if (targetItem != null) {
					// destination = targetItem.location;
					double tempdist = bgf.getDistance(location, targetItem.location);// destination.get(destinationIndex));
					if (tempdist < 5) {
						inventory.add(targetItem.item);
						source.removeEntityEvent(targetItem, "itementities");
						targetItem = null;
					}
					break;
				}
			case NONE:
				// wander
				if (dist < 5) {
					if (destination.size() > 0)
						destination.remove(0);
					timer++;
					if (timer >= 100) {
						setDestination(32);
						timer = 0;
					}

				}
				break;
			default:
				break;
			}
		} catch (Exception e) {

		}

	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(ContentBank.survivorM1, (int) location[0], (int) location[1], null);
	}

}
