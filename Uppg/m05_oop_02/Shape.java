package m05_oop_02;

import java.awt.Color;
import java.awt.Graphics2D;
import se.egy.graphics.*;

public abstract class Shape implements Drawable{
 	private int xPos, yPos;
 	private Color color;
 	

 	public Shape(int xPos, int yPos, Color color){
      	this.xPos = xPos;
      	this.yPos = yPos;
      	this.color = color;
 	}
 	
 	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public Color getColor() {
		return color;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public abstract void draw(Graphics2D g);
 	
 	public abstract int getArea();
    public abstract int getPerimeter();
}

