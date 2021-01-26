/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core.map;

/**
 * Represents direction on map
 * @author kacper
 */
public enum Direction{

    /**
     * Upward direction
     */
    Up(0,0,-1),

    /**
     * Downward direction
     */
    Down(1,0,1),

    /**
     * Left direction
     */
    Left(2,-1,0),

    /**
     * Right direction
     */
    Right(3,1,0);

    /**
     * returns 
     * @param val
     * @return
     */
    static Direction indexToDirection(int val){
        switch(val){
            case 0:
                return Up;
            case 1:
                return Down;
            case 2:
                return Left;
            case 3:
                return Right;
            default:
                throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     *
     * @param dx
     * @param dy
     * @return
     */
    static Direction dXdYTodirection(int dx, int dy){
        for(Direction d: Direction.values()){
            if(dx == d.dx && dy == d.dy){
                return d;
            }
        }
        throw new ArrayIndexOutOfBoundsException("No such a direction");
    }
    
    
    private int index;
    private int dx;
    private int dy;
    
    
    private Direction(int index, int dx, int dy){
        this.index = index;
        this.dx = dx;
        this.dy = dy;
    }
    
    /**
     * returns an index in boolean array representing directions from a location
     * @return
     */
    public int getIndex(){
        return index;
    }

    int dX(){
        return dx;
    }

    int dY(){
        return dy;
    }
}
