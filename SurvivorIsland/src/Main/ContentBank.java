package Main;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Animals.Animal;
import Animals.TameAnimal;
import Animals.WildAnimal;


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
	//items
//	public static Item branch = new Item("Branch", .35, false);
//	public static Item stick = new Item("Stick", .5, false);
//	public static Item leaf = new Item("Leaf", .02, false);
//	public static Item pebble = new Item("Pebble", .25, false);
//	public static Item rock = new Item("Rock", 1.00, false);
//	public static Item wheatSeed = new Item("Wheat Seed", .05, false);
//	
//	//food
//	public static Food apple = new Food("Apple", 3, .2, false);
//	public static Food biscuit = new Food("Biscuit", 6, .1, false);
//	
//	//tools
//	public static Tool woodenAxe = new Tool("Wooden Axe", 200, ToolType.AXE, 1.0, false);
//	public static Tool woodenBow = new Tool("Wooden Bow", 200, ToolType.BOW, 1.0, false);
//	public static Tool woodenBucket = new Tool("Wooden Bucket", 200, ToolType.BUCKET, 1.0, false);
//	public static Tool woodenCrossbow = new Tool("Wooden Crossbow", 200, ToolType.CROSSBOW, 1.0, false);
//	public static Tool woodenHoe = new Tool("Wooden Hoe", 200, ToolType.HOE, 1.0, false);
//	public static Tool woodenPickaxe = new Tool("Wooden Pickaxe", 200, ToolType.PICKAXE, 1.0, false);
//	public static Tool woodenRake = new Tool("Wooden Rake", 200, ToolType.RAKE, 1.0, false);
//	public static Tool woodenSword = new Tool("Wooden Sword", 200, ToolType.SWORD, 1.0, false);
//	
//	//furniture
//	public static Furniture woodenStool = new Furniture("Wooden Stool", 150, 3.0, true);
//	public static Furniture woodenTable = new Furniture("Wooden Table", 150, 5.0, true);
	
	//animals
	public static Animal pig1 = new Animal("Pig", 20, 80.0, true);
	public static TameAnimal pig = new TameAnimal("Pig", 20, 80.0, true);
	public static TameAnimal sheep = new TameAnimal("Sheep", 20, 50.0, true);
	public static WildAnimal deer = new WildAnimal("Deer", 20, 60.0, true);
	public static WildAnimal elk = new WildAnimal("Elk", 30, 90.0, true);
	public static WildAnimal moose = new WildAnimal("Moose", 50, 150.0, true);
	public static WildAnimal bear = new WildAnimal("Bear", 75, 140.0, true);
}
