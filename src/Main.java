import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Create a Minesweeper game with specific dimensions and number of mines
        Minesweeper game = new Minesweeper(3, 3, 10);
        Scanner scanner = new Scanner(System.in);

        // Game loop
        while (!game.getGameOver()) {
            game.displayBoard();
            // Get player input for row, col, and action (reveal or flag)
            System.out.print("Enter row: ");
            int row = scanner.nextInt();
            System.out.print("Enter column: ");
            int col = scanner.nextInt();
            System.out.print("Enter action (reveal/flag/unflag): ");
            String action = scanner.next().toLowerCase();

            // For now, just simulate a move (to be replaced with real player input logic)
            game.playerMove(row, col, action);

            // Check for win or loss conditions
            if (game.checkWin()) {
                game.displayBoard();
                System.out.println("Congratulations! You've won the game.");
                break;
            }
            if (game.checkLoss(row, col)) {
                game.displayBoard();
                System.out.println("Game Over! You hit a mine.");
                game.setGameOver(true);
            }
        }
        scanner.close();
    }
}