/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core.ui;

import com.mycompany.pandemysimulation.core.Simulation;
import com.mycompany.pandemysimulation.core.SimulationObject;
import com.mycompany.pandemysimulation.ui.UIManager;

/**
 *
 * @author kacper
 */
public abstract class AbstractVisibleComponent {
    private AbstractUIManager uiManager;
    private SimulationObject simulationObject;
    
    public void update(){
    }
    
    public void setSimulationObject(SimulationObject so){
        this.simulationObject = so;
        update();
    }
    
    protected void setUIManager(AbstractUIManager uiManager){
        this.uiManager = uiManager;
    }
    
    protected SimulationObject getSimulationObject(){
        return simulationObject;
    }
    
    protected AbstractUIManager getUIManager(){
        return uiManager;
    }
    
}
