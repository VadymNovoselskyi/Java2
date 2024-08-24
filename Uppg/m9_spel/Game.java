package m9_spel;

public class Game {

	public static void main(String[] args) {
		GameView gv = new GameView(1200, 800, "Game");
		GameController gc = new GameController(gv, 170, 300, 45, 7, 60);
		gc.run();
	}
}


