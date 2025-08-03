package problems.tic_tac_toe_game.GameStrategy;

import problems.tic_tac_toe_game.models.Board;
import problems.tic_tac_toe_game.models.Position;

import java.util.Scanner;

public class HumanPlayerStrategy implements PlayerStrategy{

    private Scanner scanner;
    private String playerName;

    public HumanPlayerStrategy(String playerName) {
        this.playerName = playerName;
        this.scanner = new Scanner(System.in);
    }


    @Override
    public Position makeMove(Board board) {
        while (true) {
            System.out.printf(
                    "%s, enter your move (row [0-2] and column [0-2]): ", playerName);
            try{
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                Position move = new Position(row, col);
                if (board.isValidMove(move)) {
                    return move;
                }

                // If the move is invalid, prompts the player to try again.
                System.out.println("Invalid move. Try again.");
            } catch (Exception e) {
                System.out.println(
                        "Invalid input. Please enter row and column as numbers.");
                scanner.nextLine(); // Clear input buffer
            }
        }
    }
}