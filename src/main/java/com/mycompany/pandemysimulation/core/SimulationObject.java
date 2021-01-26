/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core;

import com.mycompany.pandemysimulation.core.ui.VisibleComponent;

/**
 *
 * Basic class for any object that will interact with simulation environment.
 * 
 * @author kacper
 */
public abstract class SimulationObject {

    private double xPos;
    private double yPos;
    private VisibleComponent visibleComponent;
    private Simulation simulation;
    private boolean isAlive;

    /**
     *
     * Constructor
     * 
     * @param xPos pos x of agent
     * @param yPos pos y of agent
     * @param visibleComponent visible component of agent
     */
    protected SimulationObject(double xPos, double yPos, VisibleComponent visibleComponent) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.visibleComponent = visibleComponent;
        this.visibleComponent.setSimulationObject(this);
    }

    /**
     *
     * Returns x position of object in world coordinates
     * 
     * @return
     */
    public double getxPos() {
        return xPos;
    }

    /**
     *
     * returns y position of object in world coordinates
     * 
     * @return 
     */
    public double getyPos() {
        return yPos;
    }

    /**
     *
     * Returns Visible Component associated with object
     * 
     * @return 
     */
    public VisibleComponent getVisibleComponent() {
        return visibleComponent;
    }

    /**
     *
     * Returns instance of current simulation
     * 
     * @return
     */
    protected Simulation getSimulation() {
        return simulation;
    }

    /**
     *
     * Sets current simulation
     * 
     * @param simulation
     */
    protected void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    /**
     *
     * Sets object position x in world coordinates
     * 
     * @param xPos
     */
    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    /**
     *
     * Sets object position y in world coordinates
     * 
     * @param yPos
     */
    public void setyPos(double yPos) {
        this.yPos = yPos;
    }
}
