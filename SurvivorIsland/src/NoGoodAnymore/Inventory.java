package NoGoodAnymore;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	List<Item> items = new ArrayList<>();
	
	public Inventory(){
		
	}
	
	public Inventory(List<Item> inItems){
		for(int i = 0; i < inItems.size(); i++){
			items.add(inItems.get(i));
		}
	}
	
	public void addItem(Item inItem){
		items.add(inItem);
	}
	
	public void addItems(Item inItem, int numOfItems){
		for(int i = 0; i < numOfItems; i++){
			addItem(inItem);
		}
	}
	
	public boolean hasItem(Item checkItem, int numberOfItems){
		int invNum = 0;
		for(int i = 0; i < items.size(); i++){
			if(checkItem == items.get(i)){
				invNum++;
				if(invNum >= numberOfItems){
					return true;
				}
			}
		}
		return false;
	}
	
	public void removeItem(Item itemToRemove){
		for(int i = 0; i < items.size(); i++){
			if(itemToRemove == items.get(i)){
				items.remove(i);
			}
		}
	}
	
	public void removeItems(Item itemToRemove, int numberOfItems){
		for(int i = 0; i < numberOfItems; i++){
			removeItem(itemToRemove);
		}
	}
}
