/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core.ui;

import com.mycompany.pandemysimulation.core.SimulationObject;

/**
 *
 * @author kacper
 */
public abstract class AbstractVisibleComponent {
    private AbstractUIManager uiManager;
    private SimulationObject simulationObject;
    
    /**
     *
     */
    public void update(){
    }
    
    /**
     *
     * @param so
     */
    public void setSimulationObject(SimulationObject so){
        this.simulationObject = so;
        update();
    }
    
    /**
     *
     * @param uiManager
     */
    protected void setUIManager(AbstractUIManager uiManager){
        this.uiManager = uiManager;
    }
    
    /**
     *
     * @return
     */
    protected SimulationObject getSimulationObject(){
        return simulationObject;
    }
    
    /**
     *
     * @return
     */
    protected AbstractUIManager getUIManager(){
        return uiManager;
    }
    
}
