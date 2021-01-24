/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core;

import com.mycompany.pandemysimulation.core.data.AbstractDataManager;
import com.mycompany.pandemysimulation.core.map.AbstractMapManager;
import com.mycompany.pandemysimulation.core.ui.AbstractUIManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kacper
 */
public class Simulation {

    private LinkedList<ThreadAgent> threadsAgents;
    private LinkedList<MainLoopAgent> mainLoopAgents;
    private LinkedList<SimulationObject> simulationObjects;
    private final WorldData worldData;
    private final AbstractUIManager uiManager;
    private AbstractMapManager mapManager;
    private AbstractDataManager dataManager;
    private DateKeeper dateKeeper;

    public Simulation(AbstractUIManager uiManager, AbstractMapManager mapManager, AbstractDataManager dataManager) throws IOException {
        this.worldData = new WorldData(0.5, 0.5, 0.5, 0.5, 5, 0.5);
        this.uiManager = uiManager;
        uiManager.setSimulation(this);
        this.threadsAgents = new LinkedList<ThreadAgent>();
        this.mainLoopAgents = new LinkedList<MainLoopAgent>();
        this.simulationObjects = new LinkedList<SimulationObject>();
        this.dateKeeper = new DateKeeper();
        this.mapManager = mapManager;
        mapManager.setSimulation(this);
        this.dataManager = dataManager;
        this.dataManager.setSimulation(this);
    }

    public synchronized void start() throws Exception {
        uiManager.start();
        mapManager.start();
        dataManager.start();
    }

    public synchronized void update() {
        for (MainLoopAgent agent : this.mainLoopAgents) {
            agent.update();
        }
        mapManager.update();
        dataManager.update();
        uiManager.update();
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

    public synchronized List<ThreadAgent> getThreadAgents() {
        return new ArrayList(threadsAgents);
    }

    public synchronized List<MainLoopAgent> getMainLoopAgents() {
        return new ArrayList(mainLoopAgents);
    }

    public AbstractUIManager getUIManager() {
        return uiManager;
    }

    public AbstractMapManager getMapManager() {
        return mapManager;
    }

    public WorldData getWorldData() {
        return worldData;
    }

    public Date getCurrentDate() {
        return dateKeeper.getCurDate();
    }
}
