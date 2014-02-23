package People;

import java.util.HashMap;
import java.util.Random;

public class CommonStats {
	public HashMap<String, Level> stats = new HashMap<String, Level>();
	int gender;
	int race;
	
	CommonStats(int inGender, int inRace){
		gender = inGender;
		race = inRace;
		randomStats();
	}
	
	private void randomStats(){
		Random random = new Random();
		stats.put("attack", new Level(random.nextInt(3)));
		stats.put("defense", new Level(random.nextInt(3)));
		stats.put("speed", new Level(random.nextInt(3)));
		stats.put("visibility", new Level(random.nextInt(3)));
		stats.put("strength", new Level(random.nextInt(3)));
	}
}
