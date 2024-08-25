package m10_sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client extends Thread {
	private String host ="127.0.0.1"; // IP till server
	private int port = 9001; // Samma som server

	private BufferedReader in;
	private PrintWriter out; 

	private Socket socket;

	public Client() throws IOException {
		socket = new Socket(host, port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
	}

	public void writeToServer(String msg){
		out.println(msg);
	}

	public void run(){
		try {
			while(true) {

				String msgFromServer = in.readLine();
				if(msgFromServer == null)
					throw new IOException();

				System.out.println(msgFromServer);
			} 

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(out != null)
				out.close();

			try {
				in.close();
				socket.close();
			} catch (IOException e2) {}
		}
	}

	public static void main(String[] args) {
		try{
			Client client = new Client();
			client.start();

			String msg = null;
			do{
				msg = JOptionPane.showInputDialog("Meddelande till server");
				client.writeToServer(msg);
			} while(!msg.equals("q"));
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "Kunde inte ansluta till servern");
		}
	}
}