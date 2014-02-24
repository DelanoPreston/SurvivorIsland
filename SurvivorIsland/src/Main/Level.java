package Main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Items.ItemEntity;
import Maps.Map;
import People.Human;

public class Level implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6787896202288628502L;
	public Map map;
	public List<Human> humans = new ArrayList<>();
	public static List<ItemEntity> itemEntities = new ArrayList<>();
}
