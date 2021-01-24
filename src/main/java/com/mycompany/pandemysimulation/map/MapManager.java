/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.map;

import com.mycompany.pandemysimulation.person.client.Client;
import com.mycompany.pandemysimulation.person.Person;
import com.mycompany.pandemysimulation.shop.Shop;
import com.mycompany.pandemysimulation.person.supplier.Supplier;
import com.mycompany.pandemysimulation.core.ThreadAgent;
import com.mycompany.pandemysimulation.map.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author kacper
 */
public class MapManager {

    private Map map;
    private PathFinder pedestrianPathFinder;
    private PathFinder roadPathFinder;
    private DeadlockFinder pedestrianDeadlockFinder;
    private DeadlockFinder roadDeadlockFinder;

    public MapManager(Map map) {
        this.map = map;
        this.pedestrianPathFinder = new PathFinder(map.getPedestrianDirections(), map.getLocations());
        this.roadPathFinder = new PathFinder(map.getRoadDirections(), map.getLocations());
        this.pedestrianDeadlockFinder = new DeadlockFinder(map.getPedestrianDirections(), map.getLocations());
        this.roadDeadlockFinder = new DeadlockFinder(map.getRoadDirections(), map.getLocations());
    }

    public List<Person> findDeadLockPedestrian(List<Person> agents) {
        List<Person> pedestrians = agents.stream().filter(agent->agent instanceof Client).collect(Collectors.toList());
        return findDeadlock(pedestrianDeadlockFinder, pedestrians).stream().collect(Collectors.toList());
    }

    public List<Person> findDeadLockRoad(List<Person> agents) {
        List<Person> cars = agents.stream().filter(agent->agent instanceof Supplier).collect(Collectors.toList());
        return findDeadlock(roadDeadlockFinder, cars).stream().collect(Collectors.toList());
    }

    private List<Person> findDeadlock(DeadlockFinder deadlockFinder, List<Person> agents) {
        deadlockFinder.reset();
        for (Person agent : agents) {
            deadlockFinder.addEdge(agent.getNextLocation(), agent.getCurrentLocation());
        }
        List<Location> rsult = deadlockFinder.findDeadlock();
        List<Person> agentsToRemove = new LinkedList<>();
        for (Person agent : agents) {
            Location current = agent.getCurrentLocation();
            if ((!(current instanceof Shop)) && rsult.contains(agent.getCurrentLocation())) {
                agentsToRemove.add(agent);
            }
        }
        return agentsToRemove;
    }

    public Map getMap() {
        return map;
    }

    public PathFinder getPedestrianPathFinder() {
        return pedestrianPathFinder;
    }

    public PathFinder getRoadPathFinder() {
        return roadPathFinder;
    }
}
