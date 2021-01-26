/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core.map;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *  Finds Deadlocks on map
 * @author kacper
 */
public class DeadlockFinder {

    boolean[][][] directions;
    boolean[][][] occupied;
    Location[][] locations;
    int sizeX;
    int sizeY;
    boolean[][] visited;

    /**
     * Constructor
     * 
     * @param directions
     * @param locations
     */
    protected DeadlockFinder(boolean[][][] directions, Location[][] locations) {
        this.directions = directions;
        this.locations = locations;
        this.sizeY = locations.length;
        this.sizeX = locations[0].length;
        this.occupied = new boolean[sizeY][sizeX][4];
    }

    /**
     * Resets the occupied edges. Use it before introducing new ones
     *
     */
    public void reset() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                for (int i = 0; i < 4; i++) {
                    occupied[y][x][i] = false;
                }
            }
        }
    }

    /**
     *
     * Adds an occupied edge from current location to next location
     * @param next
     * @param current
     */
    public void addEdge(Location next, Location current) {
        if (next == null || current == null || next == current) {
            return;
        }
        Direction direction = Direction.dXdYTodirection(next.getCoordX() - current.getCoordX(), next.getCoordY() - current.getCoordY());
        occupied[current.getCoordY()][current.getCoordX()][direction.getIndex()] = true && directions[current.getCoordY()][current.getCoordX()][direction.getIndex()];
    }

    /**
     * Finds deadlock for the edges previously supplied with addEdge method
     * @see addEdge
     * @return returns a list of Locations that are involved in deadlock
     */
    public List<Location> findDeadlock() {
        visited = new boolean[sizeY][sizeX];
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                if (visited[y][x]) {
                    continue;
                }
                List<Node> result = DFS(locations[y][x]);
                if (result != null) {
                    return transform(finCycleInResult(result));
                }
            }
        }
        return new LinkedList<>();
    }

    private List<Node> DFS(Location location) {
        HashSet<Location> currentBranch = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        Node initNode = new Node(location.getGroup());
        initNode.setChildren(this.getDescendantLocations(initNode));
        currentBranch.addAll(initNode.getLocations());
        stack.add(initNode);
        while (!stack.empty()) {
            Node current = stack.peek();
            List<Location> descendants = current.getChildren();
            boolean didBreak = false;
            for (Location nextLocation : descendants) {
                if (currentBranch.contains(nextLocation)) {
                    stack.add(new Node(nextLocation.getGroup()));
                    Collections.reverse(stack);
                    return stack;
                }
                if (!visited[nextLocation.getCoordY()][nextLocation.getCoordX()]) {
                    Node newNode = new Node(nextLocation.getGroup());
                    newNode.setChildren(getDescendantLocations(newNode));
                    stack.add(newNode);
                    currentBranch.addAll(newNode.getLocations());
                    for (Location nextLocationGroupmate : newNode.getLocations()) {
                        visited[nextLocationGroupmate.getCoordY()][nextLocationGroupmate.getCoordX()] = true;
                    }
                    didBreak = true;
                    break;
                }
            }
            if (!didBreak) {
                stack.pop();
                currentBranch.removeAll(current.getLocations());
            }
        }
        return null;
    }

    private List<Location> getDescendantLocations(Node node) {
        List<Location> result = new LinkedList<>();
        for (Location location : node.getLocations()) {
            for (Direction action : getActions(location)) {
                Location nextLocation = locations[location.getCoordY() + action.dY()][location.getCoordX() + action.dX()];
                if (!node.getLocations().contains(nextLocation) && !node.getLocations().contains(nextLocation)) {
                    result.add(nextLocation);
                }
            }
        }
        return result;
    }

    private List<Node> finCycleInResult(List<Node> result) {
        Node last = result.remove(0);
        List<Node> finalResult = new LinkedList<>();
        finalResult.add(last);
        for (Node node : result) {
            if (node.getCoordX() != last.getCoordX() || node.getCoordY() != last.getCoordY()) {
                finalResult.add(node);
            } else {
                break;
            }
        }
        Collections.reverse(finalResult);
        return finalResult;
    }

    private List<Location> transform(List<Node> nodes) {
        List<Location> result = new LinkedList<>();
        for (Node node : nodes) {
            result.addAll(node.getLocations());
        }
        return result;
    }

    private List<Direction> getActions(Location lastLocation) {
        boolean[] moves = occupied[lastLocation.getCoordY()][lastLocation.getCoordX()];
        List<Direction> actions = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            if (moves[i]) {
                actions.add(Direction.indexToDirection(i));
            }
        }
        return actions;
    }
}
