package NoGoodAnymore;

import java.io.Serializable;

public class Stat implements Serializable{
	private static final long serialVersionUID = -4795703727497778867L;
	public int level;
	public int progress;
	public int nextLevel;
	
	public Stat(){
		level = 0;
		progress = 0;
		nextLevel = 100;
	}
	public Stat(int inLevel){
		level = inLevel;
		progress = 0;
		nextLevel = 100 * (int)Math.pow(2.5, inLevel);
	}
	
	public void addExp(){
		progress += 1;
		if(progress >= nextLevel){
			level += 1;
			nextLevel *= 2.5;
		}
	}
}
