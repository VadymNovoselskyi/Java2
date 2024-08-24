package m09_spel;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ShipEntity extends Entity{
	private int speed, missileSpeed;
	public MissileEntity missile = null; 
	
	public ShipEntity(Image image, double xPos, double yPos, int speed) {
		super(image, xPos, yPos, speed);
		this.speed = speed;
		this.missileSpeed = 300;
	}
	public ShipEntity(Image image, double xPos, double yPos, int speed, int missileSpeed) {
		super(image, xPos, yPos, speed);
		this.speed = speed;
		this.missileSpeed = missileSpeed;
	}
	
	public void move(long deltaTime) {
		int dx = super.getDirectionX();
		double x = super.getX();
		super.setX(x + dx * speed * (deltaTime / 1000000000.0));
		
		if(missile != null && missile.isActive()){
		    missile.move(deltaTime);    
		}
	}
	
	public boolean tryToFire(){
	    if(missile == null || !missile.isActive()) {
	        missile = new MissileEntity(new ImageIcon(getClass().getResource("/missile.png")).getImage(), super.getX()+13, super.getY(), missileSpeed);
	        missile.setActive(true);
	        return true;
	    }
	    else return false;
	}
	
	@Override
	public void draw(Graphics2D g){
	    if(missile != null && missile.isActive()){
	        missile.draw(g);   
	    }
	    super.draw(g);
	}
}
