package com.packtpublishing.tddjava.ch05connect4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class Connect4TDDSpec {
    private Connect4TDD tested;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        tested = new Connect4TDD();
    }

    @Test
    public void whenTheGameIsStartedTheBoardIsEmpty() {
        assertThat(tested.getNumberOfDiscs(), is(0));
    }

    @Test
    public void whenOutsideTheBoardThenRuntimeException() {
        int[] columns = {-1, 7};
        for(int column : columns) {
            exception.expect(RuntimeException.class);
            exception.expectMessage("Wrong column " + column);
            tested.putDiskInColumn(column);
        }
    }

    @Test
    public void whenDiscInsertedInColumnThenPositionIs0() {
        int column = 0;
        assertThat(tested.putDiskInColumn(column), is(0));
    }

    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIs1() {
        int column = 2;
        tested.putDiskInColumn(column);

        assertThat(tested.putDiskInColumn(column), is(1));
    }

    @Test
    public void whenDiscInsertedThenNumberOfDiscsIncreases() {
        tested.putDiskInColumn(0);
        assertThat(tested.getNumberOfDiscs(), is(1));
    }

    @Test
    public void whenNoMoreRoomInColumnThenRuntimeException() {
        int column = 3;
        for(int i = 0; i < Connect4TDD.ROWS; i++) {
            tested.putDiskInColumn(column);
        }

        exception.expect(RuntimeException.class);
        exception.expectMessage("Column " + column + " is full");
        tested.putDiskInColumn(column);
    }

    @Test
    public void whenGameStartsThenDiscColorIsRed() {
        assertThat(tested.getCurrentPlayer(), is(Connect4TDD.Player.RED));
    }

    @Test
    public void whenMoveIsMadeThenColorIsBlue() {
        int column = 1;
        tested.putDiskInColumn(column);
        assertThat(tested.getCurrentPlayer(), is(Connect4TDD.Player.BLUE));
    }
}
