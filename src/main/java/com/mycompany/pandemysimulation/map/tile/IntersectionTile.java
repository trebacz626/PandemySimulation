/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.map.tile;

import com.mycompany.pandemysimulation.core.ThreadAgent;
import com.mycompany.pandemysimulation.core.map.Location;
import java.util.List;

/**
 *
 * A tile that is connected with an intersection.
 * 
 * @author kacper
 */
public class IntersectionTile extends Tile {

    private Intersection intersection;

    /**
     *
     * Constructor
     * 
     * @param coordX x coordinate
     * @param coordY y coordinate
     * @param tileType the type of file
     * @param intersection
     */
    public IntersectionTile(int coordX, int coordY, TileType tileType, Intersection intersection) {
        super(coordX, coordY, tileType);
        this.intersection = intersection;
        intersection.addTile(this);
    }

    /**
     *
     * Lock a tile.
     * 
     * @param threadAgent
     * @throws InterruptedException
     */
    @Override
    public void enter(ThreadAgent threadAgent) throws InterruptedException {
        intersection.enter(threadAgent);
        super.enter(threadAgent);
    }

    /**
     *
     * Unlock a tile.
     * 
     * @param threadAgent
     */
    @Override
    public void leave(ThreadAgent threadAgent) {
        intersection.leave(threadAgent);
        super.leave(threadAgent);
    }

    /**
     *
     * Returns all tiles in a group.
     * 
     * @return
     */
    @Override
    public List<Location> getGroup() {
        return intersection.getTiles();
    }
}
