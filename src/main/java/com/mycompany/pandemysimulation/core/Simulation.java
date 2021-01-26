/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core;

import com.mycompany.pandemysimulation.core.data.AbstractDataManager;
import com.mycompany.pandemysimulation.core.data.WorldData;
import com.mycompany.pandemysimulation.core.map.AbstractMapManager;
import com.mycompany.pandemysimulation.core.ui.AbstractUIManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Core class of a project responsible for managing simulationObjects and
 * agents.
 *
 * Defines managers replaceable managers necessary for simulation to run.
 * Updates MainLoopAgents every frame and provides methods for addition and
 * deletion of simulationObjects
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

    /**
     *
     * Constructor
     * 
     * @param uiManager instance of class inheriting from UIManager 
     * @param mapManager instance of class inheriting from MapManager
     * @param dataManager instance of class inheriting from DataManager
     * @throws IOException
     */
    public Simulation(AbstractUIManager uiManager, AbstractMapManager mapManager, AbstractDataManager dataManager) throws IOException {
        this.worldData = new WorldData(0.5, 0.5, 0.5, 0.5, 5, 0.5);
        this.uiManager = uiManager;
        uiManager.setSimulation(this);
        this.threadsAgents = new LinkedList<>();
        this.mainLoopAgents = new LinkedList<>();
        this.simulationObjects = new LinkedList<>();
        this.dateKeeper = new DateKeeper();
        this.mapManager = mapManager;
        mapManager.setSimulation(this);
        this.dataManager = dataManager;
        this.dataManager.setSimulation(this);
    }

    /**
     *
     * Method called at the beginning of a simulation.
     * 
     * @throws Exception
     */
    public synchronized void start() throws Exception {
        dataManager.start();
        uiManager.start();
        mapManager.start();
    }

    /**
     * Method updating state of a game.
     * Do not call before calling start.
     */
    public synchronized void update() {
        for (MainLoopAgent agent : this.mainLoopAgents) {
            agent.update();
        }
        mapManager.update();
        dataManager.update();
        uiManager.update();
    }

    /**
     *
     * Adds and starts a ThreadAgent
     * Do not call before calling start.
     * 
     * @param threadAgent
     */
    public synchronized void addThreadAgent(ThreadAgent threadAgent) {
        threadsAgents.add(threadAgent);
        addSimulationObject(threadAgent);
        Thread agentThread = new Thread(threadAgent);
        agentThread.setDaemon(true);
        agentThread.start();
    }

    /**
     * Adds and starts a MainLoopAgent
     * Do not call before calling start.
     * 
     * @param mainLoopAgent
     */
    public synchronized void addMainLoopAgent(MainLoopAgent mainLoopAgent) {
        mainLoopAgents.add(mainLoopAgent);
        addSimulationObject(mainLoopAgent);
        mainLoopAgent.start();
    }

    /**
     *
     * Adds Simulation Object
     * 
     * @param simulationObject
     */
    public synchronized void addSimulationObject(SimulationObject simulationObject) {
        simulationObjects.add(simulationObject);
        simulationObject.setSimulation(this);
        uiManager.addVisibleComponent(simulationObject.getVisibleComponent());
    }

    /**
     *
     * Removes Thread Agent
     * 
     * @param threadAgent
     */
    public synchronized void removeThreadAgent(ThreadAgent threadAgent) {
        threadsAgents.remove(threadAgent);
        simulationObjects.remove(threadAgent);
        uiManager.removeVisibleComponent(threadAgent.getVisibleComponent());
        threadAgent.kill();
    }

    /**
     *
     * @return a copy of list of threadAgents
     */
    public synchronized List<ThreadAgent> getThreadAgents() {
        return new ArrayList(threadsAgents);
    }

    /**
     *
     * @return a copy of MainLoopAgents
     */
    public synchronized List<MainLoopAgent> getMainLoopAgents() {
        return new ArrayList(mainLoopAgents);
    }

    /**
     *
     * @return
     */
    public AbstractUIManager getUIManager() {
        return uiManager;
    }

    /**
     *
     * @return 
     */
    public AbstractMapManager getMapManager() {
        return mapManager;
    }

    /**
     *
     * @return
     */
    public WorldData getWorldData() {
        return worldData;
    }

    /**
     *
     * Returns current simulation date
     * 
     * @return current simulation date
     */
    public Date getCurrentDate() {
        return dateKeeper.getCurDate();
    }
}
