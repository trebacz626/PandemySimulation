/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.map;

import com.mycompany.pandemysimulation.map.tile.Tile;
import com.mycompany.pandemysimulation.map.tile.IntersectionTile;
import com.mycompany.pandemysimulation.map.tile.TileType;
import com.mycompany.pandemysimulation.map.tile.Intersection;
import com.mycompany.pandemysimulation.App;
import com.mycompany.pandemysimulation.core.SimulationObject;
import com.mycompany.pandemysimulation.core.map.Direction;
import com.mycompany.pandemysimulation.core.map.Location;
import com.mycompany.pandemysimulation.core.map.Map;
import com.mycompany.pandemysimulation.core.ui.VisibleComponent;
import com.mycompany.pandemysimulation.core.utils.Coordinates;
import com.mycompany.pandemysimulation.shop.Shop;
import com.mycompany.pandemysimulation.shop.retailshop.RetailShop;
import com.mycompany.pandemysimulation.shop.retailshop.RetailShopFactory;
import com.mycompany.pandemysimulation.shop.wholesaleshop.WholesaleShop;
import com.mycompany.pandemysimulation.shop.wholesaleshop.WholesaleShopFactory;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author kacper
 */
public class MapBuilder {

    private int sizeX;
    private int sizeY;
    private Location[][] locationMap;
    private boolean[][][] pedestrianDirection;
    private boolean[][][] suppliersDirections;
    private List<Intersection> intersections;
    private List<SimulationObject> simulationObjects;
    private List<Shop> shops;
    private Location spawnPointPedestrian;
    private Location spawnPointRoad;

    public MapBuilder(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        locationMap = new Location[sizeY][sizeX];
        pedestrianDirection = new boolean[sizeY][sizeX][4];
        suppliersDirections = new boolean[sizeY][sizeX][4];
        intersections = new LinkedList<>();
        simulationObjects = new LinkedList<>();
        shops = new LinkedList<>();
    }

    public MapBuilder addTwoWayPavementX(int startX, int endX, int y) {
        addPavementX(startX, endX, y + 1);
        addPavementX(endX, startX, y);
        return this;
    }

    public MapBuilder addTwoWayPavementY(int startY, int endY, int x) {
        addPavementY(startY, endY, x);
        addPavementY(endY, startY, x + 1);
        return this;
    }

    public MapBuilder addPavementX(int startX, int endX, int y) {
        List<Direction> directions;
        if (endX > startX) {
            directions = Arrays.asList(Direction.Righ);
        } else {
            directions = Arrays.asList(Direction.Left);
            int temp = startX;
            startX = endX;
            endX = temp;
        }

        for (int x = startX; x <= endX; x++) {
            addOnePavement(x, y, directions);
        }
        return this;
    }

    public MapBuilder addPavementY(int startY, int endY, int x) {
        List<Direction> directions;
        if (endY > startY) {
            directions = Arrays.asList(Direction.Down);
        } else {
            directions = Arrays.asList(Direction.Up);
            int temp = startY;
            startY = endY;
            endY = temp;
        }
        for (int y = startY; y <= endY; y++) {
            addOnePavement(x, y, directions);
        }
        return this;
    }

    public MapBuilder addOnePavement(int x, int y, List<Direction> directions) {
        locationMap[y][x] = new Tile(x, y, TileType.P);
        for (Direction direction : directions) {
            pedestrianDirection[y][x][direction.getIndex()] = true;
        }
        return this;
    }

    public MapBuilder add2x2PavementIntersection(int x1, int y1) {
        int x2 = x1 + 1;
        int y2 = y1 + 1;
        Intersection intersection = new Intersection(Coordinates.mapToWorld((x1 + x2) / 2), Coordinates.mapToWorld((y1 + y2) / 2), new VisibleComponent(new Image(App.class.getResource("image/" + "stoplight.jpg").toString(), sizeX, sizeY, false, false), 10, 10));
        intersections.add(intersection);
        for (int y = y1; y <= y2; y++) {
            for (int x = x1; x <= x2; x++) {
                for (int i = 0; i < 4; i++) {
                    pedestrianDirection[y][x][i] = true;
                }
                locationMap[y][x] = new IntersectionTile(x, y, TileType.PI, intersection);
            }
        }
        //upper left
        pedestrianDirection[y1][x1][Direction.Up.getIndex()] = false;
        pedestrianDirection[y1][x1][Direction.Left.getIndex()] = x1 > 0;
        //down left
        pedestrianDirection[y2][x1][Direction.Left.getIndex()] = false;
        pedestrianDirection[y2][x1][Direction.Down.getIndex()] = y2 < (sizeY - 1);
        //upper right
        pedestrianDirection[y1][x2][Direction.Righ.getIndex()] = false;
        pedestrianDirection[y1][x2][Direction.Up.getIndex()] = y1 > 0;
        //lower right
        pedestrianDirection[y2][x2][Direction.Down.getIndex()] = false;
        pedestrianDirection[y2][x2][Direction.Righ.getIndex()] = x2 < (sizeX - 1);
        return this;
    }

    public MapBuilder addPavementDirection(int x, int y, Direction direction) {
        pedestrianDirection[y][x][direction.getIndex()] = true;
        return this;
    }

    //Road
    public MapBuilder addTwoWayRoadX(int startX, int endX, int y) {
        addRoadX(startX, endX, y + 1);
        addRoadX(endX, startX, y);
        return this;
    }

