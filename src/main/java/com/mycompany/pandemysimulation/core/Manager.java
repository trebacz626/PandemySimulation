/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core;

/**
 *
 * It is an utility than manages certain part of simulation
 * 
 * @author kacper
 */
public abstract class Manager {

    private Simulation simulation;

    /**
     *
     * Actions performed on initialization
     * 
     * @throws Exception
     */
    public void start() throws Exception {

    }

    /**
     *
     * Actions performed during update
     * 
     */
    public void update() {

    }

    /**
     *
     * @return returns simulation instance
     */
    protected Simulation getSimulation() {
        return simulation;
    }

    /**
     *
     * Sets simulation instance
     * 
     * @param simulation
     */
    protected void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}
