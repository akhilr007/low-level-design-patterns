package problems.tic_tac_toe_game.GameStateHandler;

import problems.tic_tac_toe_game.models.Player;

public class GameContext {

    private GameState currentState;

    public GameContext() {
        this.currentState = new XTurnState();
    }

    public void setState(GameState state) {
        this.currentState = state;
    }

    public void next(Player player, boolean hasWon) {
        currentState.next(this, player, hasWon);
    }

    public boolean isGameOver() {
        return currentState.isGameOver();
    }

    public GameState getCurrentState() {
        return this.currentState;
    }
}