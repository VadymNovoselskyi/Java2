package m6_spel;

import java.awt.Image;

public class AlienEntity extends Entity{
	private int speed;
	
	public AlienEntity(Image image, double xPos, double yPos, int speed) {
		super(image, xPos, yPos, speed);
		this.speed = speed;
		super.setDirectionY(1);
	}
	
	public void move(long deltaTime) {
		double y = super.getY();		
		super.setY(y + speed * (deltaTime / 1000000000.0));
	}
}