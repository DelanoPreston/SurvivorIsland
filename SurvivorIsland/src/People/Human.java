package People;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import Entity.Entityz;
import Event.CustomEventSource;
import Main.BaseGameFunctions;
import Main.Location;
import Maps.AStarPathFinder;
import Maps.Path;

public class Human extends Entityz {
	/**
	 * this is just the id of the serializing object (verification thing)
	 */
	private static final long serialVersionUID = 10101010L;
	CommonStats cStats;
	public List<Location> destination = new ArrayList<>();
	int destinationIndex = 0;
	int hunger = 20;
	CustomEventSource source;
	BaseGameFunctions bgf = new BaseGameFunctions();
	
	public CustomEventSource getSource(){
		return source;
	}
	
	public Human(String inName, Location inLocation, double inWeight, boolean inSolid, CustomEventSource inSource) {
		super(inName, inLocation, inWeight, inSolid);
		cStats = new CommonStats(bgf.random.nextInt(1), bgf.random.nextInt(3));
		// setDestination(32);
		source = inSource;
	}

	public void update() {
		location.addMovement(bgf.getComponentLengths(location, destination.get(destinationIndex), cStats.stats.get("speed").level));
	}

	@Override
	public void paintComponent(Graphics2D g2D) {

	}

	public void drawPath(Graphics2D g2D) {
		for (int i = 0; i < destination.size(); i++) {
			if (i == 0)
				g2D.drawLine(location.getMapX(), location.getMapY(), destination.get(i).getMapX(), destination.get(i).getMapY());
			else
				g2D.drawLine(destination.get(i - 1).getMapX(), destination.get(i - 1).getMapY(), destination.get(i).getMapX(), destination.get(i).getMapY());
		}
	}

	public void moveToAndRemoveDest() {
		if (destination.size() > 0) {
			// this imcrements the movement of the survivor
			location.addMovement(bgf.getComponentLengths(location, destination.get(destinationIndex), cStats.stats.get("speed").level));
//			location[0] -= bgf.getComponentLengths(location, destination.get(destinationIndex), cStats.stats.get("speed").level)[0];
//			location[1] -= bgf.getComponentLengths(location, destination.get(destinationIndex), cStats.stats.get("speed").level)[1];
			if (bgf.getDistance(location, destination.get(destinationIndex)) < 5) {
				destination.remove(0);
			}
		}
	}

	protected void getAdjacentTile(int range) {

	}

	protected void addDestination(int[] inDest) {
		Location temp = new Location(inDest[0], inDest[1]);
		destination.add(temp);
	}

	protected void addDestination(double[] inDest) {
		Location temp = new Location(inDest[0], inDest[1]);
		destination.add(temp);
	}

	public Path findClosestPath(Location destination) {
		Path path;
		AStarPathFinder finder = new AStarPathFinder(source.getMap(), 500, true);
		// the locations need to be changed so that the entities walk on tiles, not pixels
		path = finder.findPath(this, location.getTileX(), location.getTileY(), destination.getTileX(), destination.getTileY());
		return path;
	}
}