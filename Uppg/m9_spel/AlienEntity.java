package m9_spel;

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
		int dy = super.getDirectoinY();
		super.setY(y + dy * speed * (deltaTime / 1000000000.0));
	}
}
