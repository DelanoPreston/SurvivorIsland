package People;

import java.awt.Graphics;

import Main.ContentBank;

@SuppressWarnings("serial")
public class Survivor extends Human{
	int happiness;
	WorkingStats wStats;
	
	public Survivor(String inName, double[] inLocation, int inWeight, boolean inSolid) {
		super(inName, inLocation, inWeight, inSolid);
		wStats = new WorkingStats();
	}
	
//	@Override
//	public void update(){
//		
//	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(ContentBank.survivorM1, (int)location[0], (int)location[1], null);//, arg4, arg5, arg6, arg7, arg8, arg9)
	}

}
