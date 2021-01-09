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
public class Map {
    
}
//
//enum TileType{
//    R,
//    RI,
//    P,
//    PI
//}
//
//
//
//class Tile extends SimulationObject implements Location{  
//    public static final int tileSize = 40;
//    private static VisibleComponent getVisibleComponent(TileType tileType){
//        String imageName;
//        if(tileType == TileType.P || tileType == TileType.PI){
//           imageName = "pavement.jpg";
//        }else{
//            imageName = "asphalt.jpg";
//        }
//        
//        return new VisibleComponent(imageName, tileSize, tileSize);
//    }
//    
//    int idX;
//    int idY;
//    
//    ReentrantLock lock;
//    
//    TileType tileType;
//    
//    public Tile(int idX, int idY, TileType tileType){
//        this.idX = idX;
//        this.idY = idY;
//        
//        this.xPos = Coordinates.mapToWorld(idX);
//        this.yPos = Coordinates.mapToWorld(idY);
//        this.tileType = tileType;
//        this.visibleComponent = 
//        lock = new ReentrantLock();
//    }
//    
//    public void enter(){
//        lock.lock();
//    }
//    
//    public synchronized void leave(){
//        lock.unlock();
//    }
//    
//
//    public double getXPos() {
//        return xPos;
//    }
//
//    public double getYPos() {
//        return yPos;
//    }
//
//    public int getIdX() {
//        return idX;
//    }
//
//    public int getIdY() {
//        return idY;
//    }
//    
//    
//    
//}
