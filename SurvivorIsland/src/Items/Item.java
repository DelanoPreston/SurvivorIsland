package Items;

import java.awt.Graphics;

import javax.swing.JComponent;

public class Item extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8067743070572962088L;
	public String name;
	public double weight;
	public int condition;
	public int imageKey;

	/**
	 * constructor for Item
	 * 
	 * @param inName
	 *            - name of the Item
	 * @param inWeight
	 *            - weight of the Item
	 * @param inSolid
	 *            - if the Item is solid
	 * @param inCond
	 *            - condition of the Item
	 * @param inToolType
	 *            - type of the Item
	 * @param inReplen
	 *            - replenish of the Item
	 */
	public Item(String inName, double inWeight, int inCondition) {
		name = inName;
		weight = inWeight;
		condition = inCondition;
		// this.addInputMethodListener(l);
		// this.add
	}

	public void paintComponent(Graphics g) {

	}

}
