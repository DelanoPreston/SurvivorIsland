package People;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

public class CommonStats implements Serializable{
	private static final long serialVersionUID = 4393463075886017235L;
	public HashMap<String, Stat> stats = new HashMap<String, Stat>();
	int gender;
	int race;
	
	CommonStats(int inGender, int inRace){
		gender = inGender;
		race = inRace;
		randomStats();
	}
	
	private void randomStats(){
		Random random = new Random();
		stats.put("attack", new Stat(random.nextInt(3)));
		stats.put("defense", new Stat(random.nextInt(3)));
		stats.put("speed", new Stat(2));
		stats.put("visibility", new Stat(random.nextInt(3)));
		stats.put("strength", new Stat(random.nextInt(3)));
	}
}
