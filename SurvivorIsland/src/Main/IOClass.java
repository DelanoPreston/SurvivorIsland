package Main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import Maps.Map;

public class IOClass {
	
	
	/**public method to be called to get maps
	 * 
	 * @return - returns a Map of the file
	 */
	public Map ReadMap(){
		String[] mapFileData = null;
		try{
			mapFileData = OpenFile("testmap.txt");
			
			char[][] tempMap = new char[mapFileData.length][mapFileData.length];
			
			for(int mapFileLine = 0; mapFileLine < mapFileData.length;){
				//read map character
				for(int j = 0; j < mapFileData.length; j++, mapFileLine++){
					char[] charLine = mapFileData[mapFileLine].toCharArray();
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
}
