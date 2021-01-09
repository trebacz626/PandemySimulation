/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author kacper
 */
public class Intersection extends SimulationObject implements Location{
    
    private ReentrantLock lock; 
    
    public Intersection(double posX, double posY, VisibleComponent visibleComponent){
        super(posX,posY,visibleComponent);
        lock = new ReentrantLock();
    }
    
    
    public void enter(ThreadAgent threadAgent){
        lock.lock();
    }
    
    public void leave(ThreadAgent threadAgent){
        lock.unlock();
    }
    

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }

    @Override
    public int getIdX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIdY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
