/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.map;

import com.mycompany.pandemysimulation.core.map.AbstractMapManager;
import com.mycompany.pandemysimulation.core.map.DeadlockFinder;
import com.mycompany.pandemysimulation.core.map.Location;
import com.mycompany.pandemysimulation.core.map.Map;
import com.mycompany.pandemysimulation.person.Person;
import com.mycompany.pandemysimulation.shop.Shop;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author kacper
 */
public class MapManager extends AbstractMapManager {

    public MapManager(Map map) {
        super(map);
    }

    @Override
    public void update() {
        List<Person> people = (List<Person>) getSimulation().getThreadAgents().stream().filter(a -> a instanceof Person).map(a -> (Person) a).collect(Collectors.toList());
        List<Person> toDelete = findDeadLockPedestrian(people);
        List<Person> carsToDelete = findDeadLockRoad(people);
        toDelete.addAll(carsToDelete);
        for (Person person : toDelete) {
            getSimulation().removeThreadAgent(person);
        }
    }

    private List<Person> findDeadLockPedestrian(List<Person> agents) {
        List<Person> pedestrians = agents.stream().filter(agent -> agent.getPathFinder() == getPedestrianPathFinder()).collect(Collectors.toList());
        return findDeadlock(getPedestrianDeadlockFinder(), pedestrians).stream().collect(Collectors.toList());
    }

    private List<Person> findDeadLockRoad(List<Person> agents) {
        List<Person> cars = agents.stream().filter(agent -> agent.getPathFinder() == getRoadPathFinder()).collect(Collectors.toList());
        return findDeadlock(getRoadDeadlockFinder(), cars).stream().collect(Collectors.toList());
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
}
