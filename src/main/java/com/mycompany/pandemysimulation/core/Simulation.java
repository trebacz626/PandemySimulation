/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core;

import com.mycompany.pandemysimulation.map.Location;
import com.mycompany.pandemysimulation.person.Client;
import com.mycompany.pandemysimulation.person.ClientFactory;
import com.mycompany.pandemysimulation.Direction;
import com.mycompany.pandemysimulation.map.MapBuilder;
import com.mycompany.pandemysimulation.map.PathFinder;
import com.mycompany.pandemysimulation.person.Person;
import com.mycompany.pandemysimulation.shop.RetailShop;
import com.mycompany.pandemysimulation.shop.Shop;
import com.mycompany.pandemysimulation.person.SupplierFactory;
import com.mycompany.pandemysimulation.Utils;
import com.mycompany.pandemysimulation.shop.WholesaleShop;
import com.mycompany.pandemysimulation.WorldData;
import com.mycompany.pandemysimulation.map.DeadlockFinder;
import com.mycompany.pandemysimulation.map.Map;
import com.mycompany.pandemysimulation.map.MapCreator;
import com.mycompany.pandemysimulation.map.MapManager;
import com.mycompany.pandemysimulation.ui.UIManager;
import java.util.Collection;
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
//    public final WorldData worldData;
    public final UIManager uiManager;
    private MapManager mapManager;

    public Simulation(UIManager uiManager) {
//        this.worldData = new WorldData();
        this.uiManager = uiManager;
        this.threadsAgents = new LinkedList<ThreadAgent>();
        this.mainLoopAgents = new LinkedList<MainLoopAgent>();
        this.simulationObjects = new LinkedList<SimulationObject>();
    }

    public void start() {
        System.out.println("Simulation Started");
        createScene();
        addAgents();
        for (MainLoopAgent agent : this.mainLoopAgents) {
            agent.start();
        }

        for (ThreadAgent agent : this.threadsAgents) {
            Thread agentThread = new Thread(agent);
            agentThread.setDaemon(true);
            agentThread.start();
        }

    }

    public void update() {
        for (MainLoopAgent agent : this.mainLoopAgents) {
            agent.update();
        }
        findDeadlock();
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

    private void drawSimulationObject(SimulationObject so) {

    }

    public void finish() {

    }

    public void startLockdown() {

    }

    public void endLockdown() {

    }

    public RetailShop getRandomRetailShop(Shop current) {
        Shop shop;
        while (!((shop = getRandomShop(current)) instanceof RetailShop));
        return (RetailShop) shop;
    }

    public WholesaleShop getRandomWholesaleShop(Shop current) {
        Shop shop;
        while (!((shop = getRandomShop(current)) instanceof WholesaleShop));
        return (WholesaleShop) shop;
    }

    public Shop getRandomShop(Shop current) {
        MainLoopAgent agent;
        do {
            agent = Utils.getRandomFromList(mainLoopAgents);

        } while (!(agent instanceof Shop) || (current != null && agent == current));
        return (Shop) agent;
    }

    public Shop getShopById(int id) {

        for (MainLoopAgent agent : this.mainLoopAgents) {
            if (agent instanceof Shop) {
                if (((Shop) agent).getUniqueId() == id) {
                    return (Shop) agent;
                }
            }
        }
        return null;
    }

    private void createScene() {
        MapBuilder builder = MapCreator.getMapBuilder();
        Map map = builder.build();
        mapManager = new MapManager(map);
        for (SimulationObject so : builder.getSimulationObjects()) {
            if (so instanceof MainLoopAgent) {
                addMainLoopAgent((MainLoopAgent) so);
            } else {
                addSimulationObject(so);
            }
        }
    }

    public List<Shop> getShops() {
        return simulationObjects.stream().filter(so -> so instanceof Shop).map(so -> (Shop) so).collect(Collectors.toList());
    }

    private void createWorldGraph(LinkedList<Location> locations) {
    }

    public void addAgents() {
        for (int i = 0; i < 100; i++) {
            addThreadAgent(ClientFactory.createRandomClient(getRandomRetailShop(null), mapManager.getPedestrianPathFinder()));
        }

        for (int i = 0; i < 100; i++) {
            addThreadAgent(SupplierFactory.createRandomSupplier(getRandomShop(null), mapManager.getRoadPathFinder()));
        }
    }

    private synchronized void addThreadAgent(ThreadAgent threadAgent) {
        threadsAgents.add(threadAgent);
        addSimulationObject(threadAgent);
    }

    private synchronized void addMainLoopAgent(MainLoopAgent mainLoopAgent) {
        mainLoopAgents.add(mainLoopAgent);
        addSimulationObject(mainLoopAgent);
    }

    private synchronized void addSimulationObject(SimulationObject simulationObject) {
        simulationObjects.add(simulationObject);
        uiManager.getMapPanelController().addVisibleComponent(simulationObject.getVisibleComponent());
    }

    public synchronized void removeThreadAgent(ThreadAgent threadAgent) {
        threadsAgents.remove(threadAgent);
        simulationObjects.remove(threadAgent);
        uiManager.getMapPanelController().removeVisibleComponent(threadAgent.getVisibleComponent());
        threadAgent.kill();
    }

}
