package People;

import java.util.HashMap;
import java.util.Random;

public class WorkingStats {
	HashMap<String, Level> stats = new HashMap<String, Level>();
	
	WorkingStats(){
		Random random = new Random();
		stats.put("gardening", new Level(random.nextInt(3)));
		stats.put("smelting", new Level(random.nextInt(3)));
		stats.put("treefelling", new Level(random.nextInt(3)));
		stats.put("hunting", new Level(random.nextInt(3)));
		stats.put("trapping", new Level(random.nextInt(3)));
		stats.put("construction", new Level(random.nextInt(3)));
		stats.put("crafting", new Level(random.nextInt(3)));
		stats.put("cooking", new Level(random.nextInt(3)));
	}
}
