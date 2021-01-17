/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core;

import com.mycompany.pandemysimulation.ui.VisibleComponent;

/**
 *
 * @author kacper
 */
public abstract class MainLoopAgent extends SimulationAgent{
    public MainLoopAgent(double xPos, double yPos, VisibleComponent visibleComponent) {
        super(xPos, yPos, visibleComponent);
        
    }
}
