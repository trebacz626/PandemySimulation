
package com.mycompany.pandemysimulation.map.tile;

import com.mycompany.pandemysimulation.core.SimulationObject;
import com.mycompany.pandemysimulation.core.ThreadAgent;
import com.mycompany.pandemysimulation.core.map.Location;
import com.mycompany.pandemysimulation.core.ui.VisibleComponent;
import com.mycompany.pandemysimulation.core.utils.Coordinates;
import com.mycompany.pandemysimulation.ui.UIManager;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import javafx.scene.image.Image;

/**
 *
 * A synchronized location that ensures only one agent enters an intersection.
 * 
 * @author kacper
 */
public class Tile extends SimulationObject implements Location {

    private static Image pavementImage = UIManager.loadImage("pavement.jpg", Coordinates.mapUnitSize, Coordinates.mapUnitSize);
    private static Image asphaltImage = UIManager.loadImage("asphalt.png", Coordinates.mapUnitSize, Coordinates.mapUnitSize);
    private static Image yellowGrassImage = UIManager.loadImage("yellow_grass.jpg", Coordinates.mapUnitSize, Coordinates.mapUnitSize);

    private static VisibleComponent getVisibleComponent(TileType tileType) {
        Image image;
        if (tileType == TileType.P || tileType == TileType.PI) {
            image = pavementImage;
        } else if (tileType == TileType.R || tileType == TileType.RI) {
            image = asphaltImage;
        } else {
            image = yellowGrassImage;
        }

        return new VisibleComponent(image, Coordinates.mapUnitSize, Coordinates.mapUnitSize);
    }

    private int coordX;
    private int coordY;

    private ReentrantLock lock;

    private TileType tileType;

    /**
     *
     * @param coordX    coordinate x
     * @param coordY    coordinate y
     * @param tileType  type of a tile
     */
    public Tile(int coordX, int coordY, TileType tileType) {
        super(Coordinates.mapToWorld(coordX), Coordinates.mapToWorld(coordY), getVisibleComponent(tileType));
        this.coordX = coordX;
        this.coordY = coordY;
        this.tileType = tileType;
        lock = new ReentrantLock();
    }

    /**
     *
     * Locks a tile.
     * 
     * @param threadAgent
     * @throws InterruptedException
     */
    @Override
    public void enter(ThreadAgent threadAgent) throws InterruptedException {
        lock.lockInterruptibly();
    }

    /**
     *
     * Unlocks a tile.
     * 
     * @param threadAgent
     */
    @Override
    public void leave(ThreadAgent threadAgent) {
        lock.unlock();
    }

    /**
     *
     * Returns x coordinate.
     * 
     * @return
     */
    @Override
    public int getCoordX() {
        return coordX;
    }

    /**
     *
     * Returns y coordinate
     * 
     * @return
     */
    @Override
    public int getCoordY() {
        return coordY;
    }

    /**
     *
     * Returns tile group in this situation just itself.
     * 
     * @return
     */
    @Override
    public List<Location> getGroup() {
        return Collections.singletonList(this);
    }

    /**
     *
     * Tells if agent should go through this location
     * 
     * @return true
     */
    @Override
    public boolean shouldGoThrough() {
        return true;
    }

}
