package problems.tic_tac_toe_game.GameStrategy;

import problems.tic_tac_toe_game.models.Board;
import problems.tic_tac_toe_game.models.Position;

public interface PlayerStrategy {

    Position makeMove(Board board);
}