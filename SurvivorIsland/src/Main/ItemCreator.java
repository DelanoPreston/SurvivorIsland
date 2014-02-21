package Main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Items.Item;
import Items.ToolType;

public class ItemCreator {
	static String path = "";
	static String fileName = "";
	static List<Item> items = new ArrayList<>();
	static IOClass io = new IOClass();
	
	public static void main(String[] args) {
		String input = "";
		Scanner in = new Scanner(System.in);
		while(true){
//			Tool woodenAxe = new Item("Wooden Axe", 200, ToolType.AXE, 1.0, false);
//			System.out.println(woodenAxe.getClass().toString().split("\\.")[1]);
			input = in.nextLine();
			
			if(input.contains("load")){
				if(input.contains("=")){
					fileName = getInput(input);
					items = io.loadItems(new File(System.getProperty("user.dir") + "/Items/" + fileName));
				}else{
					items = io.loadItems(new File(System.getProperty("user.dir") + "/Items"));
				}
			}else if(input.contains("save")){
				//public Item(String inName,double inWeight, boolean inSolid, int inCond, ToolType inToolType, int inReplen) {
				
				List<Item> itesmsa = new ArrayList<>();
				Item branch = new Item("Branch", .35, false, -1, ToolType.NONE, -1);
				itesmsa.add(branch);
				Item stick = new Item("Stick", .5, false, -1, ToolType.NONE, -1);
				itesmsa.add(stick);
				Item leaf = new Item("Leaf", .02, false, -1, ToolType.NONE, -1);
				itesmsa.add(leaf);
				Item pebble = new Item("Pebble", .25, false, -1, ToolType.NONE, -1);
				itesmsa.add(pebble);
				Item rock = new Item("Rock", 1.00, false, -1, ToolType.NONE, -1);
				itesmsa.add(rock);
				Item wheatSeed = new Item("Wheat Seed", .05, false, -1, ToolType.NONE, -1);
				itesmsa.add(wheatSeed);
				
				//food
				Item apple = new Item("Apple", .2, false, -1, ToolType.NONE, 3);
				itesmsa.add(apple);
				Item biscuit = new Item("Biscuit", .1, false, -1, ToolType.NONE, 6);
				itesmsa.add(biscuit);
				
				//tools
				Item woodenAxe = new Item("Wooden Axe", 1.0, false, 200, ToolType.AXE, -1);
				itesmsa.add(woodenAxe);
				Item woodenBow = new Item("Wooden Bow", 1.0, false, 200, ToolType.BOW, -1);
				itesmsa.add(woodenBow);
				Item woodenBucket = new Item("Wooden Bucket", 1.0, false, 200, ToolType.BUCKET, -1);
				itesmsa.add(woodenBucket);
				Item woodenCrossbow = new Item("Wooden Crossbow", 1.0, false, 200, ToolType.CROSSBOW, -1);
				itesmsa.add(woodenCrossbow);
				Item woodenHoe = new Item("Wooden Hoe", 1.0, false, 200, ToolType.HOE, -1);
				itesmsa.add(woodenHoe);
				Item woodenPickaxe = new Item("Wooden Pickaxe", 1.0, false, 200, ToolType.PICKAXE, -1);
				itesmsa.add(woodenPickaxe);
				Item woodenRake = new Item("Wooden Rake", 1.0, false, 200, ToolType.RAKE, -1);
				itesmsa.add(woodenRake);
				Item woodenSword = new Item("Wooden Sword", 1.0, false, 200, ToolType.SWORD, -1);
				itesmsa.add(woodenSword);
				
				//furniture
				Item woodenStool = new Item("Wooden Stool", 3.0, true, 150, ToolType.NONE, -1);
				itesmsa.add(woodenStool);
				Item woodenTable = new Item("Wooden Table", 5.0, true, 150, ToolType.NONE, -1);
				itesmsa.add(woodenTable);
				
				
				
				
				
				io.saveRecipes(itesmsa, new File(System.getProperty("user.dir") + "/Items"));
				fileName = "";
				items.clear();
			}else if(input.contains("new file")){
				fileName = getInput(input);
				items.clear();
			}
			
			
			if(input.equalsIgnoreCase("quit"))
				break;
		}
		in.close();
	}
	static String getInput(String input){
		String[] temp = input.split("=");
		return temp[1];
	}
//	public static Boolean saveRecipes(List<Item> inItems){
//		for(int i = 0; i < items.size(); i++){
//			Properties recipeProps = new Properties();
//			FileOutputStream out = null;
//			recipeProps.setProperty("Name" , items.get(i).name);
//			recipeProps.setProperty("Condition" , Integer.toString(items.get(i).condition));
//			recipeProps.setProperty("Instruction" , Double.toString(items.get(i).weight));
//			
//			try {
//				File filePath = new File(path);
//				if(!filePath.exists())
//					filePath.mkdirs();
//				out = new FileOutputStream(new File(path + "\\" + items.get(i).name + ".prop"));
//				recipeProps.store(out, "---No Comment---");
//				out.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//				return false;
//			}
//		}
//		return true;
//	}
//	
//	public static List<Item> loadItems(String fileName){
//		List<Item> tempItems = new ArrayList<Item>();
////		Properties itemProps = new Properties();
////		FileInputStream in;
//		
////		try{
////			for(int i = 0; i < recipesToLoad.size(); i++){
////				in = new FileInputStream("Recipes/" + recipesToLoad.get(i) + ".prop");
////				itemProps.load(in);
////				
////				String t = itemProps.getProperty("Name");
////				String[] temp1 = t.split("\\|");
////				t = itemProps.getProperty("Condition");
////				String[] tempIngr = t.split("\\|");
////				t = itemProps.getProperty("Instruction");
////				String[] tempInst = t.split("\\|");
////				
////				RecipeType[] tempType = new RecipeType[temp1.length];
////				for(int j = 0; j < temp1.length; j++){
////					tempType[j] = RecipeType.valueOf(temp1[j]);
////				}
////				
////				Item temp = new Item(recipesToLoad.get(i), tempIngr, tempInst, tempType);
////				tempItems.add(temp);
////				
////				in.close();
////			}
////			
////		}catch(IOException e){
////			e.printStackTrace();
////			return null;
////		}
//		return tempItems;
//	}
}
