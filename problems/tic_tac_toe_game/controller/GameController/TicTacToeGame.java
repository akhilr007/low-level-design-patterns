package problems.tic_tac_toe_game.controller.GameController;

import problems.tic_tac_toe_game.GameStateHandler.GameContext;
import problems.tic_tac_toe_game.GameStateHandler.GameState;
import problems.tic_tac_toe_game.GameStateHandler.OWonState;
import problems.tic_tac_toe_game.GameStateHandler.XWonState;
import problems.tic_tac_toe_game.GameStrategy.PlayerStrategy;
import problems.tic_tac_toe_game.controller.BoardGames;
import problems.tic_tac_toe_game.enums.Symbol;
import problems.tic_tac_toe_game.models.Board;
import problems.tic_tac_toe_game.models.Player;
import problems.tic_tac_toe_game.models.Position;

public class TicTacToeGame implements BoardGames {

    private Board board;
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private GameContext gameContext;

    public TicTacToeGame(PlayerStrategy XPlayerStrategy,
                         PlayerStrategy OPlayerStrategy,
                         int rows,
                         int cols) {
        this.board = new Board(rows, cols);
        this.playerX = new Player(Symbol.X, XPlayerStrategy);
        this.playerO = new Player(Symbol.O, OPlayerStrategy);
        this.currentPlayer = playerX;
        this.gameContext = new GameContext();
    }

    @Override
    public void play() {
        do {
            board.printBoard();

            Position move = currentPlayer.getPlayerStrategy().makeMove(board);
            board.makeMove(move, currentPlayer.getSymbol());

            board.checkGameState(gameContext, currentPlayer);
            switchPlayer();
        } while (!gameContext.isGameOver());
        announceResult();
    }

    // Alternates the current player after each move.
    // Ensures both players take turns
    private void switchPlayer() {
        currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
    }

    // Displays the outcome of the game based on the final game state.
    private void announceResult() {
        GameState state = gameContext.getCurrentState();
        board.printBoard();
        if (state instanceof XWonState) {
            System.out.println("Player X wins!");
        } else if (state instanceof OWonState) {
            System.out.println("Player O wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}