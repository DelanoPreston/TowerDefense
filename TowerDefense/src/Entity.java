import java.awt.Graphics;
import java.awt.geom.Point2D;

import javax.swing.JComponent;

public class Entity extends JComponent{
	int team;
	double rotation;
	Point2D position;
	boolean isAlive;
	double speed;
	
	public Entity(Point2D inPosition, double inSpeed){
		position = inPosition;
		team = 0;//no team
		isAlive = true;
		speed = inSpeed;
	}
	public Entity(Point2D inPosition, int inTeam){
		position = inPosition;
		team = inTeam;
		isAlive = true;
	}
//	public Entity(Point2D inPosition, float inRotation){
//		position = inPosition;
//		rotation = inRotation;
//		team = 0;
//		isAlive = true;
//	}
	
	public void Update(){
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		
	}
	
	public void Draw(Graphics g){
		
	}
}
