import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Map {
	String levelName = "";
	char[][] mapKey;
	MapTile[][] map;
	List<Point2D> mapPath = new ArrayList<Point2D>();
	HashMap<Character, String> mapImageKey = new HashMap<Character, String>();
	
	public Map(){}
	public Map(String name, char[][] inMapKey, HashMap<Character, String> inMapImageKey, List<Point2D> inMapPath){
		levelName = name;
		mapKey = inMapKey;
		mapPath = inMapPath;
		mapImageKey = inMapImageKey;
		map = SetMapTiles();
	}
	public void Draw(Graphics2D g2D) {
		// draw stuff
		for(int i = 0; i < mapKey.length; i++){
			for(int j = 0; j < mapKey[i].length; j++){
				map[i][j].Draw(g2D);
//				g2D.drawImage(GetTileImage(mapKey[i][j]), j * 32, i * 32, null);
			}
		}
	}
	private MapTile[][] SetMapTiles(){
		MapTile[][] temp = new MapTile[mapKey.length][mapKey.length];
		for(int i = 0; i < temp.length; i++){
			for(int j = 0; j < temp.length; j++){
				temp[i][j] = new MapTile(mapImageKey.get((mapKey[i][j])), new Point2D.Double(i * 32, j * 32));
			}
		}
		return temp;
	}
		public Point2D GetTileAtLocation(int x, int y){
			
			
			return null;
		}
}
