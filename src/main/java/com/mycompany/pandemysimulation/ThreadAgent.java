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
    private boolean isAlive;
    public ThreadAgent(double xPos, double yPos, VisibleComponent visibleComponent) {
        super(xPos, yPos, visibleComponent);
        isAlive = true;
    }

    @Override
    public void run() {
        this.start();
//        while(isAlive){
            this.update();
//            try{Thread.sleep(40);}catch(Exception e){};
//        }
    }
    
    public void kill(){
        isAlive = false;
    }
    
    
}
