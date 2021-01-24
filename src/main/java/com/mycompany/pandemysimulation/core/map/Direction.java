/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core.map;

/**
 *
 * @author kacper
 */
public enum Direction{
    Up(0,0,-1), Down(1,0,1), Left(2,-1,0), Righ(3,1,0);

    public static Direction indexToDirection(int val){
        switch(val){
            case 0:
                return Up;
            case 1:
                return Down;
            case 2:
                return Left;
            case 3:
                return Righ;
            default:
                throw new ArrayIndexOutOfBoundsException();
        }
    }
    public static Direction dXdYTodirection(int dx, int dy){
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
    
    public int getIndex(){
        return index;
    }
    
    public int dX(){
        return dx;
    }
    
    public int dY(){
        return dy;
    }
}
