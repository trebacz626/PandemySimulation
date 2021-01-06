/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author kacper
 */
public abstract class Person extends ThreadAgent{
    private boolean sick;
    private int shopsVisitedWhileSick;
    private boolean vaccinated;
    private Location nextStop;
    private Location currentLocation;
    private boolean waiting;
    
    
    protected double targetX;
    protected double targetY;
    protected long lastTime;

    public Person(boolean sick, int shopsVisitedWhileSick, boolean vaccinated, Location nextStop, Location currentLocation, boolean waiting, double xPos, double yPos, VisibleComponent visibleComponent) {
        super(xPos, yPos, visibleComponent);
        this.sick = sick;
        this.shopsVisitedWhileSick = shopsVisitedWhileSick;
        this.vaccinated = vaccinated;
        this.nextStop = nextStop;
        this.currentLocation = currentLocation;
        this.waiting = waiting;
    }
    
    
    protected abstract Location getNextLocation();
    
    protected abstract LinkedList<Location> searchForPath();
    
    
    protected void start() {
        targetX = (new Random()).nextDouble()*300;
        targetY = (new Random()).nextDouble()*300;
        lastTime = System.currentTimeMillis();
    }

    protected void update() {
        long curTime = System.currentTimeMillis();
        double deltaTimeInSec = ((double)curTime - lastTime)/1000;
        double speed = 50;
        double deltaX = speed*deltaTimeInSec;
        this.xPos += this.xPos < targetX ? deltaX : -deltaX;
        double deltaY = speed*deltaTimeInSec;
        this.yPos += this.yPos < targetY ? deltaY : -deltaY;
        if( Math.abs(xPos - targetX) < 5 && Math.abs(yPos - targetY) <5){
            List<Shop> shops = App.simulation.getShops();
            Shop destination = Utils.getRandomFromList(shops);
            targetX = destination.getxPos();
            targetY = destination.getyPos();
        }
        lastTime = curTime;
    }

    public boolean isSick() {
        return sick;
    }

    public int getShopsVisitedWhileSick() {
        return shopsVisitedWhileSick;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public Location getNextStop() {
        return nextStop;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void setSick(boolean sick) {
        this.sick = sick;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }
    
}
