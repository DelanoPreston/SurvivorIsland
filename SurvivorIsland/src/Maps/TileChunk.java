package Maps;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;

import javax.swing.ImageIcon;

import Main.ContentBank;
import Main.Location;

public class TileChunk {
	MapTile[][] tiles;
	BufferedImage chunkImage;
	Location chunkLoc;
	boolean updateChunk;
	
	public int getWidth(){
		return tiles[0].length;
	}
	
	public int getHeight(){
		return tiles.length;
	}
	
	public Location getChunkLoc(){
		return chunkLoc;
	}
	
	public MapTile getTileFromMap(int x, int y){
		return tiles[x - chunkLoc.getTileX()][y - chunkLoc.getTileY()];
	}
	
	public MapTile getTileFromChunk(int x, int y){
		return tiles[x][y];
	}
	
	public TileChunk(MapTile[][] mapChunk, Location chunkLocation){
		tiles = mapChunk;
		chunkLoc = chunkLocation;
		chunkImage = toBufferedImage(tiles);
	}
	
	public void updateImage(){
		if(updateChunk)
			chunkImage = toBufferedImage(tiles);
	}
	
	public void paintComponent(Graphics2D g2D){
		g2D.drawImage(chunkImage, null, chunkLoc.getMapX(), chunkLoc.getMapY());
	}
	
	public BufferedImage toBufferedImage(MapTile[][] chunkTiles){
		BufferedImage tempBImage = null;
		for(int y = 0; y < chunkTiles.length; y++){
			for(int x = 0; x < chunkTiles[0].length; x++){
				Image image = ContentBank.landTiles[chunkTiles[y][x].imageKey];
				
				if (image instanceof BufferedImage) {
			        return (BufferedImage)image;
			    }

			    // This code ensures that all the pixels in the image are loaded
			    image = new ImageIcon(image).getImage();

			    // Determine if the image has transparent pixels; for this method's
			    // implementation, see Determining If an Image Has Transparent Pixels
			    boolean hasAlpha = hasAlpha(image);

			    // Create a buffered image with a format that's compatible with the screen
//			    BufferedImage tempBImage = null;
			    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    try {
			        // Determine the type of transparency of the new buffered image
			        int transparency = Transparency.OPAQUE;
			        if (hasAlpha) {
			            transparency = Transparency.BITMASK;
			        }

			        // Create the buffered image
			        GraphicsDevice gs = ge.getDefaultScreenDevice();
			        GraphicsConfiguration gc = gs.getDefaultConfiguration();
			        tempBImage = gc.createCompatibleImage(
			            image.getWidth(null), image.getHeight(null), transparency);
			    } catch (HeadlessException e) {
			        // The system does not have a screen
			    }

			    if (tempBImage == null) {
			        // Create a buffered image using the default color model
			        int type = BufferedImage.TYPE_INT_RGB;
			        if (hasAlpha) {
			            type = BufferedImage.TYPE_INT_ARGB;
			        }
			        tempBImage = new BufferedImage(getWidth() * ContentBank.tileSize, getHeight() * ContentBank.tileSize, type);
			    }

			    // Copy image to buffered image
			    Graphics g = tempBImage.createGraphics();

			    // Paint the image onto the buffered image
			    g.drawImage(image, x * ContentBank.tileSize, y * ContentBank.tileSize, null);
			    g.dispose();
			}
		}
		
		

	    return tempBImage;
	}
	
	// This method returns true if the specified image has transparent pixels
	public static boolean hasAlpha(Image image) {
	    // If buffered image, the color model is readily available
	    if (image instanceof BufferedImage) {
	        BufferedImage bimage = (BufferedImage)image;
	        return bimage.getColorModel().hasAlpha();
	    }

	    // Use a pixel grabber to retrieve the image's color model;
	    // grabbing a single pixel is usually sufficient
	     PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
	    try {
	        pg.grabPixels();
	    } catch (InterruptedException e) {
	    }

	    // Get the image's color model
	    ColorModel cm = pg.getColorModel();
	    return cm.hasAlpha();
	}
}
