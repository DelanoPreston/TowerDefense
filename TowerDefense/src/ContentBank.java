import java.awt.Image;

import javax.swing.ImageIcon;


public class ContentBank {
//	public static enum TileType {Grass, Path, Start, End};
//	public static enum TowerType {Standard, Poison, Fire, Ice};
//	public static enum Effect {Burn, Slow, Poison};
	
	public static Image end, grass, path, start;
	public static Image standardTower, poisonTower, fireTower, iceTower;
	public static Image arrow, poisonArrow, fireBall, iceBall;
	public static Image creep;
		
		
		public static void ContentLoader(){
			ImageIcon ic;
			ic = new ImageIcon("Images/end.png");
			end = ic.getImage();
			ic = new ImageIcon("Images/grass.png");
			grass = ic.getImage();
			ic = new ImageIcon("Images/path.png");
			path = ic.getImage();
			ic = new ImageIcon("Images/start.png");
			start = ic.getImage();
			
			ic = new ImageIcon("Images/standardTower.png");
			standardTower = ic.getImage();
			ic = new ImageIcon("Images/poisonTower.png");
			poisonTower = ic.getImage();
			ic = new ImageIcon("Images/fireTower.png");
			fireTower = ic.getImage();
			ic = new ImageIcon("Images/iceTower.png");
			iceTower = ic.getImage();
			
			
			ic = new ImageIcon("Images/arrow.png");
			arrow = ic.getImage();
			ic = new ImageIcon("Images/poisonArrow.png");
			poisonArrow = ic.getImage();
			ic = new ImageIcon("Images/fireBall.png");
			fireBall = ic.getImage();
			ic = new ImageIcon("Images/iceBall.png");
			iceBall = ic.getImage();
			
			ic = new ImageIcon("Images/creep.png");
			creep = ic.getImage();
		}
}
