package m6_spel;

import java.awt.Image;

public class ShipEntity extends Entity{
	private int speed;
	
	public ShipEntity(Image image, double xPos, double yPos, int speed) {
		super(image, xPos, yPos, speed);
		this.speed = speed;
	}
	
	public void move(long deltaTime) {
		int dx = super.getDirectionX();
		double x = super.getX();		
		super.setX(x + dx * speed * (deltaTime / 1000000000.0));
	}
}
