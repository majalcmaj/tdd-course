package com.packtpublishing.tddjava.ch05connect4;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Connect4TDD {
    public static final int COLUMNS = 7;
    public static final int ROWS = 6;
    public static final int DISCS_TO_WIN = 4;
    private final PrintStream output;

    private Player[][] board = new Player[COLUMNS][ROWS];
    private int numberOfDiscs;
    private Player currentPlayer = Player.RED;
    private Player winner = Player.EMPTY;

    public enum Player {
        RED('R'),
        BLUE('B'),
        EMPTY(' ');

        Player(char field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return String.valueOf(field);
        }

        private char field;
    }

    public Connect4TDD(PrintStream output) {
        this.output = output;
        for (Player[] column : board) {
            Arrays.fill(column, Player.EMPTY);
        }
    }

    public int getNumberOfDiscs() {
        return numberOfDiscs;
    }

    public int putDiskInColumn(int column) {
        checkColumn(column);
        int disksInColumnCount = getNumberOfDisksInColumn(column);
        checkPositionToInsert(column, disksInColumnCount);
        insertDisc(column, disksInColumnCount);
        if (didPlayerWin(disksInColumnCount, column)) {
            setCurrentPlayerAsWinning();
        }
        switchPlayer();
        numberOfDiscs++;
        printBoard();
        return disksInColumnCount;
    }

    private void checkColumn(int column) {
        if (column < 0 || column > COLUMNS) {
            throw new RuntimeException("Wrong column " + column);
        }
    }

    private int getNumberOfDisksInColumn(int columnIdx) {
        return (int) IntStream.range(0, ROWS)
                .filter(rowIdx -> !board[columnIdx][rowIdx].equals(Player.EMPTY))
                .count();
    }

    private void checkPositionToInsert(int column, int row) {
        if (row >= ROWS) {
            throw new RuntimeException("Column " + column + " is full");
        }
    }

    private void insertDisc(int column, int row) {
        board[column][row] = currentPlayer;
    }

    private void switchPlayer() {
        if (currentPlayer == Player.BLUE) {
            currentPlayer = Player.RED;
        } else {
            currentPlayer = Player.BLUE;
        }
    }

    public Player getCurrentPlayer() {
        output.println(currentPlayer + " player's turn");
        return currentPlayer;
    }

    private void printBoard() {
        for (int y = ROWS - 1; y >= 0; y--) {
            for (int x = 0; x < COLUMNS; x++) {
                output.append('|').append(board[x][y].field);
            }
            output.append("|\n");
        }
    }

    private boolean didPlayerWin(int row, int column) {
        return checkVerticalWin(row, column) || checkHorizontalWin(row, column);
    }

    private Player[] getRow(int row) {
        return Arrays.stream(board).map(column -> column[row]).toArray(Player[]::new);
    }

    private boolean checkVerticalWin(int row, int column) {
        Player[] checkedColumn = board[column];
        if (row >= DISCS_TO_WIN - 1) {
            int offset = row - DISCS_TO_WIN + 1;
            for (int index = 0; index < DISCS_TO_WIN; index++) {
                if (checkedColumn[index + offset] != currentPlayer) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean checkHorizontalWin(int row, int column) {
        Player[] theRow = getRow(row);
        int startIdx = Math.max(0, column - DISCS_TO_WIN + 1);
        int stopIdx = Math.min(COLUMNS - DISCS_TO_WIN, column);
        for (int offset = startIdx; offset <= stopIdx; offset++) {
            if(winsForOffset(theRow, offset)) {
                return true;
            }
        }
        return false;
    }

    private Boolean winsForOffset(Player[] theRow, int offset) {
        for (int idx = 0; idx < DISCS_TO_WIN; idx++) {
            if (theRow[idx + offset] != currentPlayer) {
                return false;
            }
        }
        return true;
    }

    private void setCurrentPlayerAsWinning() {
        winner = currentPlayer;
    }

    public boolean isFinished() {
        return numberOfDiscs >= ROWS * COLUMNS;
    }

    public Player getWinner() {
        return winner;
    }
}
