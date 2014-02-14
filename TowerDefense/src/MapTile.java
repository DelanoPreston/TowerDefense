import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

public class MapTile {
	public enum TileType {Grass, Path, Start, End};
	TileType tileType = null;
	Point2D tilePosition;
	Rectangle tileArea = null;
	boolean occupied = false;
	
	public MapTile(String inType, Point2D inPosition){
		tileType = SetTileType(inType);
		tilePosition = inPosition;
//		tileArea = new Rectangle((Point)tilePosition, new Dimension(32, 32));
	}
	public void Draw(Graphics2D g2D){
		g2D.drawImage(GetImage(), (int)tilePosition.getY(), (int)tilePosition.getX(), null);
	}
	private TileType SetTileType(String inType){
		if(inType.equalsIgnoreCase("grass")){
			return TileType.Grass;
		}else if(inType.equalsIgnoreCase("path")){
			return TileType.Path;
		}else if(inType.equalsIgnoreCase("end")){
			return TileType.End;
		}else if(inType.equalsIgnoreCase("start")){
			return TileType.Start;
		}
		return TileType.Grass;
	}
	public Image GetImage(){
		if(tileType == TileType.Grass){
			return ContentBank.grass;
		}else if(tileType == TileType.Path){
			return ContentBank.path;
		}else if(tileType == TileType.End){
			return ContentBank.end;
		}else if(tileType == TileType.Start){
			return ContentBank.start;
		}else{
			return null;
		}
	}
}
