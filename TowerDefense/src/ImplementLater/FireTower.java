//package ImplementLater;
//import ContentBank;
//import Creep;
//import GamePanel;
//import Projectile;
//import Tower;
//
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.geom.AffineTransform;
//import java.awt.geom.Point2D;
//
//
//public class FireTower extends Tower{
//
//	public FireTower(String inType, Point2D position, int inTeam,GamePanel inLevel) {
//		super(inType, position, inTeam, inLevel);
////		Attack();
//	}
//	
//	/**
//	 * 
//	 */
//	@Override
//	public void Attack(Creep inTarget){
//		//shoot projectile
//		Projectile bullet = new Projectile(4, position, target, "fire");
//		projectiles.add(bullet);
//		
//		Effect();
//				
//////		Projectile bullet = new Projectile();
////		System.out.println("Fire Overriden");
//	}
//	/**
//	 * 
//	 */
//	@Override
//	public void Effect(){
//		
//	}
//	
//	/**
//	 * the Draw method for Tower
//	 */
//	@Override
//	public void Draw(Graphics g){
//		Image temp = ContentBank.fireTower; //Main.arrowTower;
//		AffineTransform at = new AffineTransform();
//		at.translate(position.getX(), position.getY());//texture.getWidth(null)/2, texture.getHeight(null)/2);
//		at.rotate(rotation);
////		at.scale(1.5, 1.5);
//		at.translate(-temp.getWidth(null)/2, -temp.getHeight(null)/2);
//		Graphics2D g2D = (Graphics2D)g;
//		g2D.drawImage(temp, at, null);
//		for(int i = 0; i < projectiles.size(); i ++){
//			projectiles.get(i).Draw(g);
//		}
//	}
//}
