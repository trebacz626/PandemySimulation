/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.person.Person;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author kacper
 */
public abstract class PersonViewController<T extends Person> extends SimulationObjectViewController<T> {

    @FXML
    private Text isSick;
    @FXML
    private Text isVaccinated;
    @FXML
    private Button removeButton;  
    
    public void update(){
        super.update();
        if(getSimulationObject() != null){
            isSick.setText(String.valueOf(getSimulationObject().isSick()));
            isVaccinated.setText(String.valueOf(getSimulationObject().isVaccinated()));
            removeButton.setOnMouseClicked(event -> {
                    getInfromationPanel().onRemove();
                    getSimulation().removeThreadAgent(getSimulationObject());
                    removeSimulationObject();
            });
        }
    }
    
}
