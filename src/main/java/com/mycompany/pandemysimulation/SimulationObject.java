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
public abstract class SimulationObject {
    protected int id;
    protected double xPos;
    protected double yPos;
    protected VisibleComponent visibleComponent;

    public SimulationObject(double xPos, double yPos, VisibleComponent visibleComponent) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.visibleComponent = visibleComponent;
    }

    public int getId() {
        return id;
    }
    

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public VisibleComponent getVisibleComponent() {
        return visibleComponent;
    }
    
    
}
