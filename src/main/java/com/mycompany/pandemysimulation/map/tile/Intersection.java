/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.map.tile;

import com.mycompany.pandemysimulation.core.SimulationObject;
import com.mycompany.pandemysimulation.core.ThreadAgent;
import com.mycompany.pandemysimulation.core.map.Location;
import com.mycompany.pandemysimulation.core.ui.VisibleComponent;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * A synchronizer that ensures that at a given group of tiles associated with it
 * there is at most one agent
 *
 * @author kacper
 */
public class Intersection extends SimulationObject {

    private ReentrantLock lock;
    private List<Location> tiles;

    /**
     *
     * Constructor
     * 
     * @param xPos pos x of agent
     * @param yPos pos y of agent
     * @param visibleComponent visible component of agent
     */
    public Intersection(double posX, double posY, VisibleComponent visibleComponent) {
        super(posX, posY, visibleComponent);
        lock = new ReentrantLock();
        tiles = new LinkedList<Location>();
    }

    /**
     *
     * Locks intersection.
     * 
     * @param threadAgent
     * @throws InterruptedException
     */
    protected void enter(ThreadAgent threadAgent) throws InterruptedException {
        lock.lockInterruptibly();
    }

    /**
     *
     * Unlocks intersection.
     * 
     * @param threadAgent
     */
    protected void leave(ThreadAgent threadAgent) {
        lock.unlock();
    }

    /**
     *
     * Add tile to intersection
     * 
     * @param tile
     */
    protected void addTile(IntersectionTile tile) {
        tiles.add(tile);
    }

    /**
     *
     * Returns Tiles belonging to the intersection
     * 
     * @return
     */
    protected List<Location> getTiles() {
        return tiles;
    }

}
