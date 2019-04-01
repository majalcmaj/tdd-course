package pl.mwciesiel.tdd.numericaltictactoe;

public class MoveValidator {
    private Integer[] board;

    public MoveValidator(Integer[] board) {
        this.board = board;
    }

    public void checkMoveLegal(int coords, int number, boolean isPlayer1Current) {
        checkCoord(coords);
        checkNumber(number);
        checkNotOccupied(board, coords);
        checkNumberNotUsed(number);
        checkPlayerPickedLegalNumber(number, isPlayer1Current);
    }

    private void checkNumberNotUsed(int number) {
        for (Integer val : board) {
            if (val != null && number == val) {
                throw new RuntimeException("The number has already been used!");
            }
        }
    }

    private void checkCoord(int coords) {
        if (coords < 0 || coords > board.length - 1) {
            throw new RuntimeException("The coords must be between 0 and 8, inclusive");
        }
    }

    private void checkNumber(int number) {
        if (number < 1 || number > 9) {
            throw new RuntimeException("Number has to have value between 1 and 9, inclusive");
        }
    }

    private static void checkNotOccupied(Integer[] board, int coords) {
        if (board[coords] != null) {
            throw new RuntimeException("The field is already occupied");
        }
    }

    private void checkPlayerPickedLegalNumber(int number, boolean isPlayer1Current) {
        if (isPlayer1Current) {
            if (number % 2 == 0)
                throw new RuntimeException("Player 1 can only pick odd numbers");
        } else {
            if (number % 2 == 1)
                throw new RuntimeException("Player 2 can only pick even numbers");
        }
    }
}
