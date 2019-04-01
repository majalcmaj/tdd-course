package pl.mwciesiel.tdd.tictactoe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TicTacToeSpec {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    private TicTacToe ticTacToe;

    @Before
    public void setUp() throws Exception {
        ticTacToe = new TicTacToe();
    }

    @Test
    public void whenXOutsideBoardThenRuntimeException() {
        exception.expect(RuntimeException.class);
        ticTacToe.play(2, 5);
    }

    @Test
    public void whenYOutsideBoardThenRuntimeException() {
        exception.expect(RuntimeException.class);
        ticTacToe.play(5, 2);
    }

    @Test
    public void whenOccupiedThenRuntimeException() {
        ticTacToe.play(2, 1);
        exception.expect(RuntimeException.class);
        ticTacToe.play(2, 1);
    }

    @Test
    public void givenFirstTurnWhenNextPlayerThenX() {
        assertEquals((Character) 'X', ticTacToe.nextPlayer());
    }

    @Test
    public void givenLastTurnWasXWhenNextTurnThenO() {
        ticTacToe.play(1, 1);
        assertEquals((Character) 'O', ticTacToe.nextPlayer());
    }

    @Test
    public void givenLastTurnWasOWhenNextTurnThenX() {
        ticTacToe.play(1, 1);
        ticTacToe.play(2, 1);
        assertEquals((Character) 'X', ticTacToe.nextPlayer());
    }

    @Test
    public void whenFirstMoveThenNoWinner() {
        String result = ticTacToe.play(1, 1);
        assertEquals("Brak zwycięzcy", result);
    }

    @Test
    public void whenPlayAndWhole1HorizontalLineThenWinner() {
        checkHorizontalWin(1);
    }

    @Test
    public void whenPlayAndWhole2HorizontalLineThenWinner() {
        checkHorizontalWin(2);
    }

    @Test
    public void whenPlayAndWhole3HorizontalLineThenWinner() {
        checkHorizontalWin(3);
    }

    private void checkHorizontalWin(int y) {
        ticTacToe.play(y, 1); //X
        ticTacToe.play(y % 3 + 1, 1); //0
        ticTacToe.play(y, 2); //X
        ticTacToe.play(y % 3 + 1, 2); //0
        String actual = ticTacToe.play(y, 3);
        assertEquals("Wygrał X", actual);
    }

    @Test
    public void whenPlayAndWhole1VerticalLineThenWinner() {
        checkVerticalWin(1);
    }

    @Test
    public void whenPlayAndWhole2VerticalLineThenWinner() {
        checkVerticalWin(2);
    }

    @Test
    public void whenPlayAndWhole3VerticalLineThenWinner() {
        checkVerticalWin(3);
    }

    private void checkVerticalWin(int x) {
        ticTacToe.play(1, x % 3 + 1); //X
        ticTacToe.play(3, x); //O
        ticTacToe.play(2, x % 3 + 1); //X
        ticTacToe.play(1, x); //O
        ticTacToe.play(2, (x + 1) % 3 + 1); //X
        String actual = ticTacToe.play(2, x); //O
        assertEquals("Wygrał O", actual);
    }

    @Test
    public void whenPlayAndWholeFirstDiagonalThenWinner() {
        ticTacToe.play(1, 1); //X
        ticTacToe.play(2, 1); //O
        ticTacToe.play(2, 2); //X
        ticTacToe.play(3, 1); //O
        String actual = ticTacToe.play(3, 3); //X
        assertEquals("Wygrał X", actual);
    }

    @Test
    public void whenPlayAndWholeSecondDiagonalThenWinner() {
        ticTacToe.play(1, 1); //X
        ticTacToe.play(3, 1); //O
        ticTacToe.play(2, 1); //X
        ticTacToe.play(1, 3); //O
        ticTacToe.play(2, 3); //X
        String actual = ticTacToe.play(2, 2); //O
        assertEquals("Wygrał O", actual);
    }

    @Test
    public void whenAllFieldsAreFilledThenDraw() {
        ticTacToe.play(1, 2);
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 3);
        ticTacToe.play(2, 3);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        String result = ticTacToe.play(3, 2);
        assertEquals("Brak zwycięzcy", result);
        result = ticTacToe.play(3, 3);
        assertEquals("Remis", result);
    }
}
