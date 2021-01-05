/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author kacper
 */
public class Simulation {
    private LinkedList<ThreadAgent> threadsAgents;
    private LinkedList<MainLoopAgent> mainLoopAgents;
    private LinkedList<SimulationObject> simulationObjects;
    public final WorldData worldData;
    public final WorldGraph worldGraph;
    public final UIManager uiManager;

    public Simulation(UIManager uiManager) {
        this.worldData = new WorldData();
        this.worldGraph = new WorldGraph();
        this.uiManager = uiManager;
        this.threadsAgents = new LinkedList<ThreadAgent>();
        this.mainLoopAgents = new LinkedList<MainLoopAgent>();
        this.simulationObjects = new LinkedList<SimulationObject>();
    }
    
      
    
    public void start(){
        System.out.println("Simulation Started");
        for(int i =0; i<10; i++){
            addThreadAgent(ClientFactory.createRandomClient(uiManager));
        }
        
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
        
        uiManager.getMapPanelController().draw();
    }
    
    private void drawSimulationObject(SimulationObject so){
        
    }
    
    public void finish(){
    
    }
    
    public void startLockdown(){
    
    }
    
    public void endLockdown(){
    
    }
    
    private void addThreadAgent(ThreadAgent threadAgent){
        threadsAgents.add(threadAgent);
        simulationObjects.add(threadAgent);
        uiManager.getMapPanelController().addVisibleComponent(threadAgent.visibleComponent);
    }
    
    private void addMainLoopAgent(MainLoopAgent mainLoopAgent){
        mainLoopAgents.add(mainLoopAgent);
        simulationObjects.add(mainLoopAgent);
    }
    
    
}
