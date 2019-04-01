package com.packtpublishing.tddjava.ch04ship;

import org.testng.annotations.*;

import static org.testng.Assert.*;

@Test
public class ShipSpec {

    private Ship ship;
    private Location location;
    private Planet planet;

    @BeforeTest
    public void setUp() {
        location = new Location(new Point(21, 13), Direction.NORTH);
        planet = new Planet(new Point(50, 50));
        ship = new Ship(location);
    }

    public void whenInstantiatedThenLocationSet() {
        assertEquals(ship.getLocation(), location);
    }

    public void whenMoveForwardThenForward() {
        Location expected  = location.copy();
        expected.forward();
        ship.moveForward();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenMoveBackwardThenForward() {
        Location expected  = location.copy();
        expected.backward();
        ship.moveBackward();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenTurnLeftThenTurnedLeft() {
        Location expected = location.copy();
        expected.turnLeft();
        ship.turnLeft();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenTurnRightThenTurnedRight() {
        Location expected = location.copy();
        expected.turnRight();
        ship.turnRight();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandFThenForward() {
        Location expected = location.copy();
        expected.forward();
        ship.receiveCommands("f");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandBThenBackward() {
        Location expected = location.copy();
        expected.backward();
        ship.receiveCommands("b");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandLThenTurnLeft() {
        Location expected = location.copy();
        expected.turnLeft();
        ship.receiveCommands("l");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandRThenTurnRight() {
        Location expected = location.copy();
        expected.turnRight();
        ship.receiveCommands("r");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsThenAllExecuted() {
        Location expected = location.copy();
        expected.turnRight();
        expected.forward();
        expected.turnLeft();
        expected.backward();
        ship.receiveCommands("rflb");
        assertEquals(ship.getLocation(), expected);
    }

    public void givenOnNorthEdgeAndTurnedNorthWhenMoveForwardTHenJumpToTheBeginningOfMap() {
        location.setDirection(Direction.NORTH);
        location.getPoint().setY();
    }
}
