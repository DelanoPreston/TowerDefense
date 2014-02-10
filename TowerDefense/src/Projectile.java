import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Projectile extends Entity{
	GameFunctions.BaseGameFunctions bgf = new GameFunctions.BaseGameFunctions();
	Creep destination;
	Point2D movement;
	String type;
	
	
	public Projectile(double inSpeed, Point2D inPosition, Creep inDestination) {
		super(inPosition, inSpeed);
		destination = inDestination;
		type = "";
		isAlive = true;
	}
	
	public Projectile(double inSpeed, Point2D inPosition, Creep inDestination, String inType) {
		super(inPosition, inSpeed);
		destination = inDestination;
		type = inType;
		isAlive = true;
	}
	
	public void ReuseProjectile(double inSpeed, Point2D inPosition, Creep inDestination){
		position = inPosition;
		speed = inSpeed;
		destination = inDestination;
		type = "";
		isAlive = true;
	}
	
	public void ReuseProjectile(double inSpeed, Point2D inPosition, Creep inDestination, String inType){
		position = inPosition;
		speed = inSpeed;
		destination = inDestination;
		type = inType;
		isAlive = true;
	}
	
	public void Draw(Graphics g){
		Image temp;
		if(type.equalsIgnoreCase("fire"))
			temp = ContentBank.fireBall;
		else if(type.equalsIgnoreCase("ice"))
			temp = ContentBank.iceBall;
		else if(type.equalsIgnoreCase("poison"))
			temp = ContentBank.poisonArrow;
		else
			temp = ContentBank.arrow;
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