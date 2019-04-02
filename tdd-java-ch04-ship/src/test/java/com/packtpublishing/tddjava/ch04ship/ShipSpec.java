package com.packtpublishing.tddjava.ch04ship;

import org.testng.annotations.*;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class ShipSpec {
    private Ship ship;
    private Location location;
    private Planet planet;
    private List<Point> obstacles;

    @BeforeTest
    public void setUp() {
        location = new Location(new Point(21, 13), Direction.NORTH);
        Point max = new Point(50, 50);
        planet = new Planet(max);
        obstacles = Arrays.asList(new Point(23, 14), new Point(22, 15), new Point(23, 16));
        ship = new Ship(location, planet, obstacles);
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
        expected.forward(planet.getMax());
        expected.turnLeft();
        expected.backward(planet.getMax());
        ship.receiveCommands("rflb");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenInstantiatedThenPlanetIsStored() {
        assertEquals(ship.getPlanet(), planet);
    }

    public void givenOnSEdgeAndTurnedSWhenMoveForwardThenWrap() {
        location.setDirection(Direction.SOUTH);
        location.getPoint().setY(planet.getMax().getY());
        ship.receiveCommands("f");
        assertEquals(ship.getLocation().getY(), 1);
    }

    public void givenOnWEdgeAndTurnedEWhenMoveBackwardThenWrap() {
        location.setDirection(Direction.WEST);
        location.getPoint().setX(planet.getMax().getX());
        ship.receiveCommands("b");
        assertEquals(ship.getLocation().getX(), 1);
    }

    public void whenInstantiatedThenObstaclesAreStored() {
        assertEquals(ship.getObstacles(), obstacles);
    }

    public void givenNextToObstacleAndTurnedFrontToItWhenForwardThenFailToMove() {
        location.getPoint().setX(23);
        location.getPoint().setY(13);
        location.setDirection(Direction.SOUTH);
        assertFalse(ship.moveForward());
    }

     public void givenNextToObstacleAndTurnedBackToItWhenBackwardThenFailToMove() {
        location.getPoint().setX(22);
        location.getPoint().setY(16);
        location.setDirection(Direction.WEST);
        assertFalse(ship.moveBackward());
    }

    public void whenReceivedCommandsThenXOnFailOOnOk() {
        String actual = ship.receiveCommands("rfflblflffrflfflffrb");
        assertEquals(actual, "OOXOOXOOOOXX");
    }
}
