/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core.utils;

/**
 *
 * Class that helps calculating coordinates from map to World and from World to  UI
 * 
 * @author kacper
 */
public final class Coordinates {

    /**
     * Size of 1 square on map in real world coordinates
     *
     */
    public static final int mapUnitSize = 30;
    private Coordinates(){
    }
    
    /**
     *
     * converts World coordinate X to UI coordinate X
     * 
     * @param x world coordinate x
     * @return
     */
    public static double toUIX(double x){
        return x;
    }
    
    /**
     *
     * converts World coordinate Y to UI coordinate Y
     * 
     * @param y world coordinate y
     * @return
     */
    public static double toUIY(double y){
        return y;
    }
    
    /**
     *
     * Converts Map coordinate to world coordinate
     * 
     * @param coordX
     * @return
     */
    public static double mapToWorld(double coordX){
        return mapUnitSize/2 + mapUnitSize*coordX;
    }
}
