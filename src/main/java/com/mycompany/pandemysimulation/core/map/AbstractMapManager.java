/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core.map;

import com.mycompany.pandemysimulation.core.Manager;

/**
 *
 * @author kacper
 */
public abstract class AbstractMapManager extends Manager{
    
    private Map map;
    private PathFinder pedestrianPathFinder;
    private PathFinder roadPathFinder;
    private DeadlockFinder pedestrianDeadlockFinder;
    private DeadlockFinder roadDeadlockFinder;
    
    public AbstractMapManager(Map map) {
        this.map = map;
        this.pedestrianPathFinder = new PathFinder(map.getPedestrianDirections(), map.getLocations());
        this.roadPathFinder = new PathFinder(map.getRoadDirections(), map.getLocations());
        this.pedestrianDeadlockFinder = new DeadlockFinder(map.getPedestrianDirections(), map.getLocations());
        this.roadDeadlockFinder = new DeadlockFinder(map.getRoadDirections(), map.getLocations());
    }
    
    public Map getMap() {
        return map;
    }

    public PathFinder getPedestrianPathFinder() {
        return pedestrianPathFinder;
    }

    public PathFinder getRoadPathFinder() {
        return roadPathFinder;
    }

    protected DeadlockFinder getPedestrianDeadlockFinder() {
        return pedestrianDeadlockFinder;
    }

    protected DeadlockFinder getRoadDeadlockFinder() {
        return roadDeadlockFinder;
    }
    
    
}
