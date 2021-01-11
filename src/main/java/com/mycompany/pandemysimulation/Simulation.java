/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author kacper
 */
public class Simulation {
    private LinkedList<ThreadAgent> threadsAgents;
    private LinkedList<MainLoopAgent> mainLoopAgents;
    private LinkedList<SimulationObject> simulationObjects;
    public final WorldData worldData;
    public WorldGraph worldGraph;//final
    public final UIManager uiManager;
    
    private Location[][] tiles;

    public Simulation(UIManager uiManager) {
        this.worldData = new WorldData();
        this.uiManager = uiManager;
        this.threadsAgents = new LinkedList<ThreadAgent>();
        this.mainLoopAgents = new LinkedList<MainLoopAgent>();
        this.simulationObjects = new LinkedList<SimulationObject>();
    }
    
      
    
    public void start(){
        System.out.println("Simulation Started");
        
        createScene();
        
        addAgents();
        
        for(ThreadAgent agent : this.threadsAgents){
            Thread agentThread = new Thread(agent);
            agentThread.setDaemon(true);
            agentThread.start();
        }
        
    }
    
    public void update(){
//        System.out.println("Update-main: " + new Random().nextInt(100));
        for(MainLoopAgent agent : this.mainLoopAgents){
            agent.update();
        }
        
        uiManager.update();
    }
    
    private void drawSimulationObject(SimulationObject so){
        
    }
    
    public void finish(){
    
    }
    
    public void startLockdown(){
    
    }
    
    public void endLockdown(){
    
    }

    public Location getRandomPavement(){
        Tile tile;
        do{
            tile = (Tile) tiles[new Random().nextInt(tiles.length)][new Random().nextInt(tiles[0].length)];
            
        }while(tile.tileType != TileType.P);
        return tile;
    }
    
