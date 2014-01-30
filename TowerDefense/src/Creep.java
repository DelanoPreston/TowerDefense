import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Creep extends Entity{
	Point2D movementTemp;
	List<Point2D> path;// = new ArrayList<Point2D>();
	int pathProgress = 0;
	public double health;
	BaseGameFunctions bgf = new BaseGameFunctions();
	
	public Creep(double inSpeed, List<Point2D> inPath, double inHealth, int inTeam){
		super(TileToCoords(inPath.get(0)), inSpeed);//it is starting in the tile location coords, not the map coords
		path = TilesToCoords(inPath);
		health = inHealth;
		//movementTemp = new Point2D.Double(0, 0);
		pathProgress = 1;
		
		
	}
	
	public void ProjectileHitCreep(float damage){
		health = health - damage;
	}
	public int RewardOnDeath(){
		return 0;
	}
	public void SetMovement(){
		//double unitLength = bgf.GetDistance(position, path.get(pathProgress));
		double angle = bgf.GetAngleRad(position, path.get(pathProgress));
		
		double xTemp = -Math.cos(angle) * speed;///(unitLength/3);
		double yTemp = -Math.sin(angle) * speed;///(unitLength/3);
		
		movementTemp = new Point2D.Double(xTemp, yTemp);
	}
	@Override
	public void Update(){
		SetMovement();
		position = new Point2D.Double(position.getX() + movementTemp.getX(), position.getY() + movementTemp.getY());
		
		if(bgf.GetDistance(position, path.get(pathProgress)) < 1.0){
			pathProgress++;
			if(pathProgress >= path.size())
				pathProgress -= path.size();
		}
		rotation = bgf.GetAngleRad(position, path.get(pathProgress));
		
		if(bgf.GetDistance(position, path.get(path.size() - 1)) <= 1){
			//it dies, and take one of your lives away
			isAlive = false;	
		}
		
		if(health <= 0.0){
			//explode
			//reward on death
			isAlive = false;
		}
	}
	@Override
	public void Draw(Graphics g){
		if(isAlive){
			AffineTransform at = new AffineTransform();
			at.translate(position.getX(), position.getY());
			at.rotate(rotation);
			at.translate(-ContentBank.creep.getWidth(null)/2, -ContentBank.creep.getHeight(null)/2);
			Graphics2D g2D = (Graphics2D)g;
			g2D.drawImage(ContentBank.creep, at, null);
		}
	}
	public List<Point2D> TilesToCoords(List<Point2D> inPoint2D){
		List<Point2D> listTemp = new ArrayList<Point2D>();
		for(int i = 0; i < inPoint2D.size(); i++){
			listTemp.add(TileToCoords(inPoint2D.get(i)));
		}
		return listTemp;
	}
	public static Point2D TileToCoords(Point2D inPoint2D){
		double xTemp = ((inPoint2D.getX() - 1.0) * 32.0) +16.0;
		double yTemp = ((inPoint2D.getY() - 1.0) * 32.0) +16.0;
		return new Point2D.Double(xTemp, yTemp);
	}
}