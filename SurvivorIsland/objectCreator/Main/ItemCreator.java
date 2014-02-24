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
				
				io.saveRecipes(itesmsa, System.getProperty("user.dir") + "/Items/");
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
