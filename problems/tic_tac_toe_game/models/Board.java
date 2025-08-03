package problems.tic_tac_toe_game.models;

import problems.tic_tac_toe_game.GameStateHandler.GameContext;
import problems.tic_tac_toe_game.enums.Symbol;

public class Board {

    private final int rows;
    private final int columns;
    private final Symbol[][] grid;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        grid = new Symbol[rows][columns];

        // Initialize all cells to EMPTY
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = Symbol.EMPTY;
            }
        }
    }

    // Checks if a given position is within the bounds of the board and empty
    public boolean isValidMove(Position pos) {
        int row = pos.getRow();
        int col = pos.getCol();
        return row >= 0 && row < rows &&
                col >= 0 && col < columns &&
                grid[row][col] == Symbol.EMPTY;
    }

    // Places a symbol on the board
    public void makeMove(Position pos, Symbol symbol) {
        grid[pos.getRow()][pos.getCol()] = symbol;
    }

    // Determines the current game state
    public void checkGameState(GameContext context, Player currentPlayer) {
        // Check rows
        for (int i = 0; i < rows; i++) {
            if (grid[i][0] != Symbol.EMPTY && isWinningLine(grid[i])) {
                context.next(currentPlayer, true);
                return;
            }
        }

        // Check columns
        for (int i = 0; i < columns; i++) {
            Symbol[] column = new Symbol[rows];
            for (int j = 0; j < rows; j++) {
                column[j] = grid[j][i];
            }
            if (column[0] != Symbol.EMPTY && isWinningLine(column)) {
                context.next(currentPlayer, true);
                return;
            }
        }

        // Check diagonals
        Symbol[] diagonal1 = new Symbol[Math.min(rows, columns)];
        Symbol[] diagonal2 = new Symbol[Math.min(rows, columns)];
        for (int i = 0; i < Math.min(rows, columns); i++) {
            diagonal1[i] = grid[i][i];
            diagonal2[i] = grid[i][columns - 1 - i];
        }
        if (diagonal1[0] != Symbol.EMPTY && isWinningLine(diagonal1)) {
            context.next(currentPlayer, true);
            return;
        }
        if (diagonal2[0] != Symbol.EMPTY && isWinningLine(diagonal2)) {
            context.next(currentPlayer, true);
            return;
        }

        // Check for draw (no EMPTY cells left)
        boolean isDraw = true;
        for (int i = 0; i < rows && isDraw; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == Symbol.EMPTY) {
                    isDraw = false;
                    break;
                }
            }
        }
        if (isDraw) {
            context.next(null, false);// Treat null player as draw
            return;
        }
    }

    // Helper method to determine if all symbols in a line are the same
    private boolean isWinningLine(Symbol[] line) {
        Symbol first = line[0];
        for (Symbol s : line) {
            if (s != first) {
                return false;
            }
        }
        return true;
    }

    // Prints the board in a clean format
    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            // Print row symbols
            for (int j = 0; j < columns; j++) {
                Symbol symbol = grid[i][j];
                switch (symbol) {
                    case X:
                        System.out.print(" X ");
                        break;
                    case O:
                        System.out.print(" O ");
                        break;
                    case EMPTY:
                    default:
                        System.out.print(" . ");
                        break;
                }
                if (j < columns - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();

            // Print divider line (if not last row)
            if (i < rows - 1) {
                for (int k = 0; k < columns; k++) {
                    System.out.print("---");
                    if (k < columns - 1) {
                        System.out.print("+");
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}