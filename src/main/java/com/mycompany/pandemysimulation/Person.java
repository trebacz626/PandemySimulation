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
    
    protected List<Location> path;

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
//        synchronized(Person.class){
            currentLocation.enter(this);
//        }
    }
    
    
    protected void update(){
//        while(true){
            System.out.println("while");
            for(Location next: path){
                System.out.println("Iterating");
                next.enter(this);
                currentLocation.leave(this);
                currentLocation = next;
                transfer();
            }
//        }
    }
    
    protected void transfer(){
        System.err.println("transfer");
        long lastTime = System.currentTimeMillis();
        targetX = currentLocation.getXPos();
        targetY = currentLocation.getYPos();
        double speed = 200;
        while(Math.abs(xPos - targetX) > 1 || Math.abs(yPos - targetY) > 1){
            long curTime = System.currentTimeMillis();
            double deltaTimeInSec = ((double)curTime - lastTime)/1000;
//            System.err.println(xPos+" "+yPos);
            double delta = speed*deltaTimeInSec;
            if( this.xPos < targetX){
                xPos+=delta;
            }else if(this.xPos > targetX){
                xPos-=delta;
            }
            if(yPos < targetY){
                yPos+=delta;
            }else if(yPos > targetY){
                yPos-=delta;
            }
            lastTime = curTime;
            try {
                Thread.sleep(40);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        this.xPos = targetX;
        this.yPos = targetY;
        System.err.println("Transfer finished");
    }
    
//    protected void update() {
//        while(true){
//            Shop shop = getNextShop();
//            takeJourneyTo(shop);
//            serve(shop);
//        }
//    }
    
    protected void setPath(List<Location> path){
        this.path = path;
    }

//    protected void update() {
//        long curTime = System.currentTimeMillis();
//        double deltaTimeInSec = ((double)curTime - lastTime)/1000;
//        double speed = 50;
//        double deltaX = speed*deltaTimeInSec;
//        this.xPos += this.xPos < targetX ? deltaX : -deltaX;
//        double deltaY = speed*deltaTimeInSec;
//        this.yPos += this.yPos < targetY ? deltaY : -deltaY;
//        if( Math.abs(xPos - targetX) < 5 && Math.abs(yPos - targetY) <5){
//            List<Shop> shops = App.simulation.getShops();
//            Shop destination = Utils.getRandomFromList(shops);
//            targetX = destination.getxPos();
//            targetY = destination.getyPos();
//        }
//        lastTime = curTime;
//    }

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
