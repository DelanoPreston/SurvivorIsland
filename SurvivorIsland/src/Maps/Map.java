package Maps;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import Main.ContentBank;
import NoGoodAnymore.Entityz;
import NoGoodAnymore.WallEntity;

public class Map extends JComponent implements TileBasedMap {
	private static final long serialVersionUID = -1719918279039504911L;
	// TileChunk[][] chunks;
	ChunkImage[][] chunkImages;
	char[][] charMap;
	MapTile[][] map;
	private boolean[][] visited;
	int tileWidth = ContentBank.tileSize;
	int tileHeight = ContentBank.tileSize;

	public Map(char[][] mapKey) {
		map = createMap(mapKey);
		chunkImages = createChunkImages(map);
		visited = new boolean[getHeight()][getWidth()];
	}

	public void placeWall(WallEntity wall) {
		int x = wall.getTileX();
		int y = wall.getTileY();
		map[y][x].setEntity(wall);

		// tells the chunkImage that this wall piece is placed in to update
		setChunkImageUpdate(wall.getTileX(), wall.getTileY());

		// updates the wall that was just placed (image update)
		updateWall(wall);

		if (map[y - 1][x].getEntity() != null) {
			// updates the wall that is above the one that was just placed (image update)
			updateWall((WallEntity) map[y - 1][x].getEntity());
			if ((wall.getY() - ContentBank.tileSize) - 1 != y)
				setChunkImageUpdate(wall.getTileX(), wall.getTileY() - 1);
		}
		if (map[y][x + 1].getEntity() != null) {
			// updates the wall that is above the one that was just placed (image update)
			updateWall((WallEntity) map[y][x + 1].getEntity());
			if ((wall.getX() - ContentBank.tileSize) + 1 != y)
				setChunkImageUpdate(wall.getTileX() + 1, wall.getTileY());
		}
		if (map[y + 1][x].getEntity() != null) {
			// updates the wall that is above the one that was just placed (image update)
			updateWall((WallEntity) map[y + 1][x].getEntity());
			if ((wall.getY() - ContentBank.tileSize) + 1 != y)
				setChunkImageUpdate(wall.getTileX(), wall.getTileY() + 1);
		}
		if (map[y][x - 1].getEntity() != null) {
			// updates the wall that is above the one that was just placed (image update)
			updateWall((WallEntity) map[y][x - 1].getEntity());
			if ((wall.getX() - ContentBank.tileSize) - 1 != y)
				setChunkImageUpdate(wall.getTileX() - 1, wall.getTileY());
		}
	}

	public void updateWall(WallEntity wall) {
		int x = wall.getTileX();
		int y = wall.getTileY();
		boolean[] wallCheck = new boolean[4];

		if (map[y - 1][x].getEntity() != null)
			if (map[y - 1][x].getEntity().getName().equalsIgnoreCase("wall"))
				wallCheck[0] = true;
		if (map[y][x + 1].getEntity() != null)
			if (map[y][x + 1].getEntity().getName().equalsIgnoreCase("wall"))
				wallCheck[1] = true;
		if (map[y + 1][x].getEntity() != null)
			if (map[y + 1][x].getEntity().getName().equalsIgnoreCase("wall"))
				wallCheck[2] = true;
		if (map[y][x - 1].getEntity() != null)
			if (map[y][x - 1].getEntity().getName().equalsIgnoreCase("wall"))
				wallCheck[3] = true;

		wall.updateKey(wallCheck);
	}

	public void update() {
		for (int y = 0; y < chunkImages.length; y++) {
			for (int x = 0; x < chunkImages[0].length; x++) {
				if (chunkImages[y][x].getUpdate())
					chunkImages[y][x].setImage(toBufferedImage(x * tileWidth, y * tileHeight));
			}
		}
	}

	public void paintComponent(Graphics2D g2D) {
		for (int y = 0; y < chunkImages.length; y++) {
			for (int x = 0; x < chunkImages[0].length; x++) {
				// chunks[x][y].paintComponent(g2D);
				g2D.drawImage(chunkImages[y][x].getImage(), x * tileWidth * ContentBank.tileSize, y * tileHeight * ContentBank.tileSize, null);
			}
		}
	}

