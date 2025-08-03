package problems.tic_tac_toe_game.GameStateHandler;

import problems.tic_tac_toe_game.models.Player;

public interface GameState {
    void next(GameContext context, Player player, boolean hasWon);
    boolean isGameOver();
}