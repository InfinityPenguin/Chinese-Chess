package generator;
import java.io.IOException;

class GameLauncher {
	public static void main(String[] args) throws IOException { 
			Game game = new Game();
			while (true) {
				game.Initialize();
				game.play();
				if (!Utility.ask("Play again?")) break;
			}
			System.out.println("Thanks for playing!");
	}
}
