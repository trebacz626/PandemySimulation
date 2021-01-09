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
    
    private TileType getTileType(char c){
        switch(c){
            case 'P':
                return TileType.P;
            case 'Q':
                return TileType.PI;
            default:
                return TileType.G;
        }
    }
    
    private void createScene(){
        char[][] map = {
            {'Q','Q','P','P','P','P','P','Q','Q','P','P','P','P','P','Q','Q'},
            {'Q','Q','P','P','P','P','P','Q','Q','P','P','P','P','P','Q','Q'},
            {'P','P','G','P','G','P','G','P','P','G','P','G','P','G','P','P'},
            {'P','P','G','G','G','G','G','P','P','G','G','G','G','G','P','P'},
            {'P','P','G','G','G','G','G','P','P','G','G','G','G','G','P','P'},
            {'P','P','G','G','G','G','G','P','P','G','G','G','G','G','P','P'},
            {'P','P','G','G','G','G','G','P','P','G','P','G','P','G','P','P'},
            {'Q','Q','P','P','P','P','P','Q','Q','P','P','P','P','P','Q','Q'},
            {'Q','Q','P','P','P','P','P','Q','Q','P','P','P','P','P','Q','Q'},
            {'P','P','G','P','G','P','G','P','P','G','G','G','G','G','P','P'},
            {'P','P','G','G','G','G','G','P','P','G','G','G','G','G','P','P'},
            {'P','P','G','G','G','G','G','P','P','G','G','G','G','G','P','P'},
            {'P','P','G','G','G','G','G','P','P','G','G','G','G','G','P','P'},
            {'P','P','G','P','G','P','G','P','P','G','P','G','P','G','P','P'},
            {'Q','Q','P','P','P','P','P','Q','Q','P','P','P','P','P','Q','Q'},
            {'Q','Q','P','P','P','P','P','Q','Q','P','P','P','P','P','Q','Q'},
               
        };
        Tile[][] tiles = new Tile[16][16];
        Intersection intersection = new Intersection(0, 0, new VisibleComponent("stoplight.jpg", 50, 50));
        for(int x=0;x<16; x++){
             for(int y=0;y<16;y++){
                 TileType type = getTileType(map[x][y]);
                 if(type == TileType.PI){
                     tiles[x][y] = new IntersectionTile(x, y, type, intersection);
                 }else{
                    tiles[x][y] = new Tile(x,y, type);
                 }
                 
                 addSimulationObject(tiles[x][y]);
             }
         }
        for(int i =3; i >= 0; i--){
            Client client = ClientFactory.createRandomClient(tiles[0][5]);
            List<Location> path = new LinkedList<>();
            for(int x=0;x<10;x++){
                path.add(tiles[x][5]);
            }
            client.setPath(path);
            addThreadAgent(client);
        }
        
        for(int i =3; i >= 0; i--){
            Client client = ClientFactory.createRandomClient(tiles[9][4]);
            List<Location> path = new LinkedList<>();
            for(int x=9;x>=0;x--){
                path.add(tiles[x][4]);
            }
            client.setPath(path);
            addThreadAgent(client);
        }
        
        
        for(int i =3; i >= 0; i--){
            Client client = ClientFactory.createRandomClient(tiles[5][0]);
            List<Location> path = new LinkedList<>();
            for(int x=0;x<10;x++){
                path.add(tiles[5][x]);
            }
            client.setPath(path);
            addThreadAgent(client);
        }
        
        for(int i =3; i >= 0; i--){
            Client client = ClientFactory.createRandomClient(tiles[4][9]);
            List<Location> path = new LinkedList<>();
            for(int x=9;x>=0;x--){
                path.add(tiles[4][x]);
            }
            client.setPath(path);
            addThreadAgent(client);
        }
        
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
