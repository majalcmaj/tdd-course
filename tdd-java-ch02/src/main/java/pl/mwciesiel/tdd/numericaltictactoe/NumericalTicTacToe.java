package pl.mwciesiel.tdd.numericaltictactoe;

public class NumericalTicTacToe {
    private static final int BOARD_DIM = 3;
    private static final int BOARD_SIZE = BOARD_DIM * BOARD_DIM;
    private static final int WINNING_SUM = 15;

    private Integer[] board = new Integer[BOARD_SIZE];
    private MoveValidator moveValidator = new MoveValidator(board);
    private boolean isPlayer1Current = true;

    public GameResult play(int coords, int number) {
        moveValidator.checkMoveLegal(coords, number, isPlayer1Current);
        makeMove(coords, number);
        if (isGameWon(coords))
            return isPlayer1Current ? GameResult.PLAYER1_WON : GameResult.PLAYER2_WON;
        if(isDrawn()) {
            return GameResult.DRAW;
        }
        switchPlayer();
        return GameResult.IN_PROGRESS;
    }

    public int getPlayersIdx() {
        return isPlayer1Current ? 1 : 2;
    }

    private void makeMove(int coords, int number) {
        board[coords] = number;
    }

    private void switchPlayer() {
        isPlayer1Current = !isPlayer1Current;
    }

    private boolean isGameWon(int coords) {
        return isGameWonVertically(coords) || isGameWonDiagonally(coords) || isGameWonHorizontally(coords);
    }

    private boolean isGameWonVertically(int coords) {
        return board[coords] != null &&
                board[(coords + BOARD_DIM) % BOARD_SIZE] != null &&
                board[(coords + 2 * BOARD_DIM) % BOARD_SIZE] != null &&
                board[coords] + board[(coords + BOARD_DIM) % BOARD_SIZE] + board[(coords + 2 * BOARD_DIM) % BOARD_SIZE] == WINNING_SUM;
    }

    private boolean isGameWonDiagonally(int coords) {
        boolean result = false;
        if (coords % 2 == 0) { // even indices only on diagonals
            result = (board[0] != null && board[4] != null && board[8] != null &&
                    board[0] + board[4] + board[8] == WINNING_SUM) ||
                    (board[2] != null && board[4] != null && board[6] != null &&
                            board[2] + board[4] + board[6] == WINNING_SUM);

        }
        return result;
    }

    private boolean isGameWonHorizontally(int coords) {
        int crd = coords - coords % BOARD_DIM;
        return board[crd] != null && board[crd + 1] != null && board[crd + 2] != null &&
                board[crd] + board[crd + 1] + board[crd + 2] == WINNING_SUM;
    }

    private boolean isDrawn() {
        for(Integer b : board) {
            if(b == null)
                return false;
        }
        return true;
    }
}
