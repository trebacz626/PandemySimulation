/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.person.client.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author kacper
 */
public class ClientViewController< T extends Client> extends PersonViewController<T> {

    @FXML
    private Text surname;

    @FXML
    private Text name;

    @FXML
    private Text pesel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getTitleTextField().setText("Client");
    }

    public void update() {
        super.update();
        if (getSimulationObject() != null) {
            pesel.setText(getSimulationObject().getPesel());
            name.setText(getSimulationObject().getFirstName());
            surname.setText(getSimulationObject().getLastName());
        }
    }

}
