package m10_sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private int port = 9001;
	private ServerSocket serverSocket = null;

	public Server() throws IOException {
		System.out.println("Up and running ...");
		serverSocket = new ServerSocket(port);

		while(true) {
			Socket socket = serverSocket.accept();
			ClientSocketThread clientThread = new ClientSocketThread(socket);
			ClientSocketThread.clientList.add(clientThread);

			clientThread.start();

			System.out.println("Connecting to client: " + ClientSocketThread.clientList.size());
			clientThread.writeToClient("You are connected!");
		}
	}

	public static void main(String[] args) throws IOException {
		new Server();
	}
}