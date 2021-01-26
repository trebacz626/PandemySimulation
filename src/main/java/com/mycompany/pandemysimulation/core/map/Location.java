/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core.map;

import com.mycompany.pandemysimulation.core.ThreadAgent;
import java.util.List;

/**
 * Represents a location on the map
 * @author kacper
 */
public interface Location {

    /**
     *
     * @return Returns X coordinate of location
     */
    public int getCoordX();

    /**
     *  
     * @return Returns X coordinate of location
     */
    public int getCoordY();

    /**
     *
     * Method that must by called by ThreadAgent to enter a location synchronously
     * 
     * @param threadAgent
     * @throws InterruptedException
     */
    public void enter(ThreadAgent threadAgent) throws InterruptedException;

    /**
     * Method that must by called by ThreadAgent to leave a location synchronously
     * @param threadAgent
     */
    public void leave(ThreadAgent threadAgent);

    /**
     * Sometimes Location get be grouped. This group can be accesed by this method
     * @return a list of Locations that are grouped with current one
     */
    public List<Location> getGroup();

    /**
     * Returns a boolean value telling, whether an agent should go through this location when going to a different one
     * @return 
     */
    public boolean shouldGoThrough();
}
