package problems.tic_tac_toe_game.models;

import problems.tic_tac_toe_game.GameStrategy.PlayerStrategy;
import problems.tic_tac_toe_game.enums.Symbol;

public class Player {

    Symbol symbol;
    PlayerStrategy playerStrategy;

    public  Player(Symbol symbol, PlayerStrategy playerStrategy) {
        this.symbol = symbol;
        this.playerStrategy = playerStrategy;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public PlayerStrategy getPlayerStrategy() {
        return playerStrategy;
    }
}