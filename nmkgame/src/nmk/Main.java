package nmk;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        final Game game = new Game(false, new HumanPlayer(), new RandomPlayer());

        game.start();
    }
}
