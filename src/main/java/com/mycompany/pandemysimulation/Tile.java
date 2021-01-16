/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import com.mycompany.pandemysimulation.core.Location;
import com.mycompany.pandemysimulation.core.SimulationObject;
import com.mycompany.pandemysimulation.core.ThreadAgent;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import javafx.scene.image.Image;

/**
 *
 * @author kacper
 */
class Tile extends SimulationObject implements Location{  
    public static final int tileSize = 30;
    private static Image pavementImage = new Image(App.class.getResource("image/"+"pavement.jpg").toString(), tileSize, tileSize, false, false);
    private static Image asphaltImage = new Image(App.class.getResource("image/"+"asphalt.png").toString(), tileSize, tileSize, false, false);
    private static Image yellowGrassImage = new Image(App.class.getResource("image/"+"yellow_grass.jpg").toString(), tileSize, tileSize, false, false);
    private static VisibleComponent getVisibleComponent(TileType tileType){
        Image image;
        if(tileType == TileType.P || tileType == TileType.PI){
           image = pavementImage;
        }else if(tileType == TileType.R || tileType == TileType.RI){
            image = asphaltImage;
        }else{
            image = yellowGrassImage;
        }
        
        return new VisibleComponent(image, tileSize, tileSize);
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

    @Override
    public List<Location> getGroup() {
        return Collections.singletonList(this);
    }
    
    
}
