/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.core.WorldData;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author kacper
 */
public class ControlPanelController implements Initializable {

    @FXML
    private Button createClient;

    @FXML
    private Button createSupplier;

    @FXML
    private TextField vaccinatedRate;

    @FXML
    private Text infectedPercent;

    @FXML
    private TextField transmissionRate;

    @FXML
    private TextField shopsWhileSick;

    @FXML
    private TextField maskEffect;

    @FXML
    private Text numberOfPeople;

    @FXML
    private TextField lockdownTreshold;

    @FXML
    private Text infectedPeople;
    
    @FXML
    private Button updateRates;

    private WorldData worldData;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setWorldData(WorldData worldData) {
        this.worldData = worldData;
    }

    public void start() {
        transmissionRate.setText(formatDouble(worldData.getTransmissionRate()));
        maskEffect.setText(formatDouble(worldData.getMaskEffect()));
        lockdownTreshold.setText(formatDouble(worldData.getLockDownThreshold()));
        vaccinatedRate.setText(formatDouble(worldData.getVaccineRate()));
        shopsWhileSick.setText(String.valueOf(worldData.getShopVisitedWhileSick()));
        
        updateRates.setOnMouseClicked(event->{
            updateWorldData();
        });
        
    }

    public void update() {
        numberOfPeople.setText(String.valueOf(worldData.getNumberOfPeople()));
        infectedPeople.setText(formatDouble(worldData.getSickPercentage() * 100));
    }

    private String formatDouble(double arg) {
        return String.format("%.2f", arg);
    }

    private void updateWorldData() {
        try {
            worldData.setTransmissionRate(Double.parseDouble(transmissionRate.getText()));
            worldData.setMaskEffect(Double.parseDouble(maskEffect.getText()));
            worldData.setLockDownThreshold(Double.parseDouble(lockdownTreshold.getText()));
            worldData.setVaccineRate(Double.parseDouble(vaccinatedRate.getText()));
            worldData.setShopVisitedWhileSick(Integer.parseInt(shopsWhileSick.getText()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
