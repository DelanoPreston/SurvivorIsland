package Main;

import java.awt.Graphics;

import javax.swing.JComponent;

public class Entity extends JComponent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 723472537018845637L;
	public String name;
	public double[] location;
	public boolean solid;
	public double weight;
	
	public Entity(String inName, double inWeight, boolean inSolid){
		name = inName;
		weight = inWeight;
		solid = inSolid;
	}
	
	public Entity(String inName, double[] inLocation, double inWeight, boolean inSolid){
		name = inName;
		location = inLocation;
		weight = inWeight;
		solid = inSolid;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
