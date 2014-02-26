package Items;

import java.util.ArrayList;
import java.util.List;

public class Furniture extends Item {
	private static final long serialVersionUID = -887641074375066035L;
	List<Item> storage = new ArrayList<>();

	public Furniture(String inName, double inWeight, int inCondition) {
		super(inName, inWeight, inCondition);
	}

	public void addItemToChest(Item inItem) {
		storage.add(inItem);
	}

	public void printStorage() {
		for (int i = 0; i < storage.size(); i++) {
			System.out.println(storage.get(i).name);
		}
	}
}
