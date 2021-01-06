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
    
    private void createScene(){
        //Wholesales
        addMainLoopAgent(WholesaleFactory.createWholesaleShop(uiManager, "Biedronka", "Krasnalowa 5", 0, 0, "wholesale1.png"));
        addMainLoopAgent(WholesaleFactory.createWholesaleShop(uiManager, "Lidl", "Ostatnia 5", 600, 600, "wholesale1.png"));
        addMainLoopAgent(WholesaleFactory.createWholesaleShop(uiManager, "Biedronka", "Krasnalowa 5", 700, 300, "wholesale1.png"));
        addMainLoopAgent(WholesaleFactory.createWholesaleShop(uiManager, "Biedronka", "Krasnalowa 5", 1000, 100, "wholesale1.png"));
        
        //Retail Shops
        addMainLoopAgent(RetailShopFactory.createRetailShop(uiManager, "Biedronka", "Nie ma ogonka 2", 200, 200, "retail.png"));
        addMainLoopAgent(RetailShopFactory.createRetailShop(uiManager, "Lidl", "Gdy sobota to 2", 400, 200, "retail.png"));
        addMainLoopAgent(RetailShopFactory.createRetailShop(uiManager, "Dino", "Wiejska 17", 400, 400, "retail.png"));
        addMainLoopAgent(RetailShopFactory.createRetailShop(uiManager, "Biedronka", "Nie ma ogonka 2", 200, 400, "retail.png"));
        
//        createWorldGraph();
    }
    
    public List<Shop> getShops(){
        return simulationObjects.stream().filter(so -> so instanceof Shop).map(so -> (Shop) so).collect(Collectors.toList());
    }
    
    private void createWorldGraph(LinkedList<Location> locations){
    }
    
    public void addAgents(){
        for(int i =0; i<10; i++){
            addThreadAgent(ClientFactory.createRandomClient(uiManager));
        }
        
        for(int i =0; i<10; i++){
            addThreadAgent(SupplierFactory.createRandomSupplier(uiManager));
        }
    }
    
    private synchronized void addThreadAgent(ThreadAgent threadAgent){
        threadsAgents.add(threadAgent);
        simulationObjects.add(threadAgent);
        uiManager.getMapPanelController().addVisibleComponent(threadAgent.visibleComponent);
    }
    
    private synchronized void addMainLoopAgent(MainLoopAgent mainLoopAgent){
        mainLoopAgents.add(mainLoopAgent);
        simulationObjects.add(mainLoopAgent);
        uiManager.getMapPanelController().addVisibleComponent(mainLoopAgent.visibleComponent);
    }
    
    
    public synchronized void removeThreadAgent(ThreadAgent threadAgent){
        threadsAgents.remove(threadAgent);
        simulationObjects.remove(threadAgent);
        uiManager.getMapPanelController().removeVisibleComponent(threadAgent.getVisibleComponent());
        threadAgent.kill();
    }
    
    
}
