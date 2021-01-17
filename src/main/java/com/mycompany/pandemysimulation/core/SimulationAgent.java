/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core;

import com.mycompany.pandemysimulation.ui.VisibleComponent;
import java.util.Random;

/**
 *
 * @author kacper
 */
public abstract class SimulationAgent extends SimulationObject{
    
    public SimulationAgent(double xPos, double yPos, VisibleComponent visibleComponent) {
        super(xPos, yPos, visibleComponent);
    }
    
    protected abstract boolean start();
    
    protected abstract boolean update();
}
