package m03_oop;

import java.awt.Color;
import java.awt.Graphics2D;

import se.egy.graphics.*;

public class Circle implements Drawable {
	private int radius;
	private int xPos, yPos;
	private Color color;

	/**
	 * Konstruktor
	 */
	public Circle(int radius, int xPos, int yPos, Color color){ 
		this.radius = radius;
		this.xPos = xPos;
		this.yPos = yPos;
		this.color = color;
	}

	public double getArea(){
		double area = Math.PI * Math.pow(radius, 2);
		return area;
	}

	public double getPerimeter(){
		double perimeter = 2 * Math.PI * radius;
		return perimeter;
	}

	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval(xPos, yPos, radius * 2, radius * 2);
	}
}

