/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core.map;

import com.mycompany.pandemysimulation.core.Manager;

/**
 * Map Manager class stores map and takes care of organizing map related actions like pathfinding and deadlock detection
 * 
 * @author kacper
 */
public abstract class AbstractMapManager extends Manager{
    
    private Map map;
    private PathFinder pedestrianPathFinder;
    private PathFinder roadPathFinder;
    private DeadlockFinder pedestrianDeadlockFinder;
    private DeadlockFinder roadDeadlockFinder;
    
    /**
     *
     * Class constructor
     * 
     * @param map
     */
    public AbstractMapManager(Map map) {
        this.map = map;
        this.pedestrianPathFinder = new PathFinder(map.getPedestrianDirections(), map.getLocations());
        this.roadPathFinder = new PathFinder(map.getRoadDirections(), map.getLocations());
        this.pedestrianDeadlockFinder = new DeadlockFinder(map.getPedestrianDirections(), map.getLocations());
        this.roadDeadlockFinder = new DeadlockFinder(map.getRoadDirections(), map.getLocations());
    }
    
    /**
     *
     * @return returns map
     */
    public Map getMap() {
        return map;
    }

    /**
     *
     * @return returns an instance of PathFinder for agents that are pedestrians 
     */
    public PathFinder getPedestrianPathFinder() {
        return pedestrianPathFinder;
    }

    /**
     *
     * @return returns an instance of PathFinder for agents that move on roads
     */
    public PathFinder getRoadPathFinder() {
        return roadPathFinder;
    }

    /**
     *
     * @return returns an instance of DeadlockFinder for agents that are pedestrians
     */
    protected DeadlockFinder getPedestrianDeadlockFinder() {
        return pedestrianDeadlockFinder;
    }

    /**
     *
     * @return returns an instance of DeadlockFinder for agents that move using roads
     */
    protected DeadlockFinder getRoadDeadlockFinder() {
        return roadDeadlockFinder;
    }
    
    
}
