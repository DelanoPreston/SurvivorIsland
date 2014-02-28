package People;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Entity.Entity;
import Event.CustomEventSource;
import Main.BaseGameFunctions;

public class Human extends Entity{
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
		setDestination(32);
		source = inSource;
	}
	
	public void update(){
		double dist = bgf.getDistance(location, destination.get(destinationIndex));
		if(dist < 5){
			setDestination(32);
		}
		location[0] -= bgf.getComponentLengths(location, destination.get(destinationIndex), cStats.stats.get("speed").level)[0];
		location[1] -= bgf.getComponentLengths(location, destination.get(destinationIndex), cStats.stats.get("speed").level)[1];
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
	}

	protected void setDestination(int range){
		double[] temp = new double[2];
		temp[0] = location[0] + bgf.random.nextInt(range * 2) - range;
		temp[1] = location[1] + bgf.random.nextInt(range * 2) - range;
		destination.add(temp);
//		destination.get(destinationIndex)[0] = location[0] + bgf.random.nextInt(range * 2) - range;
//		destination.get(destinationIndex)[1] = location[1] + bgf.random.nextInt(range * 2) - range;
	}

}