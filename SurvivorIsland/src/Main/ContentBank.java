package Main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Items.Item;

public class ContentBank {
	public static Image beach;
	public static Image sea;
	public static Image forest;
	public static Image jungle;
	public static Image survivorM1;
	public static Image survivorM2;
	public static Image survivorM3;
	public static Image survivorW1;
	public static Image survivorW2;
	public static Image survivorW3;

	public static Image woodenAxe;

	public static HashMap<String, Item> items = new HashMap<>();
	public static HashMap<String, Item> tools = new HashMap<>();
	public static HashMap<String, Item> food = new HashMap<>();
	public static HashMap<String, Item> furniture = new HashMap<>();

	public static void ContentLoader() {
		loadImages();
		loadItems();
		loadAnimals();
	}

	private static void loadAnimals() {
		// // animals
		// Animal pig1 = new Animal("Pig", 20, 80.0, true);
		// TameAnimal pig = new TameAnimal("Pig", 20, 80.0, true);
		// TameAnimal sheep = new TameAnimal("Sheep", 20, 50.0, true);
		// WildAnimal deer = new WildAnimal("Deer", 20, 60.0, true);
		// WildAnimal elk = new WildAnimal("Elk", 30, 90.0, true);
		// WildAnimal moose = new WildAnimal("Moose", 50, 150.0, true);
		// WildAnimal bear = new WildAnimal("Bear", 75, 140.0, true);
	}

	private static void loadImages() {
		BufferedImage bigImg = null;

		try {
			bigImg = ImageIO.read(new File("Images/SurvivorTileMap1.png"));

			sea = bigImg.getSubimage(0, 0, 64, 64);
			beach = bigImg.getSubimage(64, 0, 64, 64);
			forest = bigImg.getSubimage(0, 64, 64, 64);
			jungle = bigImg.getSubimage(64, 64, 64, 64);

			bigImg = ImageIO.read(new File("Images/oie_transparent.png"));

			survivorM1 = bigImg.getSubimage(0, 0, 16, 32);
			survivorM2 = bigImg.getSubimage(0, 32, 16, 32);
			survivorM3 = bigImg.getSubimage(0, 64, 16, 32);
			survivorW1 = bigImg.getSubimage(0, 96, 16, 32);
			survivorW2 = bigImg.getSubimage(0, 128, 16, 32);
			survivorW3 = bigImg.getSubimage(0, 160, 16, 32);

			bigImg = ImageIO.read(new File("Images/tools1.png"));

			woodenAxe = bigImg.getSubimage(0, 0, 16, 16);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void loadItems() {
		IOClass io = new IOClass();
		items = io.loadItems(new File(System.getProperty("user.dir") + "/Items/Item"), "Item");
		tools = io.loadItems(new File(System.getProperty("user.dir") + "/Items/Tool"), "Tool");
		food = io.loadItems(new File(System.getProperty("user.dir") + "/Items/Food"), "Food");
		furniture = io.loadItems(new File(System.getProperty("user.dir") + "/Items/Furniture"), "Furniture");
	}

}
