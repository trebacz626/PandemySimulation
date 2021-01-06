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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author kacper
 */
public class ClientViewController extends PersonViewController implements Initializable {

    private Client curClient;
    
    @FXML
    private Text surname;

    @FXML
    private Text name;

    @FXML
    private Text pesel;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        title.setText("Client");
    }   
    
    public void setSimulationObject(Client so){
    
    }
    
    public void update(){
    }
    
}
