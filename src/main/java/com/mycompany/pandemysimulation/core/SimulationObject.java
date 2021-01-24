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
public abstract class SimulationObject {

    private double xPos;
    private double yPos;
    private VisibleComponent visibleComponent;
    private Simulation simulation;
    private boolean isAlive;

    protected SimulationObject(double xPos, double yPos, VisibleComponent visibleComponent) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.visibleComponent = visibleComponent;
        this.visibleComponent.setSimulationObject(this);
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

    protected Simulation getSimulation() {
        return simulation;
    }

    protected void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }
}
