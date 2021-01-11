/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.Collections;
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
    
    private Location[][] locations;

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

    public Location getRandomShop(Shop current){
        Location location;
        do{
            location =  locations[new Random().nextInt(locations.length)][new Random().nextInt(locations[0].length)];
            
        }while( !(location instanceof Shop) || (current!=null&&location == current));
        return location;
    }
    
    private void createScene(){

        MapBuilder mapBuilder = new MapBuilder(19, 16)
                .addTwoWayRoadX(2, 13, 0).addTwoWayRoadX(2,13, 7).addTwoWayRoadX(2,13, 14)
                .addTwoWayRoadY(2, 13, 0).addTwoWayRoadY(2, 13, 7).addTwoWayRoadY(2,13,14)
                .add2x2Intersection(0, 0).add2x2Intersection(14, 0).add2x2Intersection(0,14).add2x2Intersection(14, 14).add2x2Intersection(7, 7)
                .add2x2Intersection(7, 0).add2x2Intersection(0, 7).add2x2Intersection(14, 7).add2x2Intersection(7, 14)
                .addDirectionPedestrian(5, 14, Direction.Up)
                .addOnePavement(3, 13, Collections.singletonList(Direction.Down))
                .addOnePavement(5, 13, Collections.singletonList(Direction.Up))
                .addPavementX(5, 4, 12)
                .addOnePavement(3, 12, Collections.singletonList(Direction.Down))
                .addRetailShop(4, 12, "Biedronka", "Nihilstyczna 1")
                //
                .addDirectionPedestrian(12, 14, Direction.Up)
                .addOnePavement(10, 13, Collections.singletonList(Direction.Down))
                .addOnePavement(12, 13, Collections.singletonList(Direction.Up))
                .addPavementX(12, 11, 12)
                .addOnePavement(10, 12, Collections.singletonList(Direction.Down))
                .addRetailShop(11, 12, "Lidl", "Nihilstyczna 2")
                //
                .addDirectionPedestrian(12, 7, Direction.Up)
                .addOnePavement(10, 6, Collections.singletonList(Direction.Down))
                .addOnePavement(12, 6, Collections.singletonList(Direction.Up))
                .addPavementX(12, 11, 5)
                .addOnePavement(10, 5, Collections.singletonList(Direction.Down))
                .addRetailShop(11, 5, "Stokrotka", "Nihilstyczna 2")
                //
                .addDirectionPedestrian(5, 7, Direction.Up)
                .addOnePavement(3, 6, Collections.singletonList(Direction.Down))
                .addOnePavement(5, 6, Collections.singletonList(Direction.Up))
                .addPavementX(5, 4, 5)
                .addOnePavement(3, 5, Collections.singletonList(Direction.Down))
                .addRetailShop(4, 5, "Żabka", "Nihilstyczna 4")
                .build();
        locations = mapBuilder.getLocationMap();
        for(SimulationObject so: mapBuilder.getSimulationObjects()){
            if(so instanceof MainLoopAgent){
                addMainLoopAgent((MainLoopAgent)so);
            }else{
                addSimulationObject(so);
            }
        }
        boolean [][][] directions = mapBuilder.getPedestrianDirections();
        
        
        PathFinder pathFinder = new PathFinder(directions, locations);
        for(int i=0;i<20;i++)
            addThreadAgent(ClientFactory.createRandomClient(getRandomShop(null), pathFinder));

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
