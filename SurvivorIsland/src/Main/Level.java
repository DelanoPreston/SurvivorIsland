package Main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Items.ItemEntity;
import Maps.Map;
import People.Human;
import People.Survivor;
import Plants.Plant;

public class Level  implements Serializable, MyEventClassListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6787896202288628502L;
	
	public Map map;
	public List<Human> humans = new ArrayList<>();
	public List<ItemEntity> itemEntities = new ArrayList<>();
	public List<Plant> plants = new ArrayList<>();
	BaseGameFunctions bgf = new BaseGameFunctions();
	
	@Override
	public Entity handleFindEntityEvent(Entity e) {
		ItemEntity closestItem = null;
		if(e instanceof Survivor){
			System.out.println(e.location[0] + "," + e.location[1]);
			
			
			for (int i = 0; i < itemEntities.size(); i++) {
				itemEntities.get(i);
				if (closestItem == null || bgf.getDistance(itemEntities.get(i).location, e.location) < bgf.getDistance(closestItem.location, e.location)) {
					closestItem = itemEntities.get(i);
				}
			}
		}
		return closestItem;
	}
	
	@Override
	public void handleRemoveEntityEvent(Entity e){
		itemEntities.remove(e);
	}
	
	@Override
	public int handleGetEntityCountEvent(){
		return itemEntities.size();
	}
}
