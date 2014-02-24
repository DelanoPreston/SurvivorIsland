package People;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

public class WorkingStats implements Serializable{
	HashMap<String, Stat> stats = new HashMap<String, Stat>();
	
	WorkingStats(){
		Random random = new Random();
		stats.put("gardening", new Stat(random.nextInt(3)));
		stats.put("smelting", new Stat(random.nextInt(3)));
		stats.put("treefelling", new Stat(random.nextInt(3)));
		stats.put("hunting", new Stat(random.nextInt(3)));
		stats.put("trapping", new Stat(random.nextInt(3)));
		stats.put("construction", new Stat(random.nextInt(3)));
		stats.put("crafting", new Stat(random.nextInt(3)));
		stats.put("cooking", new Stat(random.nextInt(3)));
	}
}
