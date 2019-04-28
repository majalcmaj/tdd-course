package com.packtpublishing.tddjava.ch06tictactoemongo;

import com.packtpublishing.tddjava.ch06tictactoemongo.mongo.TicTacToeBean;
import com.packtpublishing.tddjava.ch06tictactoemongo.mongo.TicTacToeCollection;

public class TicTacToe {

    private Character[][] board = {{'\0', '\0', '\0'}, {'\0', '\0', '\0'}, {'\0', '\0', '\0'}};
    private char lastPlayer = '\0';
    private static final int SIZE = 3;
    public static final String NO_WINNER = "No winner";
    public static final String RESULT_DRAW = "The result is draw";
    private TicTacToeCollection collection;
    private int turn = 0;

    public TicTacToe(TicTacToeCollection collection) {
        this.collection = collection;
        dropCollection();
    }

    private void dropCollection() {
        if (!this.collection.drop()) {
            throw new RuntimeException("Error during dropping of the collection.");
        }
    }

    public String play(int x, int y) {
        turn++;
        checkAxis(x);
        checkAxis(y);
        lastPlayer = nextPlayer();
        setBox(x, y, lastPlayer);
        saveMove(x, y);
        if (isWin(x, y)) {
            return lastPlayer + " is the winner";
        } else if (isDraw()) {
            return RESULT_DRAW;
        } else {
            return NO_WINNER;
        }
    }

    private void saveMove(int x, int y) {
        boolean saveResult = collection.saveMove(new TicTacToeBean(turn, x, y, lastPlayer));
        if (!saveResult) {
            throw new RuntimeException("Error during saving the move in the database");
        }
    }

    public char nextPlayer() {
        if (lastPlayer == 'X') {
            return 'O';
        }
        return 'X';
    }

    private void checkAxis(int axis) {
        if (axis < 1 || axis > 3) {
            throw new RuntimeException("X is outside board");
        }
    }

    private void setBox(int x, int y, char lastPlayer) {
        if (board[x - 1][y - 1] != '\0') {
            throw new RuntimeException("Box is occupied");
        } else {
            board[x - 1][y - 1] = lastPlayer;
        }
    }

    private boolean isWin(int x, int y) {
        int playerTotal = lastPlayer * SIZE;
        char horizontal, vertical, diagonal1, diagonal2;
        horizontal = vertical = diagonal1 = diagonal2 = '\0';
        for (int i = 0; i < SIZE; i++) {
            horizontal += board[i][y - 1];
            vertical += board[x - 1][i];
            diagonal1 += board[i][i];
            diagonal2 += board[i][SIZE - i - 1];
        }
        if (horizontal == playerTotal
                || vertical == playerTotal
                || diagonal1 == playerTotal
                || diagonal2 == playerTotal) {
            return true;
        }
        return false;
    }

    private boolean isDraw() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (board[x][y] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    public TicTacToeCollection getCollection() {
        return collection;
    }
}
