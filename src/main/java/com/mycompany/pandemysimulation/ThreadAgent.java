/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

/**
 *
 * @author kacper
 */
public abstract class ThreadAgent extends SimulationAgent implements Runnable{
    
    public ThreadAgent(double xPos, double yPos, VisibleComponent visibleComponent) {
        super(xPos, yPos, visibleComponent);
        
    }

    @Override
    public void run() {
        this.start();
        while(true){
            this.update();
        }
    }
    
    
}
