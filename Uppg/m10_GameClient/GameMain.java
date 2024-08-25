package m10_GameClient;

import java.io.IOException;

import javax.swing.JOptionPane;

public class GameMain {
	private GameController gameController = null;
	private GameFrame gameFrame;
	private String host = "127.0.0.1";  // Byt ut!

	public GameMain(){
		gameFrame = new GameFrame();

		host = JOptionPane.showInputDialog("ServerAdress");

		try {
			gameController = new GameController(gameFrame, host);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new GameMain();
	}
}
