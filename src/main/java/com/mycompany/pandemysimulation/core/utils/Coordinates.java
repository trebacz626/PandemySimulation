/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core.utils;

/**
 *
 * @author kacper
 */
public final class Coordinates {
    public static final int mapTileSize = 30;
    private Coordinates(){
    }
    
    public static double toUIX(double x){
        return x;
    }
    
    public static double toUIY(double y){
        return y;
    }
    
    public static double toWorldX(double x){
        return x;
    }
    
    public static double toWorldY(double y){
        return y;
    }
    
    public static double mapToWorld(double idX){
        return mapTileSize/2 + mapTileSize*idX;
    }
}
