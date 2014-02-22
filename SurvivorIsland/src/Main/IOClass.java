package Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import Items.Item;
import Items.ToolType;
import Maps.Map;

public class IOClass {
	public String path = "";
	
	/**public method to be called to get maps
	 * 
	 * @return - returns a Map of the file
	 */
	public Map ReadMap(){
		String[] mapFileData = null;
		try{
			mapFileData = OpenFile("testmap.txt");
			
			char[][] tempMap = new char[mapFileData.length][mapFileData.length];
			
			for(int mapFileLine = 0; mapFileLine < mapFileData.length; mapFileLine++){
				//read map character
				char[] charLine = mapFileData[mapFileLine].toCharArray();
				for(int j = 0; j < mapFileData.length; j++){
					
					tempMap[mapFileLine][j] = charLine[j];
				}
			}
			
			return new Map(tempMap);
		}catch(IOException e){
			//make a log - file did not load correctly
		}
		return null;
	}
	
	/**private method called by ReadFile(String)
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private String[] OpenFile(String fileName) throws IOException{
		String path = System.getProperty("user.dir") + "/mapfile/" + fileName;
		String[] fileStrings = null;
		
//		try
//		{
		Scanner tempInput = new Scanner(new File(path));
		String stringInput = tempInput.useDelimiter("\\Z").next();
		fileStrings = stringInput.split("\r\n");
		tempInput.close();
//		}
//		catch(IOException e){
//			//file not read correctly
//		}
		
		return fileStrings;
	}
	
	public Boolean saveRecipes(List<Item> inItems, String savelocation){
		for(int i = 0; i < inItems.size(); i++){
			Properties recipeProps = new Properties();
			FileOutputStream out = null;
			
//			String itemType = inItems.get(i).getClass().toString().split("\\.")[1];
//			recipeProps.setProperty("Type" , itemType);
			recipeProps.setProperty("Name" , inItems.get(i).name);
			recipeProps.setProperty("Weight" , Double.toString(inItems.get(i).weight));
			recipeProps.setProperty("Solid" , Boolean.toString(inItems.get(i).solid));
			recipeProps.setProperty("Condition" , Integer.toString(inItems.get(i).condition));
			recipeProps.setProperty("ToolType" , inItems.get(i).toolType.toString());
			recipeProps.setProperty("Replenishment" , Integer.toString(inItems.get(i).replen));
			
			try {
				File filePath = new File(path);
				if(!filePath.exists())
					filePath.mkdirs();
				out = new FileOutputStream(new File(savelocation + inItems.get(i).name + ".properties"));
				recipeProps.store(out, "---No Comment---");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public List<Item> loadItems(final File folder){
		List<Item> tempItems = new ArrayList<>();
		List<String> tempItemNames = new ArrayList<>();
		Properties itemProps = new Properties();
		FileInputStream in;
		
		for(final File fileEntry : folder.listFiles()){
			if(fileEntry.isDirectory()){
				loadItems(fileEntry);//this will run this same method to get property file inside of it
			}else{
				tempItemNames.add(fileEntry.getName());
//				System.out.println(fileEntry.getName());
			}
		}
		for(int i = 0; i < tempItemNames.size(); i++){
			try{
				
				in = new FileInputStream("Recipes/" + tempItemNames.get(i));
				itemProps.load(in);
				
//				String tempType = itemProps.getProperty("Type");
				String tempName = itemProps.getProperty("Name");
				double tempWeight = Double.parseDouble(itemProps.getProperty("Weight"));
				boolean tempSolid = Boolean.parseBoolean(itemProps.getProperty("Solid"));
				int tempDurability = Integer.parseInt(itemProps.getProperty("Condition"));
				ToolType tempToolType = ToolType.valueOf(itemProps.getProperty("ToolType"));
				int tempReplen = Integer.parseInt(itemProps.getProperty("Replenishment"));
				
				Item temp = new Item(tempName, tempWeight, tempSolid, tempDurability, tempToolType, tempReplen);
				
//				switch(tempType){
//				case "Item":
//					temp = new Item(tempName, tempWeight, tempSolid);
//					break;
//				case "Tool":
//					temp = new Tool(tempName, tempDurability, tempToolType, tempWeight, tempSolid);
//					break;
//				case "Furniture":
//					temp = new Furniture(tempName, tempDurability, tempWeight, tempSolid);
//					break;
//				case "Food":
//					
//					temp = new Food(tempName, tempReplen, tempWeight, tempSolid);
//					break;
//				default:
//					temp = new Item(tempName, tempWeight, tempSolid);
//					break;
//				}
				
				tempItems.add(temp);
				
				in.close();
				
				
			}catch(IOException e){
				System.out.println("failed to load" + tempItemNames.get(i));
//				e.printStackTrace();
//				return null;
			}
		}
		
		return tempItems;
	}
}
