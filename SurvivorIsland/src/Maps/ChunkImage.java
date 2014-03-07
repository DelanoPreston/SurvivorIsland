package Maps;

import java.awt.image.BufferedImage;

public class ChunkImage {
	BufferedImage chunkImage;
	boolean update;
	
	public ChunkImage(BufferedImage img){
		chunkImage = img;
		update = false;
	}
	
	public BufferedImage getImage(){
		return chunkImage;
	}
	
	public void setImage(BufferedImage img){
		chunkImage = img;
	}
}
