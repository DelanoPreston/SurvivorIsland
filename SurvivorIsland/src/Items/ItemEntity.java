package Items;

import java.awt.Graphics;

import Main.ContentBank;
import Main.Entity;

public class ItemEntity extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1163587124918848417L;
	public Item item;
	public boolean targetted;
	
	public ItemEntity(Item inItem, double[] inLocation) {
		super(inItem.name, inLocation, inItem.weight, inItem.solid);
		item = inItem;
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		g.drawImage(ContentBank.woodenAxe, (int)location[0], (int)location[1], null);
	}
}
