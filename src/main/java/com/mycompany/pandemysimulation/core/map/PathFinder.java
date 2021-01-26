/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core.map;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * Finds paths from point A to point B on Map with given directions
 * 
 * @author kacper
 */
public class PathFinder {

    boolean[][][] directions;
    Location[][] locations;
    int sizeX;
    int sizeY;

    /**
     *
     * @param directions directions to move 
     * @param locations locations on map
     */
    protected PathFinder(boolean[][][] directions, Location[][] locations) {
        this.directions = directions;
        this.locations = locations;
        this.sizeY = directions.length;
        this.sizeX = directions[0].length;
    }

    /**
     *
     * @param fromX x position of start Location
     * @param fromY y position of start Location
     * @param toX   y position of end Location
     * @param toY   y position of end Location
     * @return
     */
    public List<Location> findPath(int fromX, int fromY, int toX, int toY) {
        boolean[][] visited = new boolean[sizeY][sizeX];
        PriorityQueue<QueElement> q = new PriorityQueue<>();
        q.add(new QueElement(this.heurystic(fromX, fromY, toX, toY), 0, new LinkedList<>(), this.locations[fromY][fromX]));

        while (!q.isEmpty()) {
            QueElement element = q.poll();
            Location lastLocation = element.getLastLocation();
            if (visited[lastLocation.getCoordY()][lastLocation.getCoordX()]) {
                continue;
            }
            visited[lastLocation.getCoordY()][lastLocation.getCoordX()] = true;
            if (lastLocation.getCoordX() == toX && lastLocation.getCoordY() == toY) {
                return element.getPath();
            }
            if (!(lastLocation.getCoordX() == fromX && lastLocation.getCoordY() == fromY) && !locations[lastLocation.getCoordY()][lastLocation.getCoordX()].shouldGoThrough()) {
                continue;
            }
            List<Direction> actions = getActions(lastLocation);
            for (Direction action : actions) {
                Location nextLocation = locations[lastLocation.getCoordY() + action.dY()][lastLocation.getCoordX() + action.dX()];
                if (!visited[nextLocation.getCoordY()][nextLocation.getCoordX()]) {
                    int heurysticCost = heurystic(nextLocation.getCoordX(), nextLocation.getCoordY(), toX, toY);
                    List<Location> nextPath = new LinkedList<>(element.getPath());
                    nextPath.add(nextLocation);
                    int nextCost = element.getCurrent_distance() + 1;
                    QueElement nextElement = new QueElement(nextCost + heurysticCost, nextCost, nextPath, nextLocation);
                    q.add(nextElement);
                }
            }

        }
        return null;
    }

    private List<Direction> getActions(Location lastLocation) {
        boolean[] moves = directions[lastLocation.getCoordY()][lastLocation.getCoordX()];
        List<Direction> actions = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            if (moves[i]) {
                actions.add(Direction.indexToDirection(i));
            }
        }
        return actions;
    }

    private int heurystic(int fromX, int fromY, int toX, int toY) {
        return Math.abs(fromX - toX) + Math.abs(fromY - toY);
    }
}

class QueElement implements Comparable<QueElement> {

    private int heurysticDistance;
    private int current_distance;
    private List<Location> path;
    private Location lastLocation;

    QueElement(int heurysticDistance, int current_distance, List<Location> path, Location lastLocation) {
        this.heurysticDistance = heurysticDistance;
        this.current_distance = current_distance;
        this.path = path;
        this.lastLocation = lastLocation;
    }

    int getHeurysticDistance() {
        return heurysticDistance;
    }

    int getCurrent_distance() {
        return current_distance;
    }

    List<Location> getPath() {
        return path;
    }

    Location getLastLocation() {
        return lastLocation;
    }

    @Override
    public int compareTo(QueElement arg0) {
        return Integer.compare(heurysticDistance, arg0.heurysticDistance);
    }

}
