package Maps;

import java.awt.image.BufferedImage;

import Main.Location;

public class ChunkImage {
	BufferedImage chunkImage;
	Location location;
	boolean update;
	
	public ChunkImage(BufferedImage img){
		chunkImage = img;
		update = false;
	}
	
	public boolean getUpdate(){
		return update;
	}
	
	public void setUpdate(boolean in){
		update = in;
	}
	
	public BufferedImage getImage(){
		return chunkImage;
	}
	
	public void setImage(BufferedImage img){
		chunkImage = img;
		update = false;
	}
}
