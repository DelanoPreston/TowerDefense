
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Level{
	public List<Tower> towers;
	public List<Creep> creeps;
	public Map map;
	int lives;
	int money;
	
	public Level(){
		towers = new ArrayList<Tower>();
		creeps = new ArrayList<Creep>();
		map = new Map();
		lives = 10;
		money = 15;
	}
	
	public void keyPressed(KeyEvent e){
//		int key = e.getKeyCode();
		
//		if(key == KeyEvent.VK_W){
//			velY = -2;
//		}
	}
	public void keyReleased(KeyEvent e){
//		int key = e.getKeyCode();
//		if(key == KeyEvent.VK_W){
//		velY = -2;
//		}
	}
	
	public void AddTower(){
//		Tower t = new Tower("Missile", new Point(1,1), 1, this);
//		towers.add(t);
	}
}