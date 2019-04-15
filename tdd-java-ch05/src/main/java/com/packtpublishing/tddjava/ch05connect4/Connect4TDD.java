package com.packtpublishing.tddjava.ch05connect4;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Connect4TDD {
    public static final int COLUMNS = 7;
    public static final int ROWS = 6;

    private Player[][] board = new Player[COLUMNS][ROWS];
    private int numberOfDiscs;
    private Player currentPlayer = Player.RED;

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

    public Connect4TDD() {
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
        switchPlayers();
        numberOfDiscs++;
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

    private void switchPlayers() {
        if(currentPlayer == Player.BLUE) {
            currentPlayer = Player.RED;
        } else  {
            currentPlayer = Player.BLUE;
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
