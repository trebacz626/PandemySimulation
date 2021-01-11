/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kacper
 */
public class MapBuilder {
    private int sizeX;
    private int sizeY;
    Tile[][] tileMap;
    boolean[][][] pedestrianDirection;
    boolean[][][] suppliersDirections;
    List<Intersection> intersections;
    List<SimulationObject> simulationObjects;
    
    public MapBuilder(int sizeX, int sizeY){
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        tileMap = new Tile[sizeY][sizeX];
        pedestrianDirection = new boolean[sizeY][sizeX][4];
        suppliersDirections = new boolean[sizeY][sizeX][4];
        intersections = new LinkedList<>();
        simulationObjects = new LinkedList<>();
    }
    
    public MapBuilder addTwoWayRoadX(int startX, int endX, int y){
        addPavementX(startX, endX, y+1);
        addPavementX(endX, startX, y);
        return this;
    }
    
    
    public MapBuilder addTwoWayRoadY(int startY, int endY, int x){
        addPavementY(startY, endY, x);
        addPavementY(endY, startY, x+1);
        return this;
    }
    
    public MapBuilder addPavementX(int startX, int endX, int y){
        List<Direction> directions;
        if(endX > startX)
            directions = Arrays.asList(Direction.Righ);
        else{
            directions = Arrays.asList(Direction.Left);
            int temp = startX;
            startX = endX;
            endX=temp;
        }
            
        for(int x=startX;x<=endX;x++){
            addOnePavement(x, y, directions);
        }
        return this;
    }
    
    public MapBuilder addPavementY(int startY, int endY, int x){
        List<Direction> directions;
        if(endY > startY)
            directions = Arrays.asList(Direction.Down);
        else{
            directions = Arrays.asList(Direction.Up);
            int temp = startY;
            startY = endY;
            endY=temp;
        }
        for(int y=startY;y<=endY;y++){
            addOnePavement(x, y, directions);
        }
        return this;
    }
    
    public MapBuilder addOnePavement(int x, int y, List<Direction> directions){
        tileMap[y][x] = new Tile(x,y,TileType.P);
        for(Direction direction : directions){
            pedestrianDirection[y][x][direction.getVal()] = true;
        }
        return this;
    }
    
    public MapBuilder add2x2Intersection(int x1, int y1){
        int x2=x1+1;
        int y2=y1+1;
        Intersection intersection = new Intersection(Coordinates.mapToWorld((x1+x2)/2), Coordinates.mapToWorld((y1+y2)/2), new VisibleComponent("stoplight.jpg", 10, 10));
        intersections.add(intersection);
        for(int y=y1;y<=y2;y++){
            for(int x=x1;x<=x2;x++){
                for(int i=0;i<4;i++){
                    pedestrianDirection[y][x][i]=true;
                    tileMap[y][x] = new IntersectionTile(x,y,TileType.PI, intersection);
                }
            }
        }
        //upper left
        pedestrianDirection[y1][x1][Direction.Up.getVal()]= false;
        pedestrianDirection[y1][x1][Direction.Left.getVal()]= x1>0;
        //down left
        pedestrianDirection[y2][x1][Direction.Left.getVal()]= false;
        pedestrianDirection[y2][x1][Direction.Down.getVal()]= y2<(sizeY-1);
        //upper right
        pedestrianDirection[y1][x2][Direction.Righ.getVal()]= false;
        pedestrianDirection[y1][x2][Direction.Up.getVal()]= y1>0;
        //lower right
        pedestrianDirection[y2][x2][Direction.Down.getVal()]= false;
        pedestrianDirection[y2][x2][Direction.Righ.getVal()]= x2<(sizeX-1);
        return this;
    }
    
    public MapBuilder build(){
        for(int y=0;y<sizeY;y++){
            for(int x =0;x<sizeX;x++){
                if(tileMap[y][x] == null){
                    tileMap[y][x] = new Tile(x,y,TileType.G);
                }
                simulationObjects.add(tileMap[y][x]);
            }
        }
        simulationObjects.addAll(intersections);
        //TODO check borders
        return this;
    }
    
    public Tile[][] getTileMap(){
        return this.tileMap;
    }
    
    public boolean[][][] getPedestrianDirections(){
        return this.pedestrianDirection;
    }

    public boolean[][][] getSuppliersDirections() {
        return suppliersDirections;
    }

    public List<SimulationObject> getSimulationObjects() {
        return simulationObjects;
    }
    
    
}
