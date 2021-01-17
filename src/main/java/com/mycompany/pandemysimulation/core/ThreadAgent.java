/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core;

import com.mycompany.pandemysimulation.ui.VisibleComponent;

/**
 *
 * @author kacper
 */
public abstract class ThreadAgent extends SimulationAgent implements Runnable{
    private Thread thread;
    public ThreadAgent(double xPos, double yPos, VisibleComponent visibleComponent) {
        super(xPos, yPos, visibleComponent);
    }

    @Override
    public void run() {
        thread = Thread.currentThread();
        if(!this.start())
            return;
        while(this.update());
        System.out.println("Thread dies"+thread);
    }
    
    public void kill(){
        thread.interrupt();
    }
    
    
}
