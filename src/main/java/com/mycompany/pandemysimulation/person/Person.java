/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.person;

import com.mycompany.pandemysimulation.Coordinates;
import com.mycompany.pandemysimulation.map.PathFinder;
import com.mycompany.pandemysimulation.ui.VisibleComponent;
import com.mycompany.pandemysimulation.shop.Shop;
import com.mycompany.pandemysimulation.map.Location;
import com.mycompany.pandemysimulation.core.ThreadAgent;
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
    private Location nextLocation;
    private Location currentLocation;
    private boolean waiting;
    private PathFinder pathFinder;
    private Shop currentGoal;
    

    protected long lastTime;
    

    public Person(boolean sick, int shopsVisitedWhileSick, boolean vaccinated, Location nextLocation, Location currentLocation, boolean waiting, double xPos, double yPos, VisibleComponent visibleComponent, PathFinder pathFinder) {
        super(xPos, yPos, visibleComponent);
        this.sick = sick;
        this.shopsVisitedWhileSick = shopsVisitedWhileSick;
        this.vaccinated = vaccinated;
        this.nextLocation = nextLocation;
        this.currentLocation = currentLocation;
        this.waiting = waiting;
        this.pathFinder = pathFinder;
    }
    
    
    protected abstract Shop generateNextGoal();
    
    
    
    protected boolean start() {
        try{
            currentLocation.enter(this);
            return true;
        }catch(InterruptedException e){
            currentLocation.leave(this);
            return false;
        }
    }
    
    
    protected boolean update(){
        try{
            currentGoal = generateNextGoal();
            goToShop(currentGoal);
            processShop(currentGoal);
        }catch(Exception e){
            if(currentLocation!=null){
                try{currentLocation.leave(this);}catch(Exception e2){}
            }
            if(nextLocation!=null){
                try{nextLocation.leave(this);}catch(Exception e2){}
            }
            return false;
        }
        return true;
    }
    
    
    protected abstract void processShop(Shop shop);
    
    protected void goToShop(Shop goal) throws InterruptedException{
            List<Location> path = searchForPath(goal);
            for(Location next: path){
                nextLocation = next;
                next.enter(this);
                currentLocation.leave(this);
                transfer();
                currentLocation = nextLocation;
                onMoved();
            }
    }
    
    protected void onMoved(){}
    
    protected void transfer() throws InterruptedException{
        long lastTime = System.currentTimeMillis();
        double targetX = Coordinates.mapToWorld(nextLocation.getIdX());
        double targetY = Coordinates.mapToWorld(nextLocation.getIdY());
        double speed = 100;
        while(Math.abs(xPos - targetX) > 2 || Math.abs(yPos - targetY) > 2){
            long curTime = System.currentTimeMillis();
            double deltaTimeInSec = ((double)curTime - lastTime)/1000;
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
            Thread.sleep(40);

        }
        this.xPos = targetX;
        this.yPos = targetY;
    }
    
    private List<Location> searchForPath(Location to){
        return this.pathFinder.findPath(currentLocation.getIdX(), currentLocation.getIdY(), to.getIdX(), to.getIdY());
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

    public Location getNextLocation() {
        return nextLocation;
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

    public Shop getCurrentGoal() {
        return currentGoal;
    }
}
