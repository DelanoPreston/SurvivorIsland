package People;

import java.awt.Graphics;
import java.util.Random;

import Main.BaseGameFunctions;
import Main.Entity;

@SuppressWarnings("serial")
public class Human extends Entity{
	CommonStats cStats;
	BaseGameFunctions bgf = new BaseGameFunctions();
	double[] destination = new double[2];
	
	public Human(String inName, double[] inLocation, int inWeight, boolean inSolid) {
		super(inName, inLocation, inWeight, inSolid);
		cStats = new CommonStats(bgf.random.nextInt(1), bgf.random.nextInt(3));
		setDestination(16);
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
		int i = 0;
	}

}