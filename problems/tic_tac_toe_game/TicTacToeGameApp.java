package problems.tic_tac_toe_game;

import problems.tic_tac_toe_game.GameStrategy.HumanPlayerStrategy;
import problems.tic_tac_toe_game.GameStrategy.PlayerStrategy;
import problems.tic_tac_toe_game.controller.BoardGames;
import problems.tic_tac_toe_game.controller.GameController.TicTacToeGame;

public class TicTacToeGameApp {

    public static void main(String[] args) {

        /*
            flow of game
             -> grid will be of 3 * 3
             -> two players will play the game
             -> they will take alternating turns
             -> will play until one of them wins or game ends in a draw.

            core entities
            -> Board
            -> Player
            -> Position/Cell
            -> Symbol (X, 0, Empty) (Enum)

         */

        PlayerStrategy playerXStrategy = new HumanPlayerStrategy("Player X");
        PlayerStrategy playerOStrategy = new HumanPlayerStrategy("Player O");
        BoardGames game = new TicTacToeGame(playerXStrategy, playerOStrategy, 3, 3);
        game.play();
    }
}