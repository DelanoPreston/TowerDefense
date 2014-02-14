import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

public class MapTile {
	String tileType = "";
	Point2D tilePosition;
	Rectangle tileArea = null;
	boolean occupied = false;
	
	public MapTile(String inType, Point2D inPosition){
		tileType = inType;
		tilePosition = inPosition;
//		tileArea = new Rectangle((Point)tilePosition, new Dimension(32, 32));
	}
	public void Draw(Graphics2D g2D){
		g2D.drawImage(GetImage(), (int)tilePosition.getY(), (int)tilePosition.getX(), null);
	}
	public Image GetImage(){
		if(tileType.equalsIgnoreCase("grass")){
			return ContentBank.grass;
		}else if(tileType.equalsIgnoreCase("path")){
			return ContentBank.path;
		}else if(tileType.equalsIgnoreCase("end")){
			return ContentBank.end;
		}else if(tileType.equalsIgnoreCase("start")){
			return ContentBank.start;
		}else{
			return null;
		}
	}
}
