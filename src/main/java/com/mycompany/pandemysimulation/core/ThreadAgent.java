/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core;

import com.mycompany.pandemysimulation.core.ui.VisibleComponent;

/**
 *
 * @author kacper
 */
public abstract class ThreadAgent extends SimulationAgent implements Runnable{
    private Thread thread;
    protected ThreadAgent(double xPos, double yPos, VisibleComponent visibleComponent) {
        super(xPos, yPos, visibleComponent);
    }

    @Override
    public void run() {
        thread = Thread.currentThread();
        if(!this.start())
            return;
        while(this.update());
    }
    
    public void kill(){
        thread.interrupt();
    }
    
    
}
