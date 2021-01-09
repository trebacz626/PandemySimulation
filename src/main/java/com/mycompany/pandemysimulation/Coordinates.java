/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

/**
 *
 * @author kacper
 */
public final class Coordinates {
    
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
    
    public static double mapToWorld(int idX){
        return Tile.tileSize/2 + Tile.tileSize*idX;
    }
}
