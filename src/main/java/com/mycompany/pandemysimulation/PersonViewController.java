/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

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
    
}
