package com.packtpublishing.tddjava.ch06tictactoemongo;

import com.packtpublishing.tddjava.ch06tictactoemongo.mongo.TicTacToeCollection;
import org.junit.Assert;
import org.junit.Test;

import java.rmi.UnknownHostException;

public class TicTacToeInteg {
    @Test
    public void givenMongodbIsRunningThenNoException() throws UnknownHostException {
        TicTacToe ticTacToe = new TicTacToe(new TicTacToeCollection());
        Assert.assertEquals(TicTacToe.NO_WINNER, ticTacToe.play(1, 1));
    }
}
