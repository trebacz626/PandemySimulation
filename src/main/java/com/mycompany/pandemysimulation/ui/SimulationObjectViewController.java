/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.core.Simulation;
import com.mycompany.pandemysimulation.core.SimulationObject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author kacper
 */
public class SimulationObjectViewController<T extends SimulationObject>  implements Initializable {

    @FXML
    private Text title;
    @FXML
    private ImageView profilrImage;
    @FXML
    private Text posX;
    @FXML
    private Text posY;
    
    private Simulation simulation;
    private T curSimulationObject;
    
    
    protected void start(){
    
    }
    
    protected void update(){
        if(curSimulationObject != null){
            posX.setText(String.valueOf((int)curSimulationObject.getxPos()));
            posY.setText(String.valueOf((int)curSimulationObject.getyPos()));
            profilrImage.setImage(curSimulationObject.getVisibleComponent().getImage());
        }
    }
    
    protected void setSimulationObject(T so){
        curSimulationObject = so;
        update();
    }
    
    protected void setSimulation(Simulation simulation){
        this.simulation = simulation;
    }
    
    protected T getSimulationObject(){
        return curSimulationObject;
    }
    
    protected Simulation getSimulation(){
        return simulation;
    }
    
    protected void removeSimulationObject(){
        curSimulationObject = null;
    }
    
    protected Text getTitleTextField(){
        return title;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }
    
}
