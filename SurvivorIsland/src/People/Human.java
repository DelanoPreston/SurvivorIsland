package People;

import java.awt.Graphics;

import Entity.Entity;
import Event.CustomEventSource;
import Main.BaseGameFunctions;

public class Human extends Entity{
	/**
	 * this is just the id of the serializing object (verification thing)
	 */
	private static final long serialVersionUID = 10101010L;
	CommonStats cStats;
	double[] destination = new double[2];
	int hunger = 20;
	CustomEventSource source;
	BaseGameFunctions bgf = new BaseGameFunctions();
	
	
	public Human(String inName, double[] inLocation, double inWeight, boolean inSolid, CustomEventSource inSource) {
		super(inName, inLocation, inWeight, inSolid);
		cStats = new CommonStats(bgf.random.nextInt(1), bgf.random.nextInt(3));
		setDestination(16);
		source = inSource;
	}
	
	public void update(){
		double dist = bgf.getDistance(location, destination);
		if(dist < 5){
			setDestination(32);
		}
		location[0] -= bgf.getComponentLengths(location, destination, cStats.stats.get("speed").level)[0];
		location[1] -= bgf.getComponentLengths(location, destination, cStats.stats.get("speed").level)[1];
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
	}

	protected void setDestination(int range){
		destination[0] = location[0] + bgf.random.nextInt(range * 2) - range;
		destination[1] = location[1] + bgf.random.nextInt(range * 2) - range;
	}

}