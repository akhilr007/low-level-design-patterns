package problems.tic_tac_toe_game.GameStateHandler;

import problems.tic_tac_toe_game.enums.Symbol;
import problems.tic_tac_toe_game.models.Player;

public class XTurnState implements GameState{

    @Override
    public void next(GameContext context, Player player, boolean hasWon) {
        if (hasWon) {
            context.setState(player.getSymbol() == Symbol.X ? new XWonState() : new OWonState());
        }
        else {
            context.setState(new OTurnState());
        }
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}