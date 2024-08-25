package m10_GameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class PlayerThread extends Thread{
  public Player player;
  public static HashMap<Integer, PlayerThread> playerMap = new HashMap<>();
 
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private volatile boolean quit = false;

  private static int newPlayerID = 1;

  public PlayerThread(Socket socket) throws IOException {
     this.socket = socket;

     in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
     out = new PrintWriter(socket.getOutputStream(), true);
  }

  public synchronized void notifyMyClient(Command cmd, String data){
     out.println(cmd.toString() + "," + data);
  }
 
  public synchronized void notifyAllClients(Command cmd, String data){
     for (PlayerThread pt : playerMap.values()) {
        if(pt != this) // Onödigt skicka tillbaka
           pt.notifyMyClient(cmd, data);
     }
  }

  public void closeConnection(){
     quit = true;
     notifyAllClients(Command.REMOVE, player.toString());
     synchronized(playerMap){
        playerMap.remove(this.player.getID());
     }
     out.close();
     try {
        in.close();
        socket.close();
        System.out.println("Stänger in.close och socket.close");
     } catch(IOException e) {
        System.out.println("Krasch in.close eller socket.close");
     }
  }

  public void run(){
     try{
        while (!quit) {
           String input = in.readLine();
           exeCommand(input);
        }
     }catch (Exception e) {
        System.out.println("Krasch i run loop");
        closeConnection();
     }
  }

  public void exeCommand(String data) {
     String[] dataList = data.split(",");
     System.out.println(data);

     Command cmd = Command.valueOf(dataList[0]);
    
     switch(cmd){
     case NEW_PLAYER:
        player = new Player(newPlayerID++, (int)(Math.random()*700), (int)(Math.random()*500), 10);
       
        synchronized(playerMap){
           playerMap.put(player.getID(), this);
        }
        notifyMyClient(Command.CONNECTED, player.toString());
       
        notifyAllClients(cmd, player.toString());

        System.out.println("PlayerID: " + player.getID() + " Connected to server");
        break;
     case MOVE:
        int xPos = Integer.valueOf(dataList[2]);
        int yPos = Integer.valueOf(dataList[3]);
       
        this.player.update(xPos, yPos);
        notifyAllClients(cmd, player.toString());
       
        break;
     case GET_ALL:
        if(playerMap.size() > 1){
           String playerList = "";
           for (PlayerThread pt : playerMap.values()) {
              if(pt != this) // Onödigt skicka tillbaka
                 playerList += "," + pt;
           }
           // playerList.substring(1) Tar bort 1:a ","
           notifyMyClient(Command.UPDATE_ALL, playerList.substring(1));
        } 
        break;
     case HIT:
        break;
     case DEAD:
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