	private ChunkImage[][] createChunkImages(MapTile[][] mapKey) {
		int height;
		int width;
		if (mapKey.length % tileHeight > 0)
			height = mapKey.length / tileHeight + 1;
		else
			height = mapKey.length / tileHeight;
		if (mapKey[0].length % tileWidth > 0)
			width = mapKey[0].length / tileWidth + 1;
		else
			width = mapKey[0].length / tileWidth;
		ChunkImage[][] temp = new ChunkImage[height][width];

		for (int yStart = 0; yStart < temp.length; yStart++) {
			for (int xStart = 0; xStart < temp[0].length; xStart++) {
				temp[yStart][xStart] = new ChunkImage(toBufferedImage(xStart * tileWidth, yStart * tileHeight));

				System.out.println("createChunkImages: " + xStart + "," + yStart);
			}
		}
		return temp;
	}

	private MapTile[][] createMap(char[][] mapKey) {
		charMap = mapKey;
		MapTile[][] temp = new MapTile[mapKey.length][mapKey[0].length];

		double seaCost = 1000;
		double landCost = 15;
		for (int y = 0; y < mapKey.length; y++) {
			for (int x = 0; x < mapKey[0].length; x++) {
				double[] tempLocation = new double[2];
				tempLocation[0] = (x * ContentBank.tileSize);
				tempLocation[1] = (y * ContentBank.tileSize);
				if (mapKey[y][x] == 's')
					seaCost = 1;
				else
					seaCost = 1000;
				switch (mapKey[y][x]) {
				case 's':
					landCost = 35.0;
					break;
				case 'j':
					landCost = 2.0;
					break;
				case 'f':
					landCost = 1.0;
					break;
				case 'b':
					landCost = 1.2;
					break;
				case 'm':
					landCost = 50.0;
					break;
				default:
					landCost = 1000;
					break;
				}

				temp[y][x] = new MapTile(mapKey[y][x], tempLocation, landCost, seaCost);
			}
		}

		// for (int y = 0; y < temp.length; y++) {
		// for (int x = 0; x < temp[0].length; x++) {
		// System.out.print(mapKey[y][x]);
		// }
		// System.out.println();
		// }
		return temp;
	}

	public BufferedImage toBufferedImage(int xLoc, int yLoc) {
		// Create a buffered image with a format that's compatible with the screen
		BufferedImage tempBImage = null;
		for (int y = yLoc; y < yLoc + tileHeight; y++) {
			for (int x = xLoc; x < xLoc + tileWidth; x++) {
				int index = map[y][x].imageKey;
				Image image = ContentBank.landTiles[index];

				// This code ensures that all the pixels in the image are loaded
				image = new ImageIcon(image).getImage();

				// Determine if the image has transparent pixels; for this method's
				// implementation, see Determining If an Image Has Transparent Pixels
				boolean hasAlpha = hasAlpha(image);

				if (tempBImage == null) {
					// Create a buffered image using the default color model
					int type = BufferedImage.TYPE_INT_RGB;
					if (hasAlpha) {
						type = BufferedImage.TYPE_INT_ARGB;
					}
					tempBImage = new BufferedImage(tileWidth * ContentBank.tileSize, tileHeight * ContentBank.tileSize, type);
				}

				// Copy image to buffered image
				Graphics g = tempBImage.createGraphics();

				// Paint the image onto the buffered image
				g.drawImage(image, (x - xLoc) * ContentBank.tileSize, (y - yLoc) * ContentBank.tileSize, null);

				// g.drawRect((x - xLoc) * ContentBank.tileSize, (y - yLoc) * ContentBank.tileSize, (x - xLoc + 1) * ContentBank.tileSize, (y - yLoc + 1) *
				// ContentBank.tileSize);

				if (map[y][x].getEntity() != null) {// tempEntImg != null){
					g.drawImage(ContentBank.woodenWalls[map[y][x].getEntity().getImageKey()], (x - xLoc) * ContentBank.tileSize, (y - yLoc)
							* ContentBank.tileSize, null);
				}

				g.dispose();
			}
		}

		System.out.println(tempBImage.getHeight() + "," + tempBImage.getWidth());

		return tempBImage;
	}

