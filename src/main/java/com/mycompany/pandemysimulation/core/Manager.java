/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core;

import com.mycompany.pandemysimulation.core.Simulation;

/**
 *
 * @author kacper
 */
public abstract class Manager {

    private Simulation simulation;

    public void start() throws Exception{
    
    }

    public void update(){
    
    }

    protected Simulation getSimulation() {
        return simulation;
    }

    
    
    protected void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}
