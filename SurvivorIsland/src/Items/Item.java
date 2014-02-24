package Items;

import java.awt.Graphics;

public class Item{
	public String name;
	public double weight;
	public boolean solid;
	public int imageKey;
	
	/**
	 * constructor for Item
	 * @param inName - name of the Item
	 * @param inWeight - weight of the Item
	 * @param inSolid - if the Item is solid
	 * @param inCond - condition of the Item
	 * @param inToolType - type of the Item
	 * @param inReplen - replenish of the Item
	 */
	public Item(String inName,double inWeight, boolean inSolid) {
		name = inName;
		weight = inWeight;
		solid = inSolid;
	}
	
	public void paintComponent(Graphics g) {
		
	}
}
