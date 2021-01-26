/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.map;

/**
 *
 * An abstract class used to store the build process of map.
 * 
 * @author kacper
 */
public abstract class MapCreator {

    /**
     *  
     * Map definition represented as operations on map builder.
     * 
     * @return
     */
    public abstract MapBuilder getMapBuilder();
}
