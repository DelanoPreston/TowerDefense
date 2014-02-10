package GameFunctions;
import java.awt.geom.Point2D;


public class BaseGameFunctions {
	
	public BaseGameFunctions(){
		
	}
	
	public double GetDistance (Point2D obj1, Point2D obj2){
		double tempX = obj1.getX() - obj2.getX();
		double tempY = obj1.getY() - obj2.getY();
		
		return Math.sqrt(Math.pow(tempX, 2.0) + Math.pow(tempY, 2.0));
	}
	public double GetAngleRad(Point2D obj1, Point2D obj2){
		double xDiff = obj1.getX() - obj2.getX();
		double yDiff = obj1.getY() - obj2.getY();
		
		return Math.atan2(yDiff, xDiff);
	}
	public double GetAngleDeg(Point2D obj1, Point2D obj2){
		double xDiff = obj1.getX() - obj2.getX();
		double yDiff = obj1.getY() - obj2.getY();
		
		return Math.toDegrees(Math.atan2(yDiff, xDiff));
	}
	public Point2D CenterTileLocation(Point2D location, int tileSize){
		return CenterTileLocation(location.getX(), location.getY(), tileSize);
	}
	public Point2D CenterTileLocation(double x, double y, int tileSize){
		double tempx = x - (x % tileSize);
		double tempy = y - (y % tileSize);
		
		return new Point2D.Double(tempx + 16, tempy + 16);
	}
	
}
