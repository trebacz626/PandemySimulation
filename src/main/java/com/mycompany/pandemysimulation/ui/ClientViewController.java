/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.App;
import com.mycompany.pandemysimulation.Client;
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
public class ClientViewController< T extends Client> extends PersonViewController<T> {

    private T curClient;
    
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
    
    public void setSimulationObject(T so){
        curClient = so;
        update();
    }
    
    public void update(){
        if(curClient != null){
            posX.setText(String.valueOf((int)curClient.getxPos()));
            posY.setText(String.valueOf((int)curClient.getyPos()));
            profilrImage.setImage(curClient.getVisibleComponent().getImage());
            isSick.setText(String.valueOf(curClient.isSick()));
            isVaccinated.setText(String.valueOf(curClient.isVaccinated()));
            pesel.setText(curClient.getPesel());
            name.setText(curClient.getFirstName());
            surname.setText(curClient.getLastName());
            removeButton.setOnMouseClicked(event -> {
                App.simulation.removeThreadAgent(curClient);
                curClient = null;
            });
        }
    }
    
}