    private void createScene(){
//        char[][] map = {
//        Tile[][] tiles = new Tile[16][19];
//        Intersection intersection = new Intersection(0, 0, new VisibleComponent("stoplight.jpg", 50, 50));
//        for(int y=0;y<16; y++){
//             for(int x=0;x<19;x++){
//                 TileType type = getTileType(map[y][x]);
//                 if(type == TileType.PI){
//                     tiles[y][x] = new IntersectionTile(x, y, type, intersection);
//                 }else{
//                    tiles[y][x] = new Tile(x,y, type);
//                 }
//                 
//                 addSimulationObject(tiles[y][x]);
//             }
//         }

        MapBuilder mapBuilder = new MapBuilder(19, 16)
                .addTwoWayRoadX(2, 13, 0).addTwoWayRoadX(2,13, 7).addTwoWayRoadX(2,13, 14)
                .addTwoWayRoadY(2, 13, 0).addTwoWayRoadY(2, 13, 7).addTwoWayRoadY(2,13,14)
                .add2x2Intersection(0, 0).add2x2Intersection(14, 0).add2x2Intersection(0,14).add2x2Intersection(14, 14).add2x2Intersection(7, 7)
                .add2x2Intersection(7, 0).add2x2Intersection(0, 7).add2x2Intersection(14, 7).add2x2Intersection(7, 14)
                .build();
        tiles = mapBuilder.getTileMap();
        for(SimulationObject so: mapBuilder.getSimulationObjects()){
            addSimulationObject(so);
        }
        boolean [][][] directions = mapBuilder.getPedestrianDirections();
        
        
        PathFinder pathFinder = new PathFinder(directions, tiles);
        for(int i=0;i<20;i++)
            addThreadAgent(ClientFactory.createRandomClient(getRandomPavement(), pathFinder));
//        List<Location> path = pathFinder.findPath(0, 0, 8, 8);
//        for(Location location: path){
//            System.out.println(location);
//        }
        
//        for(int y=0;y<directions.length;y++){
//            for(int x=0;x< directions[y].length;x++){
//                System.out.print(x+" "+y+": ");
//                if(directions[y][x][0])
//                    System.out.print("UP ");
//                if(directions[y][x][1])
//                    System.out.print("DOWN ");
//                if(directions[y][x][2])
//                    System.out.print("LEFT ");
//                if(directions[y][x][3])
//                    System.out.print("RIGHT ");
//                System.out.println();
//            }
//        }
        
//        for(int i =3; i >= 0; i--){
//            Client client = ClientFactory.createRandomClient(tiles[0][7]);
//            List<Location> path = new LinkedList<>();
//            for(int x=0;x<16;x++){
//                path.add(tiles[x][7]);
//            }
//            client.setPath(path);
//            addThreadAgent(client);
//        }
        
//        for(int i =3; i >= 0; i--){
//            Client client = ClientFactory.createRandomClient(tiles[15][8]);
//            List<Location> path = new LinkedList<>();
//            for(int x=15;x>=0;x--){
//                path.add(tiles[x][8]);
//            }
//            client.setPath(path);
//            addThreadAgent(client);
//        }
//        
//        
//        for(int i =3; i >= 0; i--){
//            Client client = ClientFactory.createRandomClient(tiles[7][0]);
//            List<Location> path = new LinkedList<>();
//            for(int x=0;x<16;x++){
//                path.add(tiles[7][x]);
//            }
//            client.setPath(path);
//            addThreadAgent(client);
//        }
//        
//        for(int i =3; i >= 0; i--){
//            Client client = ClientFactory.createRandomClient(tiles[8][15]);
//            List<Location> path = new LinkedList<>();
//            for(int x=15;x>=0;x--){
//                path.add(tiles[8][x]);
//            }
//            client.setPath(path);
//            addThreadAgent(client);
//        }
        
        //Wholesales
//        addMainLoopAgent(WholesaleFactory.createWholesaleShop("Biedronka", "Krasnalowa 5", 10, 10, "wholesale1.png"));
//        addMainLoopAgent(WholesaleFactory.createWholesaleShop(uiManager, "Lidl", "Ostatnia 5", 600, 600, "wholesale1.png"));
//        addMainLoopAgent(WholesaleFactory.createWholesaleShop(uiManager, "Biedronka", "Krasnalowa 5", 700, 300, "wholesale1.png"));
//        addMainLoopAgent(WholesaleFactory.createWholesaleShop(uiManager, "Biedronka", "Krasnalowa 5", 1000, 100, "wholesale1.png"));
        
        //Retail Shops
//        addMainLoopAgent(RetailShopFactory.createRetailShop("Biedronka", "Nie ma ogonka 2", 200, 200, "retail.png"));
//        addMainLoopAgent(RetailShopFactory.createRetailShop(uiManager, "Lidl", "Gdy sobota to 2", 400, 200, "retail.png"));
//        addMainLoopAgent(RetailShopFactory.createRetailShop(uiManager, "Dino", "Wiejska 17", 400, 400, "retail.png"));
//        addMainLoopAgent(RetailShopFactory.createRetailShop(uiManager, "Biedronka", "Nie ma ogonka 2", 200, 400, "retail.png"));
        
//        createWorldGraph();

    }
    
    public List<Shop> getShops(){
        return simulationObjects.stream().filter(so -> so instanceof Shop).map(so -> (Shop) so).collect(Collectors.toList());
    }
    
    private void createWorldGraph(LinkedList<Location> locations){
    }
    
    public void addAgents(){
//        for(int i =0; i<10; i++){
//            addThreadAgent(ClientFactory.createRandomClient());
//        }
//        
//        for(int i =0; i<10; i++){
//            addThreadAgent(SupplierFactory.createRandomSupplier());
//        }
    }
    
    private synchronized void addThreadAgent(ThreadAgent threadAgent){
        threadsAgents.add(threadAgent);
        addSimulationObject(threadAgent);
    }
    
    private synchronized void addMainLoopAgent(MainLoopAgent mainLoopAgent){
        mainLoopAgents.add(mainLoopAgent);
        addSimulationObject(mainLoopAgent);
    }
    
    private synchronized void addSimulationObject(SimulationObject simulationObject){
        simulationObjects.add(simulationObject);
        uiManager.getMapPanelController().addVisibleComponent(simulationObject.getVisibleComponent());
    }
    
    
    public synchronized void removeThreadAgent(ThreadAgent threadAgent){
        threadsAgents.remove(threadAgent);
        simulationObjects.remove(threadAgent);
        uiManager.getMapPanelController().removeVisibleComponent(threadAgent.getVisibleComponent());
        threadAgent.kill();
    }
    
    
}
