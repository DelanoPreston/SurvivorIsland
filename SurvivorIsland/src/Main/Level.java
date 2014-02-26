package Main;

import java.awt.Graphics2D;
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
import Plants.Plant;

public class Level implements Serializable, CustomEventClassListener {

	private static final long serialVersionUID = -6787896202288628502L;

	public Map map;
	public List<Human> humans = new ArrayList<>();
	public List<ItemEntity> itemEntities = new ArrayList<>();
	public List<FurnitureEntity> furnitureEntities = new ArrayList<>();
	public List<Plant> plants = new ArrayList<>();
	BaseGameFunctions bgf = new BaseGameFunctions();
	public Entity selectedEntity = null;
	
	public void paintComponent(Graphics2D g2D){
		map.paintComponent(g2D);
		for (int i = 0; i < humans.size(); i++) {
			humans.get(i).paintComponent(g2D);
			g2D.drawOval((int)humans.get(i).location[0] - 25, (int)humans.get(i).location[1] - 25, 50, 50);
		}
		for (int i = 0; i < itemEntities.size(); i++) {
			itemEntities.get(i).paintComponent(g2D);
		}
		for (int i = 0; i < furnitureEntities.size(); i++) {
			furnitureEntities.get(i).paintComponent(g2D);
		}
	}
	
	public void update(){
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
	public Entity handleFindEntityEvent(EntityEvent e) {
		Entity entity = null;
		switch (e.entityType.toLowerCase()) {
		case "item:itementities":
			System.out.println(e.entity.location[0] + "," + e.entity.location[1]);

			for (int i = 0; i < itemEntities.size(); i++) {
				itemEntities.get(i);
				if (entity == null || bgf.getDistance(itemEntities.get(i).location, e.entity.location) < bgf.getDistance(entity.location, e.entity.location)) {
					entity = itemEntities.get(i);
				}
			}
		case "item:furnitureentities":
			System.out.println(e.entity.location[0] + "," + e.entity.location[1]);

			for (int i = 0; i < furnitureEntities.size(); i++) {
				furnitureEntities.get(i);
				if (entity == null || bgf.getDistance(furnitureEntities.get(i).location, e.entity.location) < bgf.getDistance(entity.location, e.entity.location)) {
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
	public void handleRemoveEntityEvent(EntityEvent e) {
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
	public int handleGetEntityCountEvent(StringEvent e) {
		switch (e.entityType.toLowerCase()) {
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
