/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.map;

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
    boolean[][][] occupied;
    Location[][] locations;
    int sizeX;
    int sizeY;
    boolean[][] visited;
    
    public DeadlockFinder(boolean[][][] directions, Location[][] locations){
        this.directions = directions;
        this.locations = locations;
        this.sizeY = locations.length;
        this.sizeX = locations[0].length;
        this.occupied = new boolean[sizeY][sizeX][4];
    }
    
    public void reset(){
        for(int y=0;y<sizeY;y++){
            for(int x = 0; x<sizeX;x++){
                for(int i=0;i<4;i++){
                    occupied[y][x][i]=false;
                }
            }
        }
    }
    
    public void addEdge(Location next, Location current){
        if(next == null || current == null || next == current)
            return;
        Direction direction = Direction.dXdYTodirection(next.getIdX()-current.getIdX(), next.getIdY() - current.getIdY());
        occupied[current.getIdY()][current.getIdX()][direction.getVal()] = true && directions[current.getIdY()][current.getIdX()][direction.getVal()];
    }
    
    public List<Location> findDeadlock(){
        visited = new boolean[sizeY][sizeX];
        for(int y=0;y<sizeY;y++){
            for(int x = 0; x<sizeX;x++){
                if(visited[y][x]) continue;
                List<Node> result = DFS(locations[y][x]);
                if(result!=null){
                    return transform(finCycleInResult(result));
                }
            }
        }
        return new LinkedList<>();
    }
    
    private List<Node> DFS(Location location){
        HashSet<Location> currentBranch  = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        Node initNode = new Node(location.getGroup());
        initNode.setChildren(this.getDescendantLocations(initNode));
        currentBranch.addAll(initNode.getLocations());
        stack.add(initNode);
        while(!stack.empty()){
            Node current = stack.peek();
            List<Location> descendants = current.getChildren();
            boolean didBreak = false;
            for(Location nextLocation: descendants){
                if(currentBranch.contains(nextLocation)){
                    stack.add(new Node(nextLocation.getGroup()));
                    Collections.reverse(stack);
                    return stack;
                }
                if( !visited[nextLocation.getIdY()][nextLocation.getIdX()]){
                    Node newNode = new Node(nextLocation.getGroup());
                    newNode.setChildren(getDescendantLocations(newNode));
                    stack.add(newNode);
                    currentBranch.addAll(newNode.getLocations());
                    for(Location nextLocationGroupmate: newNode.getLocations()){
                        visited[nextLocationGroupmate.getIdY()][nextLocationGroupmate.getIdX()]=true;
                    }
                    didBreak=true;
                    break;
                }
            }
            if(!didBreak){
                stack.pop();
                currentBranch.removeAll(current.getLocations());
            }
        }
        return null;
    }
    
    private List<Location> getDescendantLocations(Node node){
        List<Location> result = new LinkedList<>();
        for(Location location: node.getLocations()){
            for(Direction action : getActions(location)){
                Location nextLocation = locations[location.getIdY()+action.dY()][location.getIdX()+action.dX()];
                if(!node.getLocations().contains(nextLocation) && !node.getLocations().contains(nextLocation)){
                    result.add(nextLocation);
                }
            }
        }
        return result;
    }
    
    private List<Node> finCycleInResult(List<Node> result){
        Node last = result.remove(0);
        List<Node> finalResult= new LinkedList<>();
        finalResult.add(last);
        for(Node node:result){
            if(node.getIdx() != last.getIdx() || node.getIdy() != last.getIdy()){
                finalResult.add(node);
            }else{
                break;
            }
        }
        Collections.reverse(finalResult);
        return finalResult;
    }
    
    private List<Location> transform(List<Node> nodes){
        List<Location> result = new LinkedList<>();
        for(Node node: nodes){
            result.addAll(node.getLocations());
        }
        return result;
    }
    
    
    private List<Direction> getActions(Location lastLocation){
        boolean[] moves = occupied[lastLocation.getIdY()][lastLocation.getIdX()];
        List<Direction> actions = new LinkedList<>();
        for(int i =0; i< 4;i++){
            if(moves[i])actions.add(Direction.valToDirection(i));
        }
        return actions;
    }
}
