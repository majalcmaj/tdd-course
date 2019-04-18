package com.packtpublishing.tddjava.ch05connect4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.*;

import static com.packtpublishing.tddjava.ch05connect4.RegexMatcher.matches;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class Connect4TDDSpec {
    private Connect4TDD tested;

    @Rule
    public ExpectedException exception = ExpectedException.none();
    private OutputStream output;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        tested = new Connect4TDD(new PrintStream(output));
    }

    @Test
    public void whenTheGameIsStartedTheBoardIsEmpty() {
        assertThat(tested.getNumberOfDiscs(), is(0));
    }

    @Test
    public void whenOutsideTheBoardThenRuntimeException() {
        int[] columns = {-1, 7};
        for (int column : columns) {
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
        for (int i = 0; i < Connect4TDD.ROWS; i++) {
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

    @Test
    public void whenAskedForCurrentPlayerThenOutputNotice() {
        tested.getCurrentPlayer();
        assertThat(output.toString(), containsString(Connect4TDD.Player.RED + " player's turn"));
    }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted() {
        tested.putDiskInColumn(1);
        assertThat(output.toString(), matches("(\\| \\| \\| \\| \\| \\| \\| \\|\\n){5}\\| \\|R\\| \\| \\| \\| \\| \\|\\n"));
    }

    @Test
    public void whenGameStartsItIsNotFinished() {
        assertFalse("A game cannot be finished!", tested.isFinished());
    }

    @Test
    public void whenNoDiskCanBeIntroducedThenGameIsFinished() {
        for (int row = 0; row < Connect4TDD.ROWS; row++) {
            for (int col = 0; col < Connect4TDD.COLUMNS; col++) {
                tested.putDiskInColumn(col);
            }
        }
        assertTrue("The game has to be finished", tested.isFinished());
    }

    @Test
    public void when4VerticalDiscsAreConnectedThanPlayerWins() {
        for (int row = 0; row < 3; row++) {
            tested.putDiskInColumn(1); // R
            tested.putDiskInColumn(2); // B
        }
        tested.putDiskInColumn(1); // R
        assertThat(tested.getWinner(), is(Connect4TDD.Player.RED));
    }

    @Test
    public void when4HorizontalDiscsAreConnectedThenPlayerWins() {
        tested.putDiskInColumn(Connect4TDD.COLUMNS - 1); // R
        for (int i = 0; i < 3; i++) {
            tested.putDiskInColumn(i + 1); // B
            tested.putDiskInColumn(i + 1); // R
        }
        tested.putDiskInColumn(4); // B
        System.out.println(output.toString());
        assertThat(tested.getWinner(), is(Connect4TDD.Player.BLUE));
    }
}
