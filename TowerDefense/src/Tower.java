import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Tower extends Entity{
	double range;
	double damage;
	int level;
	int timer;
	int fireSpeed;
	String towerType;
	List<Projectile> projectiles = new ArrayList<Projectile>();
	Creep target;
//	Level currentMapLevel;
	BaseGameFunctions bgf = new BaseGameFunctions();
	public GamePanel theMapTemp;
	
	/**
	 * Constructor for Tower
	 * @param inType - type of tower, might remove when polymorphism implemented
	 * @param position - position of towers center
	 * @param inTeam - team the tower belongs to
	 * @param inMapLevel - a reference to the map, for seeing creeps
	 */
	public Tower(String inType, Point2D position, int inTeam, GamePanel inLevel){
		super(position, inTeam);
		towerType = inType;
		level = 0;
		theMapTemp = inLevel;
//		Attack();
		range = 150;
		fireSpeed = 30;
	}
	
	/**
	 * The update method for Tower
	 */
	@Override
	public void Update(){
		if(target == null && theMapTemp.creeps.size() > 0){
			for(int i = 0; i < theMapTemp.creeps.size(); i ++){
				if(theMapTemp.creeps.get(i).isAlive && bgf.GetDistance(this.position, theMapTemp.creeps.get(i).position) < range){
					target = theMapTemp.creeps.get(i);
					timer = fireSpeed;
				}
			}
		}
		else if(target != null){
			timer++;
			rotation = bgf.GetAngleRad(position, target.position);
			if(bgf.GetDistance(this.position, target.position) > range || target.health <= 0.0){
				target = null;
			}
		}
		if(target != null && timer > fireSpeed){
			Attack(target);
			
			timer = timer - fireSpeed;
		}
		for(int i = 0; i < projectiles.size(); i ++){
			if(projectiles.get(i).isAlive)
				projectiles.get(i).Update();
		}
	}
	
	
	/**
	 * the Draw method for Tower
	 */
	@Override
	public void Draw(Graphics g){
		Image temp = ContentBank.standardTower; //Main.arrowTower;
		AffineTransform at = new AffineTransform();
		at.translate(position.getX(), position.getY());//texture.getWidth(null)/2, texture.getHeight(null)/2);
		at.rotate(rotation);
//		at.scale(1.5, 1.5);
		at.translate(-temp.getWidth(null)/2, -temp.getHeight(null)/2);
		Graphics2D g2D = (Graphics2D)g;
		g2D.drawImage(temp, at, null);
		for(int i = 0; i < projectiles.size(); i ++){
			if(projectiles.get(i).isAlive)
				projectiles.get(i).Draw(g);
		}
	}
	
	
	public void Attack(Creep inTarget){
		//check if there are any unused projectile objects
		Boolean didIReuse = false;
		
		for(int i = 0; i < projectiles.size(); i ++)
		{
			if(!projectiles.get(i).isAlive){
				projectiles.get(i).ReuseProjectile(4, position, target);
				didIReuse = true;
				break;
			}
		}
		if(!didIReuse)
		{
			Projectile bullet = new Projectile(4, position, target);
			projectiles.add(bullet);
		}
		
		Effect();
	}
	
	/**
	 * 
	 */
	public void Effect(){
		//set effect on creep
		
	}
}