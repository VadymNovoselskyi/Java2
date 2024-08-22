package m6_spel;

import java.awt.Image;

public class MissileEntity extends Entity {
	private boolean active;

    public MissileEntity(Image image, double xPos, double yPos, int speed) {
        super(image, xPos, yPos, speed);
        super.setDirectionY(-1);
        active = false;
    }

    @Override
    public void move(long deltaTime) {
		double y = super.getY();	
		int dy = super.getDirectoinY();
		super.setY(y + dy * super.getSpeed() * (deltaTime / 1000000000.0));
    }

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}

