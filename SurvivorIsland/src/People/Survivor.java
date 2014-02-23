package People;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Items.Item;
import Items.ItemEntity;
import Main.ContentBank;
import Main.Entity;
import Main.GamePanel;

@SuppressWarnings("serial")
public class Survivor extends Human{
	int happiness;
	WorkingStats wStats;
	ItemEntity targetItem;
	Job job = Job.GATHER;
	List<Item> inventory = new ArrayList<>();
	
	public Survivor(String inName, double[] inLocation, int inWeight, boolean inSolid) {
		super(inName, inLocation, inWeight, inSolid);
		wStats = new WorkingStats();
	}
	
	@Override
	public void update(){
		double dist = bgf.getDistance(location, destination);
		switch(job){
		case GATHER:
			if(targetItem == null && GamePanel.itemEntities.size() > 0){
				ItemEntity closestItem = null;
				for(int i = 0; i < GamePanel.itemEntities.size(); i++){
					GamePanel.itemEntities.get(i);
					if(closestItem == null || bgf.getDistance(GamePanel.itemEntities.get(i).location, location) < bgf.getDistance(closestItem.location, location)){
						closestItem = GamePanel.itemEntities.get(i);
					}
				}
				targetItem = closestItem;
				break;
			}else if(targetItem != null){
				destination = targetItem.location;
				if(bgf.getDistance(location, destination) < 5){
					inventory.add(targetItem.item);
					GamePanel.itemEntities.remove(targetItem);
					targetItem = null;
				}
				break;
			}
		case NONE:
			//wander
			if(dist < 5){
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
		g.drawImage(ContentBank.survivorM1, (int)location[0], (int)location[1], null);
	}

}
