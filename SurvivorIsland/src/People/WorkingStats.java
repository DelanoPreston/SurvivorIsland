package People;

import java.util.HashMap;
import java.util.Random;

public class WorkingStats {
	HashMap<String, Integer> stats = new HashMap<String, Integer>();
	
	WorkingStats(){
		Random random = new Random();
		stats.put("gardening", random.nextInt(3));
		stats.put("smelting", random.nextInt(3));
		stats.put("treefelling", random.nextInt(3));
		stats.put("hunting", random.nextInt(3));
		stats.put("trapping", random.nextInt(3));
		stats.put("construction", random.nextInt(3));
		stats.put("crafting", random.nextInt(3));
		stats.put("cooking", random.nextInt(3));
	}
}
