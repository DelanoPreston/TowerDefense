
import java.util.ArrayList;
import java.util.List;

public class Level{
	public List<Tower> towers;
	public List<Creep> creeps;
	public Map map;
	int lives;
	int money;
	
	public Level(Map inMap){
		towers = new ArrayList<Tower>();
		creeps = new ArrayList<Creep>();
		map = inMap;
		lives = 10;
		money = 15;
	}
}