    public MapBuilder addTwoWayRoadY(int startY, int endY, int x) {
        addRoadY(startY, endY, x);
        addRoadY(endY, startY, x + 1);
        return this;
    }

    public MapBuilder addRoadX(int startX, int endX, int y) {
        List<Direction> directions;
        if (endX > startX) {
            directions = Arrays.asList(Direction.Righ);
        } else {
            directions = Arrays.asList(Direction.Left);
            int temp = startX;
            startX = endX;
            endX = temp;
        }
        for (int x = startX; x <= endX; x++) {
            addOneRoad(x, y, directions);
        }
        return this;
    }

    public MapBuilder addRoadY(int startY, int endY, int x) {
        List<Direction> directions;
        if (endY > startY) {
            directions = Arrays.asList(Direction.Down);
        } else {
            directions = Arrays.asList(Direction.Up);
            int temp = startY;
            startY = endY;
            endY = temp;
        }
        for (int y = startY; y <= endY; y++) {
            addOneRoad(x, y, directions);
        }
        return this;
    }

    public MapBuilder addOneRoad(int x, int y, List<Direction> directions) {
        locationMap[y][x] = new Tile(x, y, TileType.R);
        for (Direction direction : directions) {
            suppliersDirections[y][x][direction.getIndex()] = true;
        }
        return this;
    }

    public MapBuilder addRoadDirection(int x, int y, Direction direction) {
        suppliersDirections[y][x][direction.getIndex()] = true;
        return this;
    }

    public MapBuilder add2x2RoadIntersection(int x1, int y1) {
        int x2 = x1 + 1;
        int y2 = y1 + 1;
        Intersection intersection = new Intersection(Coordinates.mapToWorld((x1 + x2) / 2), Coordinates.mapToWorld((y1 + y2) / 2), new VisibleComponent(new Image(App.class.getResource("image/" + "stoplight.jpg").toString(), sizeX, sizeY, false, false), 10, 10));
        intersections.add(intersection);
        for (int y = y1; y <= y2; y++) {
            for (int x = x1; x <= x2; x++) {
                for (int i = 0; i < 4; i++) {
                    suppliersDirections[y][x][i] = true;
                }
                locationMap[y][x] = new IntersectionTile(x, y, TileType.RI, intersection);
            }
        }
        //upper left
        suppliersDirections[y1][x1][Direction.Up.getIndex()] = false;
        suppliersDirections[y1][x1][Direction.Left.getIndex()] = x1 > 0;
        //down left
        suppliersDirections[y2][x1][Direction.Left.getIndex()] = false;
        suppliersDirections[y2][x1][Direction.Down.getIndex()] = y2 < (sizeY - 1);
        //upper right
        suppliersDirections[y1][x2][Direction.Righ.getIndex()] = false;
        suppliersDirections[y1][x2][Direction.Up.getIndex()] = y1 > 0;
        //lower right
        suppliersDirections[y2][x2][Direction.Down.getIndex()] = false;
        suppliersDirections[y2][x2][Direction.Righ.getIndex()] = x2 < (sizeX - 1);
        return this;
    }

    public MapBuilder addRetailShop(int x, int y, String name, String address) {
        RetailShop shop = RetailShopFactory.createRetailShop(name, address, x, y, "retail.png");
        locationMap[y][x] = (Location) shop;
        shops.add(shop);
        return this;
    }

    public MapBuilder addWholesaleShop(int x, int y, String name, String address) {
        WholesaleShop shop = WholesaleShopFactory.createWholesaleShop(name, address, x, y, "wholesale1.png");
        locationMap[y][x] = (Location) shop;
        shops.add(shop);
        return this;
    }

    public MapBuilder markSpawnPointPedestrian(int x, int y) {
        spawnPointPedestrian = locationMap[y][x];
        return this;
    }

    public MapBuilder markSpawnPointRoad(int x, int y) {
        spawnPointRoad = locationMap[y][x];
        return this;
    }

    public Map build() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                if (locationMap[y][x] == null) {
                    locationMap[y][x] = new Tile(x, y, TileType.G);
                }
                simulationObjects.add((SimulationObject) locationMap[y][x]);
            }
        }
        simulationObjects.addAll(intersections);

        for (int i = 0; i < sizeX; i++) {
            pedestrianDirection[0][i][Direction.Up.getIndex()] = false;
            pedestrianDirection[sizeY - 1][i][Direction.Down.getIndex()] = false;

            suppliersDirections[0][i][Direction.Up.getIndex()] = false;
            suppliersDirections[sizeY - 1][i][Direction.Down.getIndex()] = false;
        }

        for (int i = 0; i < sizeY; i++) {
            pedestrianDirection[i][0][Direction.Left.getIndex()] = false;
            pedestrianDirection[i][sizeX - 1][Direction.Righ.getIndex()] = false;

            suppliersDirections[i][0][Direction.Left.getIndex()] = false;
            suppliersDirections[i][sizeX - 1][Direction.Righ.getIndex()] = false;
        }
        //TODO check borders
        return new Map(sizeX, sizeY, locationMap, pedestrianDirection, suppliersDirections, spawnPointPedestrian, spawnPointRoad);
    }

    public Location[][] getLocationMap() {
        return this.locationMap;
    }

    public boolean[][][] getPedestrianDirections() {
        return this.pedestrianDirection;
    }

    public boolean[][][] getSuppliersDirections() {
        return suppliersDirections;
    }

    public List<SimulationObject> getSimulationObjects() {
        return simulationObjects;
    }

    public List<Shop> getShops() {
        return shops;
    }
}
