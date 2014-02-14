import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Timer;

//import ImplementLater.FireTower;
//import ImplementLater.IceTower;
//import ImplementLater.PoisonTower;
/**
 * GamePanel class that extends JPanel
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	int timer = 0;
	Timer mainTimer;
	GameFunctions.BaseGameFunctions bgf;
	PopupListener popupListener;
	IOClass fileStuff;
//	Map map;
	Level level;
	public List<Tower> towers = new ArrayList<Tower>();
	public List<Creep> creeps = new ArrayList<Creep>();
	
	/**
	 * Constructor for the GamePanel class that extends JPanel
	 */
	public GamePanel(){
		setFocusable(true);
		
		//base game functions object declaration
		bgf = new GameFunctions.BaseGameFunctions();
		
		//creates the popup menu
		CreatePopupMenu();
		
		//adds the keyboard listener for keyboard input
		addKeyListener(new KeyboardListener());
		
		//makes the file io class, and gets the map data
		fileStuff = new IOClass();
		level = new Level(fileStuff.GetMapAt(1));
//		map = fileStuff.GetMapAt(1);
		
		//timer for updating game every 10 miliseconds
		mainTimer = new Timer(10, new TimerListener());
		mainTimer.start();
	}
	
	/**
	 * CreatePopupMenu class is only called when the gamePanel is created to instantiate the popup menu
	 */
	public void CreatePopupMenu() {
        JMenuItem menuItem;
        
        MenuListener menuListener = new MenuListener();
        
        //Create the popup menu.
        JPopupMenu popup = new JPopupMenu();
        menuItem = new JMenuItem("Tower:Standard");
        menuItem.addActionListener(menuListener);
        popup.add(menuItem);
        
        /*
        menuItem = new JMenuItem("Tower:Fire");
        menuItem.addActionListener(menuListener);
        popup.add(menuItem);
        menuItem = new JMenuItem("Tower:Ice");
        menuItem.addActionListener(menuListener);
        popup.add(menuItem);
        menuItem = new JMenuItem("Tower:Poison");
        menuItem.addActionListener(menuListener);
        popup.add(menuItem);//*/
 
        //Add listener to the text area so the popup menu can come up.
        popupListener = new PopupListener(popup);
        this.addMouseListener(popupListener);
    }
	
	/**
	 * Update Method, Action performed calls this to update game
	 */
	public void Update(){
		Tower tempTowerRemove = null;
		for(Tower t : towers){
			t.Update();
			if(!t.isAlive)
				tempTowerRemove = t;
		}
		towers.remove(tempTowerRemove);
		
		Creep tempCreepRemove = null;
		for(Creep c : creeps){
			c.Update();
			if(!c.isAlive)
				tempCreepRemove = c;
		}
		creeps.remove(tempCreepRemove);
	}
	
	/**
	 * Paint Method, Action performed repaint to paint the game
	 */
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		
		//draws the map
		level.map.Draw(g2D);
		
		//goes through the list of towers and calls the draw method
		for(int i = 0; i < towers.size(); i++){
			towers.get(i).Draw(g);
		}
		
		//goes throught the list of creeps and calls their draw methods
		for(int i = 0; i < creeps.size(); i++){
			creeps.get(i).Draw(g);
		}
	}
	
	/**
	 * NewTower method is a Factory I beleive, where you call it to make a tower and depeding on input it
	 * will make any of the towers by one method call
	 * 
	 * @param inType - the type that the tower will be
	 */
	public void NewTower(ContentBank.TowerType inType){
		Point2D tempPoint2D = null;
		switch(inType){
//		case Fire:
//			tempPoint2D = bgf.CenterTileLocation(popupListener.GetPopupLocation(), 32);
//			Tower tempFireTower = new FireTower("Fiya Towa", tempPoint2D, 1, this);
//			towers.add(tempFireTower);
//			break;
//		case Ice:
//			tempPoint2D = bgf.CenterTileLocation(popupListener.GetPopupLocation(), 32);
//			Tower tempIceTower = new IceTower("Iyace Towa", tempPoint2D, 1, this);
//			towers.add(tempIceTower);
//			break;	
//		case Poison:
//			tempPoint2D = bgf.CenterTileLocation(popupListener.GetPopupLocation(), 32);
//			Tower tempPoisonTower = new PoisonTower("Poysan Towa", tempPoint2D, 1, this);
//			towers.add(tempPoisonTower);
//			break;
		default:
			tempPoint2D = bgf.CenterTileLocation(popupListener.GetPopupLocation(), 32);
			Tower tempTower = new Tower("awesome", tempPoint2D, 1, this);
			towers.add(tempTower);
			break;
		}
	}
	
	/**
	 * TimerListener class, implements ActionListener, this class only calls the update methods that
	 * run for every cycle/scene of the game
	 * 
	 * @author Preston Delano
	 */
	class TimerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Update();
			repaint();
			
			if(timer >= 25){
				Creep tempCreep = new Creep(1.0, level.map.mapPath, 10.0, 1);
				creeps.add(tempCreep);
				timer = 0;
			}
			timer++;
		}
	}
	
	/**
	 * KeyboardListener class, implements ActionListener, this class is used when there is a key press,
	 * release, or type
	 * 
	 * @author Preston Delano
	 *
	 */
	class KeyboardListener implements KeyListener{
		@Override
		public void keyPressed(KeyEvent arg0) {
			
			int key = arg0.getKeyCode();
			
			if(key == KeyEvent.VK_SPACE){
				Creep tempCreep = new Creep(1.0, level.map.mapPath, 10.0, 1);
				creeps.add(tempCreep);
			}
		}
		@Override
		public void keyReleased(KeyEvent arg0) {
			
		}
		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}
	}
	
	/**
	 * MenuListener class, implements ActionListener, this class is used when a menu item is selected,
	 * clicked or other things
	 * 
	 * @author Preston Delano
	 *
	 */
	class MenuListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(arg0.paramString().contains("Standard")){
				NewTower(ContentBank.TowerType.Standard);
			}/*
			if(arg0.paramString().contains("Fire")){
				NewTower(ContentBank.TowerType.Fire);
			}
			else if(arg0.paramString().contains("Ice")){
				NewTower(ContentBank.TowerType.Ice);
			}
			else if(arg0.paramString().contains("Poison")){
				NewTower(ContentBank.TowerType.Poison);
			}//*/
		}
	}
	
	/**
	 * PopupListener class, implements ActionListener, this is called when the user clicks anywhere, this is
	 * only used for right click for the popup at the momment
	 * 
	 * @author Preston Delano
	 *
	 */
	class PopupListener extends MouseAdapter{
		JPopupMenu popup;
		Point2D location = null;
		
	    PopupListener(JPopupMenu popupMenu) {
	        popup = popupMenu;
	    }
	    public Point2D GetPopupLocation(){
			return location;
		}
	    public void mousePressed(MouseEvent e) {
	    	ShowPopup(e);
	    }
	    public void mouseReleased(MouseEvent e) {
	    	ShowPopup(e);
	    }
	    private void ShowPopup(MouseEvent e) {
	        if (e.isPopupTrigger()) {
	            popup.show(e.getComponent(), e.getX(), e.getY());
	            location = new Point2D.Double(e.getX(), e.getY());
	        }
	    }
	}
}
