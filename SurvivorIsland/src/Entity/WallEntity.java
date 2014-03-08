package Entity;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import Main.ContentBank;
import Main.Location;

public class WallEntity extends StructureEntity {
	private static final long serialVersionUID = -8870012075099802649L;
	boolean[] adjWalls = {false, false, false, false};//treat like North, East, South, West
	
	public WallEntity(String inName, Location inLocation, double inWeight, boolean inSolid) {
		super(inName, inLocation, inWeight, inSolid);
	}

	
	public void updateKey(boolean[] adjacentWalls) {
		adjWalls = adjacentWalls;
		if(!adjWalls[0] && !adjWalls[1] && !adjWalls[2] && !adjWalls[3]){
			imageKey = 0;//no adjacent walls
		}else if(adjWalls[0] && !adjWalls[1] && !adjWalls[2] && !adjWalls[3]){
			imageKey = 1;//only a wall to the north
		}else if(!adjWalls[0] && adjWalls[1] && !adjWalls[2] && !adjWalls[3]){
			imageKey = 2;//only a wall to the east
		}else if(!adjWalls[0] && !adjWalls[1] && adjWalls[2] && !adjWalls[3]){
			imageKey = 3;//only a wall to the south
		}else if(!adjWalls[0] && !adjWalls[1] && !adjWalls[2] && adjWalls[3]){
			imageKey = 4;//only a wall to the west
		}else if(adjWalls[0] && adjWalls[1] && !adjWalls[2] && !adjWalls[3]){
			imageKey = 5;//one wall north, and one wall east
		}else if(!adjWalls[0] && adjWalls[1] && adjWalls[2] && !adjWalls[3]){
			imageKey = 6;//one wall east, and one wall south
		}else if(!adjWalls[0] && !adjWalls[1] && adjWalls[2] && adjWalls[3]){
			imageKey = 7;//one wall south, and one wall west
		}else if(adjWalls[0] && !adjWalls[1] && !adjWalls[2] && adjWalls[3]){
			imageKey = 8;//one wall south, and one wall north
		}else if(adjWalls[0] && !adjWalls[1] && adjWalls[2] && !adjWalls[3]){
			imageKey = 9;//one wall north, and one wall south
		}else if(!adjWalls[0] && adjWalls[1] && !adjWalls[2] && adjWalls[3]){
			imageKey = 10;//one wall east, and one wall west
		}else if(adjWalls[0] && adjWalls[1] && adjWalls[2] && !adjWalls[3]){
			imageKey = 11;//walls on all four sides but west
		}else if(!adjWalls[0] && adjWalls[1] && adjWalls[2] && adjWalls[3]){
			imageKey = 12;//walls on all four sides but north
		}else if(adjWalls[0] && !adjWalls[1] && adjWalls[2] && adjWalls[3]){
			imageKey = 13;//walls on all four sides but east
		}else if(adjWalls[0] && adjWalls[1] && !adjWalls[2] && adjWalls[3]){
			imageKey = 14;//walls on all four sides but south
		}else if(adjWalls[0] && adjWalls[1] && adjWalls[2] && adjWalls[3]){
			imageKey = 15;//walls on all four sides
		}
	}

	@Override
	public void paintComponent(Graphics2D g2D) {
		AffineTransform at = g2D.getTransform();
		at.translate(-8, -8);
		g2D.setTransform(at);
		g2D.drawImage(ContentBank.woodenWalls[imageKey], location.getMapX(), location.getMapY(), null);
		at.translate(8, 8);
		g2D.setTransform(at);
	}
}
