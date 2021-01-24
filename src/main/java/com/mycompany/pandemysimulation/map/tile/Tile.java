/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.map.tile;

import com.mycompany.pandemysimulation.App;
import com.mycompany.pandemysimulation.core.SimulationObject;
import com.mycompany.pandemysimulation.core.ThreadAgent;
import com.mycompany.pandemysimulation.core.map.Location;
import com.mycompany.pandemysimulation.core.ui.VisibleComponent;
import com.mycompany.pandemysimulation.core.utils.Coordinates;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import javafx.scene.image.Image;

/**
 *
 * @author kacper
 */
public class Tile extends SimulationObject implements Location {

    private static Image pavementImage = new Image(App.class.getResource("image/" + "pavement.jpg").toString(), Coordinates.mapTileSize, Coordinates.mapTileSize, false, false);
    private static Image asphaltImage = new Image(App.class.getResource("image/" + "asphalt.png").toString(), Coordinates.mapTileSize, Coordinates.mapTileSize, false, false);
    private static Image yellowGrassImage = new Image(App.class.getResource("image/" + "yellow_grass.jpg").toString(), Coordinates.mapTileSize, Coordinates.mapTileSize, false, false);

    private static VisibleComponent getVisibleComponent(TileType tileType) {
        Image image;
        if (tileType == TileType.P || tileType == TileType.PI) {
            image = pavementImage;
        } else if (tileType == TileType.R || tileType == TileType.RI) {
            image = asphaltImage;
        } else {
            image = yellowGrassImage;
        }

        return new VisibleComponent(image, Coordinates.mapTileSize, Coordinates.mapTileSize);
    }

    private int idX;
    private int idY;

    private ReentrantLock lock;

    private TileType tileType;

    public Tile(int idX, int idY, TileType tileType) {
        super(Coordinates.mapToWorld(idX), Coordinates.mapToWorld(idY), getVisibleComponent(tileType));
        this.idX = idX;
        this.idY = idY;
        this.tileType = tileType;
        lock = new ReentrantLock();
    }

    @Override
    public void enter(ThreadAgent threadAgent) throws InterruptedException {
        lock.lockInterruptibly();
    }

    @Override
    public void leave(ThreadAgent threadAgent) {
        lock.unlock();
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

    @Override
    public boolean shouldGoThrough() {
        return true;
    }

}