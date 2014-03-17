package NoGoodAnymore;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import Event.CustomEventSource;
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
		super(dad.name, dad.getMapLocation(), 3.6, false, inSource);

	}

	@Override
	public void paintComponent(Graphics2D g2D) {
		g2D.drawImage(ContentBank.survivorM1, location.getMapX(), location.getMapY(), null);
	}

	@Override
	public void update() {
		moveToAndRemoveDest();

		switch (job) {
		case CONSTRUCT:
			construct();
			break;
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
	
	private void construct(){
		
	}
	
	private void gather() {
		if (targetItem == null && source.getEntityCountEvent("itementities", "targettable") > 0) {
			targetItem = (ItemEntity) source.findEntityEvent(this, "item:itementities");
			
			targetItem.targetted = true;
			destination = findClosestPath(targetItem.getMapLocation()).changePathToDoubleList();
			//destination.add(targetItem.location);
		} else if (targetItem != null) {
			double tempdist = bgf.getDistance(location, targetItem.getMapLocation());
			if (tempdist <= 5) {
				inventory.add(targetItem.item);
				source.removeEntityEvent(targetItem, "itementities");
				targetItem = null;
			}
		}
	}

	private void wander() {
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
