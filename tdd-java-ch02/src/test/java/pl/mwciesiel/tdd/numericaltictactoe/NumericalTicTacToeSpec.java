package pl.mwciesiel.tdd.numericaltictactoe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class NumericalTicTacToeSpec {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    private NumericalTicTacToe ttt;

    @Before
    public void setUp() throws Exception {
        ttt = new NumericalTicTacToe();
    }

    @Test
    public void whenCoordOutsideTheBoardThenException() {
        exception.expect(RuntimeException.class);
        ttt.play(9, 1);
    }

    @Test
    public void whenNumberOutsideRangeThenException() {
        exception.expect(RuntimeException.class);
        ttt.play(1, 10);
    }

    @Test
    public void whenPlaceOccupiedThenException() {
        ttt.play(1, 1);
        exception.expect(RuntimeException.class);
        ttt.play(1, 2);
    }

    @Test
    public void whenNumberAlreadyUsedThenException() {
        ttt.play(0, 1);
        ttt.play(1, 2);
        exception.expect(RuntimeException.class);
        ttt.play(6, 1);
    }

    @Test
    public void whenGameStartsThenPlayers1Turn() {
        assertEquals(1, ttt.getPlayersIdx());
    }

    @Test
    public void whenTurnIsMadeThenPlayers2Turn() {
        ttt.play(1, 1);
        assertEquals(2, ttt.getPlayersIdx());
    }

    @Test
    public void when2TurnsAreMadeThenPlayers1Turn() {
        ttt.play(1, 1);
        ttt.play(2, 2);
        assertEquals(1, ttt.getPlayersIdx());
    }

    @Test
    public void whenPlayer1PicksEvenNumberThanException() {
        exception.expect(RuntimeException.class);
        ttt.play(1, 2);
    }

    @Test
    public void whenPlayer2PicksOddNumberThanException() {
        ttt.play(1, 1);
        exception.expect(RuntimeException.class);
        ttt.play(2, 3);
    }

    @Test
    public void whenAfterFirstMoveThenGameStillInProgress() {
        GameResult result = ttt.play(1, 1);
        assertEquals(GameResult.IN_PROGRESS, result);
    }

    @Test
    public void whenPlayer1WonVerticallyThenTheGoodStatusIsReturned() {
        ttt.play(0, 7);
        ttt.play(4, 2);
        ttt.play(2, 3);
        GameResult result = ttt.play(1, 8);
        assertEquals(GameResult.IN_PROGRESS, result);
        result = ttt.play(7, 5);
        assertEquals(GameResult.PLAYER1_WON, result);
    }

    @Test
    public void whenPlayer2WonHorizontallyThenTheGoodStatusIsReturned() {
        ttt.play(2, 1);
        ttt.play(7, 2);
        GameResult result = ttt.play(6, 7);
        assertEquals(GameResult.IN_PROGRESS, result);
        result = ttt.play(8, 6);
        assertEquals(GameResult.PLAYER2_WON, result);
    }

    @Test
    public void whenPlayer1WonDiagonallyThenTheGoodStatusIsReturned() {
        ttt.play(0, 1);
        ttt.play(4, 8);
        ttt.play(1, 3);
        GameResult result = ttt.play(2, 2);
        assertEquals(GameResult.IN_PROGRESS, result);
        result = ttt.play(6, 5);
        assertEquals(GameResult.PLAYER1_WON, result);
    }

    @Test
    public void whenPlayer2WonDiagonallyThenTheGoodStatusIsReturned() {
        ttt.play(0, 9);
        ttt.play(4, 4);
        ttt.play(6, 3);
        ttt.play(3, 6);
        GameResult result = ttt.play(5, 1);
        assertEquals(GameResult.IN_PROGRESS, result);
        result = ttt.play(8, 2);
        assertEquals(GameResult.PLAYER2_WON, result);
    }

    @Test
    public void whenAllFieldsFilledAndNoOneWinsThenDrawStatusReturned() {
        ttt.play(0, 1);
        ttt.play(1, 2);
        ttt.play(2, 5);
        ttt.play(3, 4);
        ttt.play(4, 9);
        ttt.play(5, 6);
        ttt.play(6, 7);
        GameResult result = ttt.play(7, 8);
        assertEquals(GameResult.IN_PROGRESS, result);
        result = ttt.play(8, 3);
        assertEquals(GameResult.DRAW, result);
    }
}
