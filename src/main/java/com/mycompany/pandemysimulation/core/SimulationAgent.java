/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core;

import com.mycompany.pandemysimulation.core.ui.VisibleComponent;

/**
 *
 * A Simulation Object that does something. ( is active)
 * 
 * @author kacper
 */
public abstract class SimulationAgent extends SimulationObject {

    /**
     *  Constructor
     * @param xPos pos x of agent
     * @param yPos pos y of agent
     * @param visibleComponent visible component of agent
     */
    public SimulationAgent(double xPos, double yPos, VisibleComponent visibleComponent) {
        super(xPos, yPos, visibleComponent);
    }

    /**
     *
     * Performs initial agent actions.
     * 
     * @return true if everything is OK false if not
     */
    protected abstract boolean start();

    /**
     *
     * Performs update operation.
     * agent should perform update operation till update returns false.
     * 
     * @return true if everything is OK false if not
     */
    protected abstract boolean update();
}
