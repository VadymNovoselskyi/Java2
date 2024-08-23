package m9_spel;

public class Game {

   public static void main(String[] args) {
       GameView gv = new GameView(1000, 800,"Game");
       GameController gc = new GameController(gv);
       gc.run();

   }
}


