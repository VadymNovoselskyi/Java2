package m10_GameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
   private int port;
   private ServerSocket listener;

   public GameServer(int port) throws IOException{
       this.port = port;
       listener = new ServerSocket(port);

       listenToConnections();
   }

   public void listenToConnections(){
       try {
           while (true) {
               Socket socket = listener.accept();
               new PlayerThread(socket).start();
           }
       }catch (IOException e) {
           e.printStackTrace();
       }finally {
           try {
               listener.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }

   public static void main(String[] args) throws IOException {
       System.out.println("Game server is up & running.");
       new GameServer(9001);
   }
}