	// This method returns true if the specified image has transparent pixels
	public static boolean hasAlpha(Image image) {
		// If buffered image, the color model is readily available
		if (image instanceof BufferedImage) {
			BufferedImage bimage = (BufferedImage) image;
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

	@Override
	public void pathFinderVisited(int x, int y) {
		visited[x][y] = true;
	}

	@Override
	public boolean blocked(Entityz entity, int x, int y) {
		if (getTile(x, y).entity != null && getTile(x, y).entity.solid)
			return true;
		return false;
	}

	public MapTile getTile(int x, int y) {
		try {
			return map[y][x];
		} catch (Exception e) {
			int i = 0;

			if (i == 0) {

			}
		}
		return null;
	}

	@Override
	public int getWidthInTiles() {
		int temp = getWidth();
		return temp;
	}

	@Override
	public int getHeightInTiles() {
		return getHeight();
	}

	public int getWidth() {
		return map[0].length;
	}

	public int getHeight() {
		return map.length;
	}

	@Override
	public float getCost(Entityz entity, int sx, int sy, int tx, int ty) {
		double tempCost = Math.max(getTile(tx, ty).landTileCost, getTile(sx, sy).landTileCost);
		if (Math.abs(sx - tx) == 1 && Math.abs(sy - ty) == 1)
			return (float) Math.sqrt(tempCost + tempCost);
		return (float) tempCost;
	}

	public void setChunkImageUpdate(int x, int y) {
		chunkImages[y / tileHeight][x / tileWidth].setUpdate(true);
	}
}

// public BufferedImage toBufferedImage(int xLoc, int yLoc) {
// // Create a buffered image with a format that's compatible with the screen
// BufferedImage tempBImage = null;
// for (int y = yLoc; y < yLoc + tileHeight; y++) {
// for (int x = xLoc; x < xLoc + tileWidth; x++) {
// Image image = ContentBank.landTiles[map[y][x].imageKey];
//
// // This code ensures that all the pixels in the image are loaded
// image = new ImageIcon(image).getImage();
//
// // Determine if the image has transparent pixels; for this method's
// // implementation, see Determining If an Image Has Transparent Pixels
// boolean hasAlpha = hasAlpha(image);
//
// // if (map[y][x].getEntity() != null) {
// // BufferedImage tempTestImg = null;
// // GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
// // try {
// // // Determine the type of transparency of the new buffered image
// // int transparency = Transparency.OPAQUE;
// // if (hasAlpha) {
// // transparency = Transparency.BITMASK;
// // }
// //
// // // Create the buffered image
// // GraphicsDevice gs = ge.getDefaultScreenDevice();
// // GraphicsConfiguration gc = gs.getDefaultConfiguration();
// // tempTestImg = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
// // } catch (HeadlessException e) {
// // // The system does not have a screen
// // }
// //
// // Graphics gTemp = tempTestImg.createGraphics();
// //
// // gTemp.drawImage(image, (x - xLoc) * ContentBank.tileSize, (y - yLoc) * ContentBank.tileSize, null);
// // gTemp.drawImage(ContentBank.woodenWalls[map[y][x].getEntity().getImageKey()], (x - xLoc) * ContentBank.tileSize, (y - yLoc) *
// // ContentBank.tileSize, null);
// //
// // gTemp.dispose();
// //
// // image = tempTestImg;
// // }
//
// if (tempBImage == null) {
// // Create a buffered image using the default color model
// int type = BufferedImage.TYPE_INT_RGB;
// if (hasAlpha) {
// type = BufferedImage.TYPE_INT_ARGB;
// }
// tempBImage = new BufferedImage(tileWidth * ContentBank.tileSize, tileHeight * ContentBank.tileSize, type);
// }
//
// // Copy image to buffered image
// Graphics g = tempBImage.createGraphics();
//
// // Paint the image onto the buffered image
// g.drawImage(image, (x - xLoc) * ContentBank.tileSize, (y - yLoc) * ContentBank.tileSize, null);
//
// if (map[y][x].getEntity() != null) {// tempEntImg != null){
// g.drawImage(ContentBank.woodenWalls[map[y][x].getEntity().getImageKey()], (x - xLoc) * ContentBank.tileSize, (y - yLoc)
// * ContentBank.tileSize, null);
// }
//
// g.dispose();
// }
// }
//
// System.out.println(tempBImage.getHeight() + "," + tempBImage.getWidth());
//
// return tempBImage;
// }
