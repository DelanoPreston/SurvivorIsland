package People;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import Entity.Entity;
import Event.CustomEventSource;
import Main.BaseGameFunctions;

public class Human extends Entity {
	/**
	 * this is just the id of the serializing object (verification thing)
	 */
	private static final long serialVersionUID = 10101010L;
	CommonStats cStats;
	public List<double[]> destination = new ArrayList<double[]>();
	int destinationIndex = 0;
	int hunger = 20;
	CustomEventSource source;
	BaseGameFunctions bgf = new BaseGameFunctions();

	public Human(String inName, double[] inLocation, double inWeight, boolean inSolid, CustomEventSource inSource) {
		super(inName, inLocation, inWeight, inSolid);
		cStats = new CommonStats(bgf.random.nextInt(1), bgf.random.nextInt(3));
		//setDestination(32);
		source = inSource;
	}

	public void update() {
		double dist = bgf.getDistance(location, destination.get(destinationIndex));
		if (dist < 5) {
			//setDestination(32);
		}
		location[0] -= bgf.getComponentLengths(location, destination.get(destinationIndex), cStats.stats.get("speed").level)[0];
		location[1] -= bgf.getComponentLengths(location, destination.get(destinationIndex), cStats.stats.get("speed").level)[1];
	}

	@Override
	public void paintComponent(Graphics2D g2D) {

	}

	public void drawPath(Graphics2D g2D) {
		for (int i = 0; i < destination.size(); i++) {
			if (i == 0)
				g2D.drawLine((int) location[0] + 8, (int) location[1] + 32, (int) destination.get(i)[0], (int) destination.get(i)[1]);
			else
				g2D.drawLine((int) destination.get(i - 1)[0], (int) destination.get(i - 1)[1], (int) destination.get(i)[0], (int) destination.get(i)[1]);
		}
	}

	public void moveToAndRemoveDest() {
		if (destination.size() > 0) {
			// this imcrements the movement of the survivor
			location[0] -= bgf.getComponentLengths(location, destination.get(destinationIndex), cStats.stats.get("speed").level)[0];
			location[1] -= bgf.getComponentLengths(location, destination.get(destinationIndex), cStats.stats.get("speed").level)[1];
			if (bgf.getDistance(location, destination.get(destinationIndex)) < 5) {
				destination.remove(0);
			}
		}
	}

	protected void getAdjacentTile(int range) {

	}

	

	protected void addDestination(int[] inDest) {
		double[] temp = new double[2];
		temp[0] = inDest[0];
		temp[1] = inDest[1];
		destination.add(temp);
	}

	protected void addDestination(double[] inDest) {
		destination.add(inDest);
	}
}