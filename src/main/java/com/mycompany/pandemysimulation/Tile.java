/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author kacper
 */
class Tile extends SimulationObject implements Location{  
    public static final int tileSize = 30;
    private static VisibleComponent getVisibleComponent(TileType tileType){
        String imageName;
        if(tileType == TileType.P || tileType == TileType.PI){
           imageName = "pavement.jpg";
        }else if(tileType == TileType.R || tileType == TileType.RI){
            imageName = "asphalt.png";
        }else{
            imageName = "yellow_grass.jpg";
        }
        
        return new VisibleComponent(imageName, tileSize, tileSize);
    }
    
    int idX;
    int idY;
    
    ReentrantLock lock;
    
    TileType tileType;
    
    public Tile(int idX, int idY, TileType tileType){
        super(Coordinates.mapToWorld(idX), Coordinates.mapToWorld(idY), getVisibleComponent(tileType));
        this.idX = idX;
        this.idY = idY;
        this.tileType = tileType;
        lock = new ReentrantLock();
    }
    
    public void enter(ThreadAgent threadAgent){
        lock.lock();
    }
    
    public void leave(ThreadAgent threadAgent){
        lock.unlock();
    }
    

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public int getIdX() {
        return idX;
    }

    public int getIdY() {
        return idY;
    }  

    @Override
    public String toString() {
        return "Tile{" + "idX=" + idX + ", idY=" + idY + ", lock=" + lock + ", tileType=" + tileType + '}';
    }
    
    
}
