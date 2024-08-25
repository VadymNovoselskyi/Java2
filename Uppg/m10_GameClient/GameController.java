package m10_GameClient;

import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;

import m10_GameServer.Command;

public class GameController extends Thread{
	private GameFrame gameFrame;
	private Communicator communicator;
	private HashMap<Integer,Player> playerMap = new HashMap<>();
	private ClientPlayer me;

	private long lastUpdateTime;
	private boolean gameRunning = true;

	public GameController(GameFrame gameFrame, String host) throws IOException{
		this.gameFrame = gameFrame;

		communicator = new Communicator(this, host, 9001);

	}

	public void closeConnection(){
		communicator.closeConnection();
	}

	public void update(long deltaTime){
		me.setDirectionX(0);
		me.setDirectionY(0);

		if(gameFrame.keyDown.get("right"))
			me.setDirectionX(1);
		if(gameFrame.keyDown.get("left"))
			me.setDirectionX(-1);
		if(gameFrame.keyDown.get("down"))
			me.setDirectionY(1);
		if(gameFrame.keyDown.get("up"))
			me.setDirectionY(-1);

		if(me.update(deltaTime))
			communicator.notifyServer(Command.MOVE, me.toString());
	}


	public synchronized void updatePlayerMap(String data){
		String[] dataList = data.split(",");

		Command cmd = Command.valueOf(dataList[0]);

		int playerID = Integer.valueOf(dataList[1]);
		int xPos = Integer.valueOf(dataList[2]);
		int yPos = Integer.valueOf(dataList[3]);
		int health = Integer.valueOf(dataList[4]);

		switch(cmd){
		case CONNECTED:
			me = new ClientPlayer(playerID,xPos, yPos, health);
			me.setColor(Color.BLUE);
			playerMap.put(playerID, me);
			this.start();

			communicator.notifyServer(Command.GET_ALL, me.toString());
			break;

		case UPDATE_ALL:
			for(int i = 1; i < dataList.length-1; i += 4 ){
				playerID = Integer.valueOf(dataList[i]);
				xPos = Integer.valueOf(dataList[i+1]);
				yPos = Integer.valueOf(dataList[i+2]);
				health = Integer.valueOf(dataList[i+3]);

				playerMap.put(playerID, new Player(playerID,xPos, yPos,health));
			}
			break;

		case MOVE:
			playerMap.get(playerID).update(xPos, yPos);
			break;

		case NEW_PLAYER:
			playerMap.put(playerID, new Player(playerID,xPos, yPos,health));
			break;

		case REMOVE:
			playerMap.remove(playerID);
			break;
		default:
		}
	}

	public void run() {
		lastUpdateTime = System.nanoTime();

		while(gameRunning){
			long deltaTime = System.nanoTime() - lastUpdateTime;
			if(deltaTime > 33333333){
				lastUpdateTime = System.nanoTime();
				update(deltaTime);
				gameFrame.render(playerMap);
			}
		}
	}
}

