package People;

public class Level {
	public int level;
	public int progress;
	public int nextLevel;
	
	public Level(){
		level = 0;
		progress = 0;
		nextLevel = 100;
	}
	public Level(int inLevel){
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
