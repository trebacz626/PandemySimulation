/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.App;
import com.mycompany.pandemysimulation.Person;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author kacper
 */
public abstract class PersonViewController<T extends Person> extends SimulationObjectViewController<T> {

    @FXML
    protected Text isSick;
    @FXML
    protected Text isVaccinated;
    @FXML
    protected Button removeButton;  
    
    public void update(){
        super.update();
        if(getSimulationObject() != null){
            isSick.setText(String.valueOf(getSimulationObject().isSick()));
            isVaccinated.setText(String.valueOf(getSimulationObject().isVaccinated()));
            removeButton.setOnMouseClicked(event -> {
                App.simulation.removeThreadAgent(getSimulationObject());
                removeSimulationObject();
            });
        }
    }
    
}
