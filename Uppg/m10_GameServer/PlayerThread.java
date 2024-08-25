package m10_GameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class PlayerThread extends Thread{
	public Player player;
	public static HashMap<Integer, PlayerThread> deadPlayerMap = new HashMap<>();
	public static HashMap<Integer, PlayerThread> alivePlayerMap = new HashMap<>();

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private boolean dead = false, active = true;

	private static int newPlayerID = 1;

	public PlayerThread(Socket socket) {
		try {
			this.socket = socket;

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized void notifyMyClient(Command cmd, String data){
		out.println(cmd.toString() + "," + data);
	}

	public synchronized void notifyAllClients(Command cmd, String data){
		for (PlayerThread pt : alivePlayerMap.values()) {
			if(pt != this)
				pt.notifyMyClient(cmd, data);
		}
		for (PlayerThread pt : deadPlayerMap.values()) {
			if(pt != this)
				pt.notifyMyClient(cmd, data);
		}
	}

	public void closeConnection() {
		notifyMyClient(Command.DISCONNECT, this.player.toString());
		if(!dead) {
			synchronized(alivePlayerMap){
				alivePlayerMap.remove(this.player.getID());
			}
			notifyAllClients(Command.REMOVE, player.toString());
		}
		else {
			synchronized(alivePlayerMap){
				deadPlayerMap.remove(this.player.getID());
			}
		}
		active = false;
		dead = true;
		out.close();
		try {
			in.close();
			socket.close();
			System.out.println("Disconnecting PlayerID: " +this.player.getID());
		} catch(IOException e) {
			System.out.println("Krasch in.close eller socket.close");
		}
	}

	public void run(){
		try{
			while (!dead) {
				String input = in.readLine();
				exeCommand(input);
			}
			while(active) {
				String input = in.readLine();
				String[] dataList = input.split(",");
				Command cmd = Command.valueOf(dataList[0]);
				if(cmd.equals(Command.DISCONNECT)) {
					closeConnection();
				}
			}
		}catch (Exception e) {
			System.out.println("Krasch i run loop" +e);
			closeConnection();
		}
	}

	public void exeCommand(String data) {
		String[] dataList = data.split(",");
		//		System.out.println(data);

		Command cmd = Command.valueOf(dataList[0]);

		switch(cmd) {
		case NEW_PLAYER:
			player = new Player(newPlayerID++, (int)(Math.random()*700), (int)(Math.random()*500), 10);

			synchronized(alivePlayerMap){
				alivePlayerMap.put(player.getID(), this);
			}
			notifyMyClient(Command.CONNECTED, player.toString());
			notifyAllClients(cmd, player.toString());

			System.out.println("PlayerID: " + player.getID() + " Connected to server");
			break;

		case MOVE:
			int xPos = Integer.valueOf(dataList[2]);
			int yPos = Integer.valueOf(dataList[3]);

			this.player.update(xPos, yPos);
			notifyAllClients(cmd, this.player.toString());
			break;

		case GET_ALL:
			if(alivePlayerMap.size() > 1){
				String playerList = "";
				for (PlayerThread pt : alivePlayerMap.values()) {
					if(pt != this) playerList += "," + pt;
				}
				// playerList.substring(1) Tar bort 1:a ","
				notifyMyClient(Command.UPDATE_ALL, playerList.substring(1));
			} 
			break;

		case HIT:
			break;
		case DEAD:			
			synchronized(alivePlayerMap){
				alivePlayerMap.remove(player.getID());
			}
			synchronized(alivePlayerMap){
				deadPlayerMap.put(player.getID(), this);
			}

			notifyMyClient(Command.REMOVE, this.player.toString());
			notifyAllClients(Command.REMOVE, this.player.toString());
			System.out.println("PlayerID: " + this.player.getID() + " DIED");
			dead = true;
			break;

		case DISCONNECT:
			closeConnection();
			break;

		default:
			break;
		}
	}

	@Override
	public String toString() {
		return player.toString();
	} 
}

