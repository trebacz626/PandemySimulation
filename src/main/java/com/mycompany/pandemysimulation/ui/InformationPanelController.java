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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author kacper
 */
public class InformationPanelController implements Initializable {
    
    @FXML
    private Text textField;
    private SimulationObject so;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void displayInfo(SimulationObject displayedObject){
        this.so=displayedObject;
        textField.setText(displayedObject.toString());
    }
    
    public void update(){
        if(so != null){
            displayInfo(so);
        }
    }
    
}
