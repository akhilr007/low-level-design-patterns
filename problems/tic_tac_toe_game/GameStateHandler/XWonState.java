package problems.tic_tac_toe_game.GameStateHandler;

import problems.tic_tac_toe_game.models.Player;

public class XWonState implements GameState{

    @Override
    public void next(GameContext context, Player player, boolean hasWon) {

    }

    @Override
    public boolean isGameOver() {
        return true;
    }
}