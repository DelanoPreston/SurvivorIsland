package People;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import Entity.ItemEntity;
import Event.CustomEventSource;
import Items.Item;
import Main.ContentBank;
import Main.Location;

public class Survivor extends Human {
	private static final long serialVersionUID = 8276603702773051758L;
	int timer = 0;
	int happiness;
	WorkingStats wStats;
	ItemEntity targetItem;
	Job job = Job.GATHER;
	List<Item> inventory = new ArrayList<>();

	public Survivor(String inName, Location inLocation, int inWeight, boolean inSolid, CustomEventSource inSource) {
		super(inName, inLocation, inWeight, inSolid, inSource);
		wStats = new WorkingStats();
	}

	public Survivor(Survivor dad, Survivor mom, CustomEventSource inSource) {
		super(dad.name, dad.location, 3.6, false, inSource);

	}

	@Override
	public void paintComponent(Graphics2D g2D) {
		AffineTransform at = g2D.getTransform();
		g2D.setColor(new Color(0, 0, 0, 96));
		drawPath(g2D);
		g2D.setColor(new Color(0, 0, 0, 255));
		AffineTransform humanAT = at;
		humanAT.translate(-8, -32);
		g2D.setTransform(humanAT);
		g2D.drawImage(ContentBank.survivorM1, location.getMapX(), location.getMapY(), null);
		g2D.setTransform(at);
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
			destination = findClosestPath(targetItem.location).changePathToDoubleList();
			//destination.add(targetItem.location);
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
	
	protected void setDestination(int range) {
		// double[] temp = new double[2];
		// temp[0] = location[0] + bgf.random.nextInt(range * 2) - range;
		// temp[1] = location[1] + bgf.random.nextInt(range * 2) - range;
		int[] temp = new int[2];
		temp = source.getAdjacentTileLocation(this, Integer.toString(range));
		addDestination(temp);
		// destination.get(destinationIndex)[0] = location[0] + bgf.random.nextInt(range * 2) - range;
		// destination.get(destinationIndex)[1] = location[1] + bgf.random.nextInt(range * 2) - range;
	}
}
