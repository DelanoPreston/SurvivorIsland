package Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import Items.Food;
import Items.Furniture;
import Items.Item;
import Items.Tool;
import Items.ToolType;
import Maps.Map;

public class IOClass {

	/**
	 * public method to be called to get maps
	 * 
	 * @return - returns a Map of the file
	 */
	public Map ReadMap() {
		String[] mapFileData = null;
		try {
			mapFileData = OpenFile("testmap.txt");

			char[][] tempMap = new char[mapFileData.length][mapFileData.length];

			for (int mapFileLine = 0; mapFileLine < mapFileData.length; mapFileLine++) {
				// read map character
				char[] charLine = mapFileData[mapFileLine].toCharArray();
				for (int j = 0; j < mapFileData.length; j++) {

					tempMap[mapFileLine][j] = charLine[j];
				}
			}

			return new Map(tempMap);
		} catch (IOException e) {
			// make a log - file did not load correctly
		}
		return null;
	}

	/**
	 * private method called by ReadFile(String)
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private String[] OpenFile(String fileName) throws IOException {
		String path = System.getProperty("user.dir") + "/mapfile/" + fileName;
		String[] fileStrings = null;

		// try
		// {
		Scanner tempInput = new Scanner(new File(path));
		String stringInput = tempInput.useDelimiter("\\Z").next();
		fileStrings = stringInput.split("\r\n");
		tempInput.close();
		// }
		// catch(IOException e){
		// //file not read correctly
		// }

		return fileStrings;
	}

	/**
	 * saves all the items as separate .properties files
	 * 
	 * @param folder
	 *            - pass in the Items folder
	 * @param inItems
	 *            - the items to be saved
	 */
	public void saveItems(String folder, List<Item> inItems) {
		String outFileLocation = "";
		for (int i = 0; i < inItems.size(); i++) {
			try {
				Properties itemProps = new Properties();
				FileOutputStream out = null;

				itemProps.setProperty("Type", inItems.get(i).getClass().toString().split("\\.")[1]);
				itemProps.setProperty("Name", inItems.get(i).name);
				itemProps.setProperty("Weight", Double.toString(inItems.get(i).weight));
				itemProps.setProperty("Solid", Boolean.toString(inItems.get(i).solid));
				itemProps.setProperty("Condition", Integer.toString(inItems.get(i).condition));

				String switchString = inItems.get(i).getClass().toString().split("\\.")[1];
				switch (switchString) {
				case "Item":
					outFileLocation = folder + "/Item/" + inItems.get(i).name + ".properties";
					break;
				case "Tool":
					Tool tempTool = (Tool) inItems.get(i);
					itemProps.setProperty("Durability", Integer.toString(tempTool.durability));
					itemProps.setProperty("ToolType", tempTool.toolType.toString());
					outFileLocation = folder + "/Tool/" + inItems.get(i).name + ".properties";
					break;
				case "Food":
					Food tempFood = (Food) inItems.get(i);
					itemProps.setProperty("Replenishment", Integer.toString(tempFood.replenishment));
					outFileLocation = folder + "/Food/" + inItems.get(i).name + ".properties";
					break;
				case "Furniture":
					// Furniture tempFurniture = (Furniture) inItems.get(i);
					outFileLocation = folder + "/Furniture/" + inItems.get(i).name + ".properties";
					break;
				default:
					break;
				}
				out = new FileOutputStream(outFileLocation);
				itemProps.store(out, "---No Comment---");
				out.close();
			} catch (IOException e) {
				System.out.println("failed to save: " + inItems.get(i).name);
				// e.printStackTrace();
			}
		}
	}

	// public List<Item> loadItems(final File folder) {
	public HashMap<String, Item> loadItems(final File folder, String itemType) {
		// list of items to be returned
		HashMap<String, Item> tempItems = new HashMap<>();
		List<String> tempItemNames = new ArrayList<>();
		Properties itemProps = new Properties();
		FileInputStream in = null;

		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				HashMap<String, Item> temp = loadItems(fileEntry, itemType);
				tempItems.putAll(temp);
				// property file inside of it
			} else {
				tempItemNames.add(fileEntry.getName());
				// System.out.println(fileEntry.getName());
			}
		}
		for (int i = 0; i < tempItemNames.size(); i++) {
			try {
				String path = folder.getPath() + "/" + tempItemNames.get(i);
				in = new FileInputStream(path);// + ".properties");
				itemProps.load(in);

				Item temp = null;

				// String tempType = itemProps.getProperty("Type");
				String tempName = itemProps.getProperty("Name");
				double tempWeight = Double.parseDouble(itemProps.getProperty("Weight"));
				int tempCondition = Integer.parseInt(itemProps.getProperty("Condition"));
				boolean tempSolid = Boolean.parseBoolean(itemProps.getProperty("Solid"));
				ToolType tempToolType = ToolType.NONE;
				int tempDurability = 0;
				int tempReplen = 0;
				switch (itemType) {
				case "Item":
					temp = new Item(tempName, tempWeight, tempSolid, tempCondition);
					break;
				case "Tool":
					tempToolType = ToolType.valueOf(itemProps.getProperty("ToolType"));
					if (itemProps.containsKey("Durability"))
						tempDurability = Integer.parseInt(itemProps.getProperty("Durability"));
					temp = new Tool(tempName, tempWeight, tempSolid, tempCondition, tempToolType, tempDurability);
					break;
				case "Furniture":
					tempDurability = Integer.parseInt(itemProps.getProperty("Condition"));
					temp = new Furniture(tempName, tempWeight, tempSolid, tempCondition);
					break;
				case "Food":
					tempReplen = Integer.parseInt(itemProps.getProperty("Replenishment"));
					temp = new Food(tempName, tempWeight, tempSolid, tempReplen, tempCondition);
					break;
				default:
					break;
				}

				tempItems.put(temp.name, temp); // add(temp);

				in.close();

			} catch (IOException e) {
				System.out.println("failed to load: " + tempItemNames.get(i));
			} catch(Exception e){
				System.out.println("failed to load: " + tempItemNames.get(i));
				e.printStackTrace();
			}
		}
		
//		List<Item> items = new ArrayList<>(tempItems.values());
//		for(int i = 0; i< tempItems.size(); i++){
//			System.out.println(items.get(i).getClass().toString().split("\\.")[1]);
//		}
		return tempItems;
	}

	public void saveLevel(Level inGamePanel, String saveGameName) {
		try {
			FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + "/SaveGame/" + saveGameName + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(inGamePanel);

			out.close();
			fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Level loadLevel(String name) {
		Level temp = null;
		try {
			FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + "/SaveGame/" + name + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);

			temp = (Level) in.readObject();

			in.close();
			fileIn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
}
