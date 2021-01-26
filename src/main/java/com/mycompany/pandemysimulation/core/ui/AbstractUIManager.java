/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core.ui;

import com.mycompany.pandemysimulation.core.Manager;
import com.mycompany.pandemysimulation.core.SimulationObject;

/**
 *
 * @author kacper
 */
public abstract class AbstractUIManager extends Manager{

    /**
     * Shows information about a given simulation Object
     * 
     * @param simulationObject
     */
    public abstract void showInformation(SimulationObject simulationObject);

    /**
     *  Adds Visible Component 
     * 
     * @param visibleComponent
     */
    public void addVisibleComponent(VisibleComponent visibleComponent){
        visibleComponent.setUIManager(this);
    };

    /**
     *  Removes Visible Component
     * 
     * @param visibleComponent
     */
    public abstract void removeVisibleComponent(VisibleComponent visibleComponent);
    
}
