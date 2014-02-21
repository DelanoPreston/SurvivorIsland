package People;

import java.util.HashMap;
import java.util.Random;

public class CommonStats {
	HashMap<String, Level> stats = new HashMap<String, Level>();
	
	CommonStats(){
		Random random = new Random();
		stats.put("attack", new Level(random.nextInt(3)));
		stats.put("defense", new Level(random.nextInt(3)));
		stats.put("speed", new Level(random.nextInt(3)));
		stats.put("visibility", new Level(random.nextInt(3)));
		stats.put("strength", new Level(random.nextInt(3)));
	}
}
