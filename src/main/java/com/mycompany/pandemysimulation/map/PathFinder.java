/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.map;

import com.mycompany.pandemysimulation.shop.Shop;
import com.mycompany.pandemysimulation.map.Location;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author kacper
 */
public class PathFinder {
    boolean[][][] directions;
    Location[][] locations;
    int sizeX;
    int sizeY;

    public PathFinder(boolean[][][] directions, Location[][] locations) {
        this.directions = directions;
        this.locations = locations;
        this.sizeY = directions.length;
        this.sizeX = directions[0].length;
        
    }
    
    
    public List<Location> findPath(int fromX,int fromY, int toX, int toY){
        boolean[][] visited = new boolean[sizeY][sizeX];
        PriorityQueue<QueElement> q = new PriorityQueue<>();
        q.add(new QueElement(this.heurystic(fromX, fromY, toX, toY), 0, new LinkedList<>(), this.locations[fromY][fromX]));

        while(!q.isEmpty()){
            QueElement element = q.poll();
            Location lastLocation = element.getLastLocation();
            if(visited[lastLocation.getIdY()][lastLocation.getIdX()]){
                continue;
            }
            visited[lastLocation.getIdY()][lastLocation.getIdX()] = true;
            if(lastLocation.getIdX() == toX && lastLocation.getIdY() == toY){
                return element.getPath();
            }
            if( !(lastLocation.getIdX() == fromX && lastLocation.getIdY() == fromY) && locations[lastLocation.getIdY()][lastLocation.getIdX()] instanceof Shop){
                continue;
            }
            List<Direction> actions = getActions(lastLocation);
            for(Direction action: actions){
                Location nextLocation = locations[lastLocation.getIdY()+action.dY()][lastLocation.getIdX()+action.dX()];
                if(!visited[nextLocation.getIdY()][nextLocation.getIdX()]){
                    int heurysticCost = heurystic(nextLocation.getIdX(), nextLocation.getIdY(), toX, toY);
                    List<Location> nextPath = new LinkedList<>(element.getPath());
                    nextPath.add(nextLocation);
                    int nextCost = element.getCurrent_distance()+1;
                    QueElement nextElement = new QueElement(nextCost+heurysticCost, nextCost, nextPath, nextLocation);
                    q.add(nextElement);
                }
            }
        
        }
        return null;
    }
    
    private List<Direction> getActions(Location lastLocation){
        boolean[] moves = directions[lastLocation.getIdY()][lastLocation.getIdX()];
        List<Direction> actions = new LinkedList<>();
        for(int i =0; i< 4;i++){
            if(moves[i])actions.add(Direction.valToDirection(i));
        }
        return actions;
    }
    
    
    private int heurystic(int fromX, int fromY, int toX, int toY){
        return Math.abs(fromX-toX)+Math.abs(fromY-toY);
    }
}


class QueElement implements Comparable<QueElement>{
    private int heurysticDistance;
    private int current_distance;
    private List<Location> path;
    private Location lastLocation;

    public QueElement(int heurysticDistance, int current_distance, List<Location> path, Location lastLocation) {
        this.heurysticDistance = heurysticDistance;
        this.current_distance = current_distance;
        this.path = path;
        this.lastLocation = lastLocation;
    }

    public int getHeurysticDistance() {
        return heurysticDistance;
    }

    public int getCurrent_distance() {
        return current_distance;
    }

    public List<Location> getPath() {
        return path;
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    @Override
    public int compareTo(QueElement arg0) {
        return Integer.compare(heurysticDistance, arg0.heurysticDistance);
    }
    
    

}
