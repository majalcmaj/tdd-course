package com.packtpublishing.tddjava.ch04ship;

import java.util.List;

public class Ship {

    private final Location location;
    private final Planet planet;
    private List<Point> obstacles;

    public Ship(Location location, Planet planet, List<Point> obstacles) {
        this.location = location;
        this.planet = planet;
        this.obstacles = obstacles;
    }

    public Location getLocation() {
        return location;
    }

    public boolean moveForward() {
        return location.forward(planet.getMax(), obstacles);
    }

    public boolean moveBackward() {
        return location.backward(planet.getMax(), obstacles);
    }

    public void turnLeft() {
        location.turnLeft();
    }

    public void turnRight() {
        location.turnRight();
    }

    public String receiveCommands(String commands) {
        StringBuilder results = new StringBuilder();
        for (char command : commands.toCharArray()) {
            switch (command) {
                case 'f':
                    if(moveForward()) {
                        results.append("O");
                    } else {
                        results.append("X");
                    }
                    break;
                case 'b':
                    if(moveBackward()) {
                        results.append("O");
                    } else {
                        results.append("X");
                    }
                    break;
                case 'l':
                    turnLeft();
                    break;
                case 'r':
                    turnRight();
                    break;
            }
        }
        return results.toString();
    }

    public Planet getPlanet() {
        return planet;
    }

    public List<Point> getObstacles() {
        return obstacles;
    }
}
