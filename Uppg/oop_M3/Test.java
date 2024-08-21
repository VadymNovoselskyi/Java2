package oop_M3;

import java.awt.Color;

import se.egy.graphics.*;

public class Test {

	public static void main(String[] args) {
		GameScreen gs = new GameScreen("Rektanglar", 800, 600, false);
//		Drawable[] drawables = new Drawable[4];
//		
//		drawables[0] = new Rectangle(200, 100, 50, 50, Color.green);
//		drawables[1] = new Rectangle(200, 100, 550, 50, Color.red);
//		drawables[2] = new Circle(100, 50, 300, Color.red);
//		drawables[3] = new Circle(100, 550, 300, Color.yellow);		
//		
//		gs.render(drawables);
		
		Rectangle[] rectangles = new Rectangle[2];
		
		rectangles[0] = new Rectangle(200, 100, 50, 50, Color.green);
		rectangles[1] = new Rectangle(200, 100, 550, 50, Color.red);	
		
		gs.render(rectangles);
		
		try{ Thread.sleep(2000);}catch(Exception e){};
		
		rectangles[0].setX(550);
		rectangles[1].setX(50);
		gs.render(rectangles);

	}

}
