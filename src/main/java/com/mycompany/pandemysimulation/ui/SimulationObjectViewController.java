/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

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
public abstract class SimulationObjectViewController<T extends SimulationObject>  implements Initializable {

    @FXML
    protected Text title;
    @FXML
    protected ImageView profilrImage;
    @FXML
    protected Text posX;
    @FXML
    protected Text posY;
    
    private T curSimulationObject;
    
    public void setSimulationObject(T so){
        curSimulationObject = so;
        update();
    }
    
    public void start(){
    
    }
    
    public void update(){
        if(curSimulationObject != null){
            posX.setText(String.valueOf((int)curSimulationObject.getxPos()));
            posY.setText(String.valueOf((int)curSimulationObject.getyPos()));
            profilrImage.setImage(curSimulationObject.getVisibleComponent().getImage());
        }
    }
    
    protected T getSimulationObject(){
        return curSimulationObject;
    }
    
    protected void removeSimulationObject(){
        curSimulationObject = null;
    }
    
}
