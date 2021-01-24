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
    public abstract void showInformation(SimulationObject simulationObject);
    public void addVisibleComponent(VisibleComponent visibleComponent){
        visibleComponent.setUIManager(this);
    };
    public abstract void removeVisibleComponent(VisibleComponent visibleComponent);
    
}
