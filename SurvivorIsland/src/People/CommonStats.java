package People;

import java.util.HashMap;
import java.util.Random;

public class CommonStats {
	HashMap<String, Integer> stats = new HashMap<String, Integer>();
	
	CommonStats(){
		Random random = new Random();
		stats.put("attack", random.nextInt(3));
		stats.put("defense", random.nextInt(3));
		stats.put("speed", random.nextInt(3));
		stats.put("visibility", random.nextInt(3));
	}
}
