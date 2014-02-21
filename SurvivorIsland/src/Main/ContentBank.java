package Main;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Items.Food;
import Items.Item;
import Items.Tool;
import Items.ToolType;


public class ContentBank {
	public static Image grass, beach, sea, forest, jungle;
		
		
		public static void ContentLoader(){
			BufferedImage bigImg = null;
			
			try {
				bigImg = ImageIO.read(new File("Images/SurvivorTileMap.png"));
				
				sea = bigImg.getSubimage(0, 0, 64, 64);
				beach = bigImg.getSubimage(64, 0, 64, 64);
				grass = bigImg.getSubimage(128, 0, 64, 64);
				forest = bigImg.getSubimage(192, 0, 64, 64);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//furniture, food, tool, item
	public static Item stick = new Item("Stick", .5, false);
	public static Item pebble = new Item("Pebble", .5, false);
	
	public static Food biscuit = new Food("Biscuit", 6, .1, false);
	
	public static Tool woodenSword = new Tool("Wooden Sword", 200, ToolType.SWORD, 1.0, false);
	
	public static Furniture chair = new Furniture();
}
