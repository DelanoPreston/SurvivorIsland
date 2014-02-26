package Main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Items.ItemEntity;
import Maps.Map;
import People.Human;
import People.Survivor;
import Plants.Plant;

public class Level implements Serializable, MyEventClassListener {
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
	public Entity handleFindEntityEvent(Entity e, String s) {
		Entity entity = null;
		switch (s.toLowerCase()) {
		case "itementities":
			System.out.println(e.location[0] + "," + e.location[1]);

			for (int i = 0; i < itemEntities.size(); i++) {
				itemEntities.get(i);
				if (entity == null || bgf.getDistance(itemEntities.get(i).location, e.location) < bgf.getDistance(entity.location, e.location)) {
					entity = itemEntities.get(i);
				}
			}
			break;
		case "plants":
			break;
		case "humans":
			break;
		}
//		if (e instanceof Survivor) {
//
//		}
		return entity;
	}

	@Override
	public void handleRemoveEntityEvent(Entity e, String s) {
		switch (s.toLowerCase()) {
		case "itementities":
			itemEntities.remove(e);
			break;
		case "plants":
			plants.remove(e);
			break;
		case "humans":
			humans.remove(e);
			break;
		}
	}

	@Override
	public int handleGetEntityCountEvent(String s) {
		switch (s.toLowerCase()) {
		case "itementities":
			return itemEntities.size();
		case "plants":
			return plants.size();
		case "humans":
			return humans.size();
		}
		return 0;
	}
}
