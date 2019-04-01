package pl.mwciesiel.tdd.tictactoe;

public class TicTacToe {

    private Character[][] board = {
            {'\0', '\0', '\0'},
            {'\0', '\0', '\0'},
            {'\0', '\0', '\0'}
    };
    private int movesCounter = 0;

    private final static int[][][] winningCombinations = {
            {{0, 0}, {0, 1}, {0, 2}},
            {{1, 0}, {1, 1}, {1, 2}},
            {{2, 0}, {2, 1}, {2, 2}},

            {{0, 0}, {1, 0}, {2, 0}},
            {{0, 1}, {1, 1}, {2, 1}},
            {{0, 2}, {1, 2}, {2, 2}},

            {{0, 0}, {1, 1}, {2, 2}},
            {{0, 2}, {1, 1}, {2, 0}},
    };

    private Character currentPlayer = 'X';

    public String play(int y, int x) {
        checkAxis(x, "X");
        checkAxis(y, "Y");
        doMove(x, y);
        boolean isWin = checkWinner();
        if(isWin) {
            return "Wygrał " + currentPlayer;
        }
        switchPlayer();
        if(isDraw()) {
            return "Remis";
        }
        return "Brak zwycięzcy";
    }

    private boolean checkWinner() {
        for (int[][] c : winningCombinations) {
            if (board[c[0][0]][c[0][1]] == currentPlayer &&
                    board[c[1][0]][c[1][1]] == currentPlayer &&
                    board[c[2][0]][c[2][1]] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private void checkAxis(int index, String axis) {
        if (index < 1 || index > 3) {
            throw new RuntimeException(String.format("Wartość %s wykracza poza planszę!", axis));
        }
    }

    private void doMove(int x, int y) {
        if (board[y - 1][x - 1] != '\0') {
            throw new RuntimeException(String.format("Pole (%d, %d) jest już zajęte", y, x));
        }
        board[y - 1][x - 1] = currentPlayer;
    }

    private void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }

    private boolean isDraw() {
        return ++movesCounter >= 9;
    }

    public Character nextPlayer() {
        return currentPlayer;
    }
}
