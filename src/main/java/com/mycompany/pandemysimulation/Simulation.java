/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.LinkedList;

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
    public final PrimaryController uiController;

    public Simulation() {
        this.worldData = new WorldData();
        this.worldGraph = new WorldGraph();
        this.uiController = new PrimaryController();
        this.threadsAgents = new LinkedList<ThreadAgent>();
        this.mainLoopAgents = new LinkedList<MainLoopAgent>();
        this.simulationObjects = new LinkedList<SimulationObject>();
    }
    
    
    
    
    public void start(){
        //...
        while(true){
            update();
        }
        
    }
    
    private void update(){
        for(MainLoopAgent agent : this.mainLoopAgents){
            agent.update();
        }
        
        for(SimulationObject so : this.simulationObjects){
            this.drawSimulationObject(so);
        }
    }
    
    private void drawSimulationObject(SimulationObject so){
        
    }
    
    public void finish(){
    
    }
    
    public void startLockdown(){
    
    }
    
    public void endLockdown(){
    
    }
    
    
}
