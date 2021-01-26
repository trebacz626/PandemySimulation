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
public class Map {
    
    private int sizeX;
    private int sizeY;
    private Location[][] locations;
    private boolean[][][] pedestrianDirections;
    private boolean[][][] roadDirections;
    private Location spawnPointPedestrian;
    private Location spawnPointRoad;

    /**
     *
     * Constructor
     * 
     * @param sizeX size of map in X axis
     * @param sizeY size of map in Y axis
     * @param locations 2d array of Locations
     * @param pedestrianDirections 3d boolean array representing pedestrians directions each location on map
     * @param roadDirections 3d boolean array representing road directions each location on map
     * @param spawnPointPedestrian spawn point for pedestrian agents
     * @param spawnPointRoad spawn point for road moving agents 
     */
    public Map(int sizeX, int sizeY, Location[][] locations, boolean[][][] pedestrianDirections, boolean[][][] roadDirections, Location spawnPointPedestrian, Location spawnPointRoad) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.locations = locations;
        this.pedestrianDirections = pedestrianDirections;
        this.roadDirections = roadDirections;
        this.spawnPointPedestrian = spawnPointPedestrian;
        this.spawnPointRoad = spawnPointRoad;
    }

    /**
     *
     * 
     * @return size of map in x coordinate
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     *
     * 
     * @return size of map in y coordinate
     */
    public int getSizeY() {
        return sizeY;
    }
    
    /**
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return
     */
    public Location getAt(int x, int y){
        return locations[y][x];
    }
    
    /**
     *
     * @return spawn point for pedestrian agents
     */
    public Location getSpawnPointPedestrian(){
        return spawnPointPedestrian;
    }
    
    /**
     *
     * @return spawn point for road agents
     */
    public Location getSpawnPointRoad(){
        return spawnPointRoad;
    }
    
    /**
     *
     * @return 2d array of locations representing map 
     */
    protected Location[][] getLocations() {
        return locations;
    }
    
    /**
     *
     * @return array of directions for pedestrian agents
     */
    protected boolean[][][] getPedestrianDirections(){
        return pedestrianDirections;
    }
    
    /**
     *
     * @return array of directions for road agents
     */
    protected boolean[][][] getRoadDirections(){
        return roadDirections;
    }
    
}
