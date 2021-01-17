/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.map;

/**
 *
 * @author kacper
 */
public class Map {
    
    private int sizeX;
    private int sizeY;
    private Location[][] locations;
    private boolean[][][] pedestrianDirections;
    private boolean[][][] roadDirections;

    public Map(int sizeX, int sizeY, Location[][] locations, boolean[][][] pedestrianDirections, boolean[][][] roadDirections) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.locations = locations;
        this.pedestrianDirections = pedestrianDirections;
        this.roadDirections = roadDirections;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
    
    public Location getAt(int x, int y){
        return locations[y][x];
    }

    public Location[][] getLocations() {
        return locations;
    }
    
    protected boolean[][][] getPedestrianDirections(){
        return pedestrianDirections;
    }
    
    protected boolean[][][] getRoadDirections(){
        return roadDirections;
    }
    
}
