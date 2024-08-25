package m10_GameClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import m10_GameServer.Command;


public class Communicator extends Thread{
	private Socket socket;
	private int port;
	private String host;

	private BufferedReader in;
	private PrintWriter out;

	private GameController gameController;

	private volatile boolean  quit = false;

	public Communicator(GameController gameController, String host, int port) throws IOException {
		this.host = host;
		this.port = port;
		this.gameController = gameController;

		this.start();
	}

	public void notifyServer(Command cmd, String data) {
		out.println(cmd.toString() + "," + data);
	}

	public void notifyServer(Command cmd) {
		out.println(cmd.toString());
	}


	public void run() {
		try {
			socket = new Socket(InetAddress.getByName(host), port);

			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			notifyServer(Command.NEW_PLAYER);

			while(!quit) {
				String input = in.readLine();
				gameController.updatePlayerMap(input);
			}
		}catch (IOException e) {
			closeConnection();
		}
	}

	public void closeConnection() {
		quit = true;
		out.close();

		try {
			in.close();
			socket.close();
		}catch (IOException e) {}
	}
}

