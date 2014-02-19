package Main;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


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
}
