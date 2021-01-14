/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import com.mycompany.pandemysimulation.core.SimulationObject;
import com.mycompany.pandemysimulation.core.ThreadAgent;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author kacper
 */
public class Intersection extends SimulationObject{
    
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
    
}
