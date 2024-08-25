package m10_GameClient;

import java.io.IOException;
import javax.swing.JOptionPane;

public class GameMain {
	private GameController gameController;
	private String host = "127.0.0.1";

	public GameMain(){
		//host = JOptionPane.showInputDialog("Server Adress");

		try {
			gameController = new GameController(host, 800, 600);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new GameMain();
	}
}
