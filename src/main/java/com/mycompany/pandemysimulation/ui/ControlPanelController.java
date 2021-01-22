/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.core.Simulation;
import com.mycompany.pandemysimulation.core.WorldData;
import com.mycompany.pandemysimulation.person.ClientFactory;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    
    @FXML
    private Text date;
    
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    private Simulation simulation;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public void start() {
        transmissionRate.setText(formatDouble(simulation.getWorldData().getTransmissionRate()));
        maskEffect.setText(formatDouble(simulation.getWorldData().getMaskEffect()));
        lockdownTreshold.setText(formatDouble(simulation.getWorldData().getLockDownThreshold()));
        vaccinatedRate.setText(formatDouble(simulation.getWorldData().getVaccineRate()));
        shopsWhileSick.setText(String.valueOf(simulation.getWorldData().getShopVisitedWhileSick()));
        
        updateRates.setOnMouseClicked(event->{
            updateWorldData();
        });
        
        createClient.setOnMouseClicked(event->{
            simulation.addClient();
        });
        
        createSupplier.setOnMouseClicked(event->{
            simulation.addSupplier();
        });
        
        
    }

    public void update() {
        numberOfPeople.setText(String.valueOf(simulation.getWorldData().getNumberOfPeople()));
        infectedPeople.setText(formatDouble(simulation.getWorldData().getSickPercentage() * 100));
        date.setText(dateFormat.format(simulation.getCurrentDate()));
    }

    private String formatDouble(double arg) {
        return String.format("%.2f", arg);
    }

    private void updateWorldData() {
        try {
            simulation.getWorldData().setTransmissionRate(Double.parseDouble(transmissionRate.getText()));
            simulation.getWorldData().setMaskEffect(Double.parseDouble(maskEffect.getText()));
            simulation.getWorldData().setLockDownThreshold(Double.parseDouble(lockdownTreshold.getText()));
            simulation.getWorldData().setVaccineRate(Double.parseDouble(vaccinatedRate.getText()));
            simulation.getWorldData().setShopVisitedWhileSick(Integer.parseInt(shopsWhileSick.getText()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
