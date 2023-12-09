import java.util.Scanner;

/**
 * Game runner class
 */
public class BattleshipGame {
    public static void main(String[] args) {
        BattleshipGame game = new BattleshipGame();
        game.start();
    }

    /**
     * Game loop
     */
    public void start() {
        do {
            Ocean ocean = new Ocean();
            ocean.placeAllShipsRandomly();

            while (!ocean.isGameOver()) {
                ocean.print();
                int row = getInput("Row", Ocean.ROW);
                int column = getInput("Column", Ocean.COL);
                ocean.shootAt(row, column);
            }

            System.out.printf("\nGame Over! Your score is %d.\n", ocean.getShotsFired());

        } while (wannaPlayAgain());
    }

    /**
     * Get input from user
     *
     * @param name  name of the value, should be either "Row" or "Column"
     * @param range suggest the valid value range
     * @return a number set by user within the range
     */
    private int getInput(String name, int range) {
        while (true) {
            System.out.printf("Input %s (0-%d): ", name, range - 1);
            try {
                Scanner scanner = new Scanner(System.in);
                int i = scanner.nextInt();
                if (i >= 0 && i < range) {
                    return i;
                }
            } catch (Exception ignored) {

            }
            System.out.println("Invalid input, please try again");
        }
    }

    /**
     * @return true if user want to play again
     */
    private boolean wannaPlayAgain() {
        try {
            System.out.print("Play again? (y/n): ");

            Scanner scanner = new Scanner(System.in);
            var input = scanner.next();

            return input.equals("y") || input.equals("Y");
        } catch (Exception ignored) {
            return false;
        }
    }
}
