/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core;

import com.mycompany.pandemysimulation.map.MapBuilder;
import com.mycompany.pandemysimulation.person.Person;
import com.mycompany.pandemysimulation.map.Map;
import com.mycompany.pandemysimulation.map.MapCreator;
import com.mycompany.pandemysimulation.map.MapManager;
import com.mycompany.pandemysimulation.ui.UIManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.stage.Stage;

/**
 *
 * @author kacper
 */
public class Simulation {

    private LinkedList<ThreadAgent> threadsAgents;
    private LinkedList<MainLoopAgent> mainLoopAgents;
    private LinkedList<SimulationObject> simulationObjects;
    private final WorldData worldData;
    private final UIManager uiManager;
    private MapManager mapManager;
    private DateKeeper dateKeeper;

    public Simulation(Stage stage, MapCreator mapCreator) throws IOException {
        this.worldData = new WorldData(0.5,0.5,0.5,0.5,5);
        this.uiManager = new UIManager(stage, this);
        this.threadsAgents = new LinkedList<ThreadAgent>();
        this.mainLoopAgents = new LinkedList<MainLoopAgent>();
        this.simulationObjects = new LinkedList<SimulationObject>();
        this.dateKeeper = new DateKeeper();
        MapBuilder mapBuilder = mapCreator.getMapBuilder();
        Map map = mapBuilder.build();
        mapManager = new MapManager(map);
        for (SimulationObject so : mapBuilder.getSimulationObjects()) {
            if (so instanceof MainLoopAgent) {
                addMainLoopAgent((MainLoopAgent) so);
            } else {
                addSimulationObject(so);
            }
        }
    }

    public synchronized void start() {
        for (MainLoopAgent agent : this.mainLoopAgents) {
            agent.start();
        }
    }

    public synchronized void update() {
        for (MainLoopAgent agent : this.mainLoopAgents) {
            agent.update();
        }
        findDeadlock();
        updateWorldData();
        uiManager.update();
    }

    private void findDeadlock() {
        List<Person> people = threadsAgents.stream().filter(a -> a instanceof Person).map(a -> (Person) a).collect(Collectors.toList());
        List<Person> toDelete = mapManager.findDeadLockPedestrian(people);
        List<Person> carsToDelete = mapManager.findDeadLockRoad(people);
        toDelete.addAll(carsToDelete);
        for (Person person : toDelete) {
            removeThreadAgent(person);
        }
    }
    
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
    }

    public synchronized void addSimulationObject(SimulationObject simulationObject) {
        simulationObjects.add(simulationObject);
        uiManager.getMapPanelController().addVisibleComponent(simulationObject.getVisibleComponent());
    }

    public synchronized void removeThreadAgent(ThreadAgent threadAgent) {
        threadsAgents.remove(threadAgent);
        simulationObjects.remove(threadAgent);
        uiManager.getMapPanelController().removeVisibleComponent(threadAgent.getVisibleComponent());
        threadAgent.kill();
    }
    
    public synchronized List<ThreadAgent> getThreadAgents(){
        return new ArrayList(threadsAgents);
    }
    
    public synchronized List<MainLoopAgent> getMainLooAgents(){
        return new ArrayList(mainLoopAgents);
    }

    
    public UIManager getUIManager(){
        return uiManager;
    }
    
    public MapManager getMapManager(){
        return mapManager;
    }
    
    public WorldData getWorldData(){
        return worldData;
    }
    
    public Date getCurrentDate(){
        return dateKeeper.getCurDate();
    }
}
