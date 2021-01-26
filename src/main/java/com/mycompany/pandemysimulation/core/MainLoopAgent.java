/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core;

import com.mycompany.pandemysimulation.core.ui.VisibleComponent;

/**
 *
 * Simulation agent that operates in a main loop
 * 
 * @author kacper
 */
public abstract class MainLoopAgent extends SimulationAgent {

    /**
     *
     * @param xPos pos x of agent
     * @param yPos pos y of agent
     * @param visibleComponent visible component of agent
     */
    public MainLoopAgent(double xPos, double yPos, VisibleComponent visibleComponent) {
        super(xPos, yPos, visibleComponent);

    }
}
