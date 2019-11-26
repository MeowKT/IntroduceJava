package nmk;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final boolean log;
    private final Player[] players;

    public Game(final boolean log, final List<Player> players) {
        this.log = log;
        this.players = players.toArray(new Player[players.size()]);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int n = 0, m = 0, k = 0;
        boolean correct = false;
        while (!correct) {
            correct = true;
            System.out.println("Enter n, m, k: ");
            try {
                n = scanner.nextInt();
                m = scanner.nextInt();
                k = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Not a number. Try again");
                scanner.nextLine();
                correct = false;
            }
            if (n < 0 || m < 0 || k <= 0) {
                System.out.println("Not positive number. Try again");
                correct = false;
            }
        }
        scanner.nextLine();
        String ans;
        do {
            this.start(n, m, k);
            System.out.println("Do you want play again?(write YES if you want): ");
            ans = scanner.nextLine().toLowerCase();
        } while (ans.equals("yes"));
    }

    public void start(int n, int m, int k) {
        int result = this.play(new NMKBoard(n, m, k, players.length));
        System.out.println("Game result: " + (result == -2 ? "detected cheater player" : result));
    }

    public int play(Board board) {
        while (true) {
            for (int i = 0; i < players.length; i++) {
                int result = move(board, players[i], i + 1);
                if (result != -1) {
                    return result;
                }
            }
        }
    }

    private int move(final Board board, final Player player, final int number) {
        final Move move = player.move(new ProxyBoard(board.getPosition()), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + number + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + number + " won");
            return number;
        } else if (result == Result.LOSE) {
            log("Player " + number + " has cheats");
            return -2;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
