/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.person;

import com.mycompany.pandemysimulation.core.MainLoopAgent;
import com.mycompany.pandemysimulation.core.ThreadAgent;
import com.mycompany.pandemysimulation.core.map.Location;
import com.mycompany.pandemysimulation.core.map.PathFinder;
import com.mycompany.pandemysimulation.core.ui.VisibleComponent;
import com.mycompany.pandemysimulation.core.utils.Coordinates;
import com.mycompany.pandemysimulation.core.utils.Utils;
import com.mycompany.pandemysimulation.shop.Shop;
import com.mycompany.pandemysimulation.shop.retailshop.RetailShop;
import com.mycompany.pandemysimulation.shop.wholesaleshop.WholesaleShop;
import java.util.List;
import java.util.Random;

/**
 *
 * @author kacper
 */
public abstract class Person extends ThreadAgent {

    private boolean sick;
    private int shopsVisitedWhileSick;
    private boolean vaccinated;
    private boolean wearingMask;
    private Location nextLocation;
    private Location currentLocation;
    private PathFinder pathFinder;
    private Shop currentShop;
    private long lastTime;
    private ProductStorage productStorage;

    protected Person(int productStoreCapacity, boolean sick, boolean vaccinated, boolean wearingMask, Location nextLocation, Location currentLocation, double xPos, double yPos, VisibleComponent visibleComponent, PathFinder pathFinder) {
        super(xPos, yPos, visibleComponent);
        this.sick = sick;
        this.shopsVisitedWhileSick = 0;
        this.vaccinated = vaccinated;
        this.wearingMask = wearingMask;
        this.nextLocation = nextLocation;
        this.currentLocation = currentLocation;
        this.pathFinder = pathFinder;
        this.productStorage = new ProductStorage(productStoreCapacity);
    }

    protected abstract Shop generateNextGoal();

    protected boolean start() {
        try {
            currentLocation.enter(this);
            return true;
        } catch (InterruptedException e) {
            try {
                currentLocation.leave(this);
            } catch (IllegalMonitorStateException e2) {

            } finally {
                return false;
            }
        }
    }

    /**
     *
     * @return
     */
    @Override
    protected boolean update() {
        try {
            currentShop = generateNextGoal();
            goToShop(currentShop);
            passDisease();
            processShop(currentShop);
        } catch (Exception e) {
            if (currentLocation != null) {
                try {
                    currentLocation.leave(this);
                } catch (Exception e2) {
                }
            }
            if (nextLocation != null) {
                try {
                    nextLocation.leave(this);
                } catch (Exception e2) {
                }
            }
            return false;
        }
        return true;
    }

    protected abstract void processShop(Shop shop) throws InterruptedException;

    protected void goToShop(Shop goal) throws InterruptedException {
        List<Location> path = searchForPath(goal);
        for (Location next : path) {
            nextLocation = next;
            next.enter(this);
            currentLocation.leave(this);
            transfer();
            currentLocation = nextLocation;
            onMoved();
        }
        if (sick) {
            shopsVisitedWhileSick++;
            if (shopsVisitedWhileSick > getSimulation().getWorldData().getShopVisitedWhileSick()) {
                sick = false;
            }
        }
    }

    protected void onMoved() {
    }

    protected void transfer() throws InterruptedException {
        long lastTime = System.currentTimeMillis();
        double targetX = Coordinates.mapToWorld(nextLocation.getIdX());
        double targetY = Coordinates.mapToWorld(nextLocation.getIdY());
        double speed = 100;
        while (Math.abs(getxPos() - targetX) > 2 || Math.abs(getyPos() - targetY) > 2) {
            long curTime = System.currentTimeMillis();
            double deltaTimeInSec = ((double) curTime - lastTime) / 1000;
            double delta = speed * deltaTimeInSec;
            if (this.getxPos() < targetX) {
                setxPos(getxPos() + delta);
            } else if (this.getxPos() > targetX) {
                setxPos(getxPos() - delta);
            }
            if (getyPos() < targetY) {
                setyPos(getyPos() + delta);
            } else if (getyPos() > targetY) {
                setyPos(getyPos() - delta);
            }
            lastTime = curTime;
            Thread.sleep(40);
        }
        setxPos(targetX);
        setyPos(targetY);
    }

    private List<Location> searchForPath(Location to) {
        return this.pathFinder.findPath(currentLocation.getIdX(), currentLocation.getIdY(), to.getIdX(), to.getIdY());
    }

    private void passDisease() {
        double transmisionRate = getSimulation().getWorldData().getTransmissionRate();
        if (isSick()) {
            for (Person person : currentShop.getCopyOfPeopleInside()) {
                if (person != this && !person.isSick() && new Random().nextDouble() < transmisionRate) {
                    person.infect();
                }
            }
        } else {
            for (Person person : currentShop.getCopyOfPeopleInside()) {
                if (person != this && person.isSick() && new Random().nextDouble() < person.getOutgoingTransmissionModifiers() * transmisionRate) {
                    this.infect();
                    return;
                }
            }
        }
    }

    protected void infect() {
        if (new Random().nextDouble() < getIngoingTransmissionModifiers()) {
            this.sick = true;
            this.shopsVisitedWhileSick = 0;
        }
    }

    protected double getIngoingTransmissionModifiers() {
        double base = 1.0;
        if (isVaccinated()) {
            base *= getSimulation().getWorldData().getVaccineRate();
        }
        if (isWearingMask()) {
            base *= getSimulation().getWorldData().getMaskEffect();
        }
        return base;
    }

    protected double getOutgoingTransmissionModifiers() {
        double base = 1.0;
        if (isWearingMask()) {
            base *= getSimulation().getWorldData().getMaskEffect();
        }
        return base;
    }

    protected RetailShop getRandomRetailShop(Shop current) {
        Shop shop;
        while (!((shop = getRandomShop(current)) instanceof RetailShop));
        return (RetailShop) shop;
    }

    protected WholesaleShop getRandomWholesaleShop(Shop current) {
        Shop shop;
        while (!((shop = getRandomShop(current)) instanceof WholesaleShop));
        return (WholesaleShop) shop;
    }

    protected Shop getRandomShop(Shop current) {
        List<MainLoopAgent> agents = getSimulation().getMainLoopAgents();
        MainLoopAgent agent;
        do {
            agent = Utils.getRandomFromList(agents);

        } while (!(agent instanceof Shop) || (current != null && agent == current));
        return (Shop) agent;
    }

    protected ProductStorage getProductStorage() {
        return productStorage;
    }

    public boolean isSick() {
        return sick;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public boolean isWearingMask() {
        return wearingMask;
    }

    public Location getNextLocation() {
        return nextLocation;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setSick(boolean sick) {
        this.sick = sick;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public Shop getCurrentShop() {
        return currentShop;
    }

    public PathFinder getPathFinder() {
        return pathFinder;
    }
}
