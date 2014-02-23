package Main;
import java.awt.geom.Point2D;
import java.util.Random;


public class BaseGameFunctions {
	public Random random;
	
	public BaseGameFunctions(){
		random = new Random();
	}
	
	public double getDistance (Point2D obj1, Point2D obj2){
		double tempX = obj1.getX() - obj2.getX();
		double tempY = obj1.getY() - obj2.getY();
		
		return Math.sqrt(Math.pow(tempX, 2.0) + Math.pow(tempY, 2.0));
	}
	public double getDistance (double[] obj1, double[] obj2){
		double tempX = obj1[0] - obj2[0];
		double tempY = obj1[1] - obj2[1];
		
		return Math.sqrt(Math.pow(tempX, 2.0) + Math.pow(tempY, 2.0));
	}
	public double getAngleRad(double[] obj1, double[] obj2){
		double xDiff = obj1[0] - obj2[0];
		double yDiff = obj1[1] - obj2[1];
		
		return Math.atan2(yDiff, xDiff);
	}
	public double getAngleRad(Point2D obj1, Point2D obj2){
		double xDiff = obj1.getX() - obj2.getX();
		double yDiff = obj1.getY() - obj2.getY();
		
		return Math.atan2(yDiff, xDiff);
	}
	public double getAngleDeg(Point2D obj1, Point2D obj2){
		double xDiff = obj1.getX() - obj2.getX();
		double yDiff = obj1.getY() - obj2.getY();
		
		return Math.toDegrees(Math.atan2(yDiff, xDiff));
	}
	public Point2D centerTileLocation(Point2D location, int tileSize){
		return centerTileLocation(location.getX(), location.getY(), tileSize);
	}
	public Point2D centerTileLocation(double x, double y, int tileSize){
		double tempx = x - (x % tileSize);
		double tempy = y - (y % tileSize);
		
		return new Point2D.Double(tempx + (tileSize / 2), tempy + (tileSize / 2));
	}
	public double[] getComponentLengths(double[] obj1, double[] obj2, int multiplier){
		double rad = getAngleRad(obj1, obj2);
		
		double[] temp = {Math.cos(rad) * multiplier, Math.sin(rad) * multiplier};
		return temp;
	}
}
