/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.deadlock;

import com.mycompany.pandemysimulation.Direction;
import com.mycompany.pandemysimulation.core.Location;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author kacper
 */
public class DeadlockFinder {
    boolean[][][] directions;
    Location[][] locations;
    int sizeX;
    int sizeY;
    boolean[][] visited;
    
    public DeadlockFinder(boolean[][][] directions, Location[][] locations){
        this.directions = directions;
        this.locations = locations;
        this.sizeY = directions.length;
        this.sizeX = locations.length;
    }
    
    
    public List<Location> findDeadlock(){
        visited = new boolean[sizeY][sizeX];
        for(int y=0;y<sizeY;y++){
            for(int x = 0; x<sizeX;x++){
                if(visited[y][x]) continue;
                List<Location> result = DFS(locations[y][x]);
                if(result!=null){
                    return finCycleInResult(result);
                }
            }
        }
        return null;
    }
    
    private List<Location> DFS(Location location){
        HashSet<Location> currentBranch  = new HashSet<>();
        Stack<Location> stack = new Stack<>();
        currentBranch.add(location);
        stack.add(location);
        while(!stack.empty()){
            Location current = stack.peek();
            List<Direction> actions = getActions(current);
            boolean didBreak = false;
            for(Direction action : actions){
                Location nextLocation = locations[current.getIdY()+action.dY()][current.getIdX()+action.dX()];
                if(currentBranch.contains(nextLocation)){
                    stack.add(nextLocation);
                    return new LinkedList<>(stack);
                }
                if( !visited[nextLocation.getIdY()][nextLocation.getIdX()]){
                    stack.add(nextLocation);
                    currentBranch.add(nextLocation);
                    didBreak=true;
                    visited[nextLocation.getIdY()][nextLocation.getIdX()]=true;
                    break;
                }
            }
            if(!didBreak){
                stack.pop();
                currentBranch.remove(current);
            }
        }
        return null;
    }
    
    private List<Location> finCycleInResult(List<Location> result){
//        System.out.println(result);
        Location last = result.remove(0);
        List<Location> finalResult= new LinkedList<Location>();
        finalResult.add(last);
        for(Location location:result){
            if(location != last){
                finalResult.add(location);
            }else{
                break;
            }
        }
        Collections.reverse(finalResult);
        return finalResult;
    }
    
    
    private List<Direction> getActions(Location lastLocation){
        boolean[] moves = directions[lastLocation.getIdY()][lastLocation.getIdX()];
        List<Direction> actions = new LinkedList<>();
        for(int i =0; i< 4;i++){
            if(moves[i])actions.add(Direction.valToDirection(i));
        }
        return actions;
    }
}
