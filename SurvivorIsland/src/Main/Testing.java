package Main;

import java.util.ArrayList;
import java.util.List;

import Items.Furniture;
import Items.Item;
import Items.Tool;
import Items.ToolType;

public class Testing {

	public static void main(String[] args) {
//		(String inName, double inWeight, boolean inSolid, int inCond, ToolType inToolType, int inReplen, int inStorageCap){
		
		Furniture chest = new Furniture("chest", 12.0, true, 300);
		Tool axe = new Tool("axe", 10.0, false, 100, ToolType.AXE, 100);
		chest.addItemToChest(axe);
		axe = new Tool("axe2qwer", 100, false, 100, ToolType.AXE, 200);
		chest.addItemToChest(axe);
		
		Item holder = null;
		
		holder = chest;
		
		Furniture chest2 = (Furniture) holder;
		
		chest2.printStorage();
		
		holder = chest;
		
		System.out.println(holder.getClass().toString().split("\\.")[1]);
		
		List<Item> items = new ArrayList<>();
		
		items.add(chest);
		
		printList(items);
	}

	private static void printList(List<Item> items) {
		System.out.println(items.get(0).getClass().toString().split("\\.")[1]);
	}

}
