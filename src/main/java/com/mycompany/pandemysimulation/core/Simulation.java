/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core;

import com.mycompany.pandemysimulation.core.map.AbstractMapManager;
import com.mycompany.pandemysimulation.core.ui.AbstractUIManager;
import com.mycompany.pandemysimulation.person.Person;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author kacper
 */
public class Simulation{

    private LinkedList<ThreadAgent> threadsAgents;
    private LinkedList<MainLoopAgent> mainLoopAgents;
    private LinkedList<SimulationObject> simulationObjects;
    private final WorldData worldData;
    private final AbstractUIManager uiManager;
    private AbstractMapManager mapManager;
    private DateKeeper dateKeeper;

    public Simulation(AbstractUIManager uiManager, AbstractMapManager mapManager) throws IOException {
        this.worldData = new WorldData(0.5,0.5,0.5,0.5,5);
        this.uiManager = uiManager;
        uiManager.setSimulation(this);
        this.threadsAgents = new LinkedList<ThreadAgent>();
        this.mainLoopAgents = new LinkedList<MainLoopAgent>();
        this.simulationObjects = new LinkedList<SimulationObject>();
        this.dateKeeper = new DateKeeper();
        this.mapManager = mapManager;
        mapManager.setSimulation(this);
    }

    public synchronized void start() throws Exception {
        uiManager.start();
        mapManager.start();
    }

    public synchronized void update() {
        for (MainLoopAgent agent : this.mainLoopAgents) {
            agent.update();
        }
        mapManager.update();
        updateWorldData();
        uiManager.update();
    }
    
    //TODO dele
    private void updateWorldData(){
        List<Person> people = threadsAgents.stream().filter(ta->ta instanceof Person).map(ps->(Person)ps).collect(Collectors.toList());
        List<Person> sickPeople = people.stream().filter(ps->ps.isSick()).collect(Collectors.toList());
        this.worldData.setNumberOfSickPeople(sickPeople.size());
        this.worldData.setNumberOfPeople(people.size());
    }

    public synchronized void addThreadAgent(ThreadAgent threadAgent) {
        threadsAgents.add(threadAgent);
        addSimulationObject(threadAgent);
        Thread agentThread = new Thread(threadAgent);
            agentThread.setDaemon(true);
            agentThread.start();
    }

    public synchronized void addMainLoopAgent(MainLoopAgent mainLoopAgent) {
        mainLoopAgents.add(mainLoopAgent);
        addSimulationObject(mainLoopAgent);
        mainLoopAgent.start();
    }

    public synchronized void addSimulationObject(SimulationObject simulationObject) {
        simulationObjects.add(simulationObject);
        simulationObject.setSimulation(this);
        uiManager.addVisibleComponent(simulationObject.getVisibleComponent());
    }

    public synchronized void removeThreadAgent(ThreadAgent threadAgent) {
        threadsAgents.remove(threadAgent);
        simulationObjects.remove(threadAgent);
        uiManager.removeVisibleComponent(threadAgent.getVisibleComponent());
        threadAgent.kill();
    }
    
    public synchronized List<ThreadAgent> getThreadAgents(){
        return new ArrayList(threadsAgents);
    }
    
    public synchronized List<MainLoopAgent> getMainLooAgents(){
        return new ArrayList(mainLoopAgents);
    }

    
    public AbstractUIManager getUIManager(){
        return uiManager;
    }
    
    public AbstractMapManager getMapManager(){
        return mapManager;
    }
    
    public WorldData getWorldData(){
        return worldData;
    }
    
    public Date getCurrentDate(){
        return dateKeeper.getCurDate();
    }
}
