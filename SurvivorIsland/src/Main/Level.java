package Main;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Entity.Entity;
import Entity.FurnitureEntity;
import Entity.ItemEntity;
import Event.CustomEventClassListener;
import Event.EntityEvent;
import Event.StringEvent;
import Maps.Map;
import People.Human;
import People.Survivor;
import Plants.Plant;

public class Level implements Serializable, CustomEventClassListener {

	private static final long serialVersionUID = -6787896202288628502L;
	private Map map;
	private List<Human> humans = new ArrayList<>();
	private List<ItemEntity> itemEntities = new ArrayList<>();
	private List<FurnitureEntity> furnitureEntities = new ArrayList<>();
	private List<Plant> plants = new ArrayList<>();
	private BaseGameFunctions bgf = new BaseGameFunctions();
	private Entity selectedEntity = null;

	public void addHuman(Survivor human) {
		humans.add(human);
	}

	public void addItem(ItemEntity itemEntity) {
		itemEntities.add(itemEntity);
	}

	public void addFurniture(FurnitureEntity fEntity) {
		furnitureEntities.add(fEntity);
	}
	
	public Entity getSelectedEntity(){
		return selectedEntity;
	}
	
	public void setMap(Map inMap){
		map = inMap;
	}
	
	public void paintComponent(Graphics2D g2D) {
		map.paintComponent(g2D);

		AffineTransform at = g2D.getTransform();

		// g2D.setTransform(at);

		for (int i = 0; i < furnitureEntities.size(); i++) {
			furnitureEntities.get(i).paintComponent(g2D);
		}
		AffineTransform itemAT = at;
		itemAT.translate(-8, -8);
		g2D.setTransform(itemAT);
		for (int i = 0; i < itemEntities.size(); i++) {
			itemEntities.get(i).paintComponent(g2D);
		}
		
		for (int i = 0; i < humans.size(); i++) {
			humans.get(i).paintComponent(g2D);
			// g2D.drawOval((int) humans.get(i).location[0] - 25, (int) humans.get(i).location[1] - 25, 50, 50);
		}
	}

	public void update() {
		map.update();
		for (int i = 0; i < humans.size(); i++) {
			humans.get(i).update();
		}
		for (int i = 0; i < itemEntities.size(); i++) {
			itemEntities.get(i).update();
		}
		for (int i = 0; i < furnitureEntities.size(); i++) {
			furnitureEntities.get(i).update();
		}
	}

	@Override
	public synchronized Entity handleFindEntityEvent(EntityEvent e) {
		Entity entity = null;
		switch (e.entityType.toLowerCase()) {
		case "item:itementities":
			// System.out.println(e.entity.location[0] + "," + e.entity.location[1]);

			for (int i = 0; i < itemEntities.size(); i++) {
				// itemEntities.get(i);
				if (!itemEntities.get(i).targetted
						&& (entity == null || bgf.getDistance(itemEntities.get(i).location, e.entity.location) < bgf.getDistance(entity.location,
								e.entity.location))) {

					entity = itemEntities.get(i);

				}
			}
		case "item:furnitureentities":
			// System.out.println(e.entity.location[0] + "," + e.entity.location[1]);

			for (int i = 0; i < furnitureEntities.size(); i++) {
				furnitureEntities.get(i);
				if (entity == null
						|| bgf.getDistance(furnitureEntities.get(i).location, e.entity.location) < bgf.getDistance(entity.location, e.entity.location)) {
					entity = itemEntities.get(i);
				}
			}
			break;
		case "plants":
			break;
		case "humans":
			for (int i = 0; i < humans.size(); i++) {
				humans.get(i);
				if (entity == null || bgf.getDistance(humans.get(i).location, e.entity.location) < bgf.getDistance(entity.location, e.entity.location)) {
					entity = humans.get(i);
				}
			}
			break;
		}
		return entity;
	}

	@Override
	public synchronized void handleRemoveEntityEvent(EntityEvent e) {
		switch (e.entityType.toLowerCase()) {
		case "itementities":
			itemEntities.remove(e.entity);
			break;
		case "plants":
			plants.remove(e.entity);
			break;
		case "humans":
			humans.remove(e.entity);
			break;
		}
	}

	@Override
	public synchronized int handleGetEntityCountEvent(StringEvent e) {
		switch (e.command.toLowerCase()) {
		case "itementities":
			if (e.subCommand.toLowerCase().equals("targettable")) {
				int temp = 0;
				for (int i = 0; i < itemEntities.size(); i++) {
					if (!itemEntities.get(i).targetted)
						temp++;
				}
				return temp;
			} else
				return itemEntities.size();
		case "plants":
			return plants.size();
		case "humans":
			return humans.size();
		}
		return 0;
	}

	@Override
	public synchronized int[] handleGetAdjacentTileLocation(EntityEvent e) {
		int[] tileLocation = map.getTileAtLocation(e.entity.getX(), e.entity.getY());
		int[] temp = { -1, -1 };

		while (temp[0] == -1) {
			int x1 = GamePanel.random.nextInt(5) - 2;// + tileLocation[0];
			int y1 = GamePanel.random.nextInt(5) - 2;// + tileLocation[1];
			int x = x1 + tileLocation[0];
			int y = y1 + tileLocation[1];

			if (!map.blocked(e.entity, x, y)) {
				temp = map.getLocationAtTile(x, y);
			}
		}

		return temp;
	}
	
	public synchronized Map handleGetMap(){
		return map;
	}

	public synchronized Location handleGetTileAtLocation(Location inTileLoc){
		int[] tempugh = map.getTileAtLocation(inTileLoc);
		Location temp = new Location(tempugh[0] * 16 + 8, tempugh[1] * 16 + 8);
		return temp;
	}
}
