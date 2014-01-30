import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Projectile extends Entity{
	BaseGameFunctions bgf = new BaseGameFunctions();
	Creep destination;
	Point2D movement;
	
	public Projectile(double inSpeed, Point2D inPosition, Creep inDestination) {
		super(inPosition, inSpeed);
		destination = inDestination;
		// TODO Auto-generated constructor stub
	}
	public void Draw(Graphics g){
		Image temp = ContentBank.arrow; //Main.arrowTower;
		AffineTransform at = new AffineTransform();
		at.translate(position.getX(), position.getY());//texture.getWidth(null)/2, texture.getHeight(null)/2);
		at.rotate(rotation);
//		at.scale(1.5, 1.5);
		at.translate(-temp.getWidth(null)/2, -temp.getHeight(null)/2);
		Graphics2D g2D = (Graphics2D)g;
		g2D.drawImage(temp, at, null);
	}
	public void SetMovement(){
		//double unitLength = bgf.GetDistance(position, path.get(pathProgress));
		double angle = bgf.GetAngleRad(position, destination.position);
		
		double xTemp = -Math.cos(angle) * speed;///(unitLength/3);
		double yTemp = -Math.sin(angle) * speed;///(unitLength/3);
		
		movement = new Point2D.Double(xTemp, yTemp);
	}
	@Override
	public void Update(){
		SetMovement();
		position = new Point2D.Double(position.getX() + movement.getX(), position.getY() + movement.getY());
		rotation = bgf.GetAngleRad(position, destination.position) - Math.PI / 2;
		if(bgf.GetDistance(position, destination.position) < 5.0){
			isAlive = false;
			destination.health -= 2.0;
		}
	}
}