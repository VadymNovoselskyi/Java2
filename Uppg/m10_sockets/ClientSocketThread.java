package m10_sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientSocketThread extends Thread {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private int clientID;

	public static ArrayList<ClientSocketThread> clientList = new ArrayList<>(10);
	private static int clientCounter;

	public ClientSocketThread(Socket socket) throws IOException {
		this.socket = socket;
		clientID = clientCounter + 1;
		clientCounter++;
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
	} 

	public void run(){
		String msgFromClient = null;

		while(true){
			try {
				msgFromClient = in.readLine();
			} catch (IOException e){}
			
			if(msgFromClient == null || msgFromClient.equals("q")) break;
			
			System.out.println("From Client #" +clientID +": " + msgFromClient);
			writeToAllClients(msgFromClient);
		}
	}

	public void writeToClient(String msg){
		if(msg != null)  // Vägra skicka null
			out.println(msg);
	}

	public void writeToAllClients(String msg){
		for(int i = 0; i < clientList.size(); i++) {
			if(clientID - 1 != i) clientList.get(i).writeToClient("Client #" +clientID +": " +msg);
			else clientList.get(i).writeToClient("You said: " +msg);
		}
	}
}