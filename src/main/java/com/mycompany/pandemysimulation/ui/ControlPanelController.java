/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.core.Simulation;
import com.mycompany.pandemysimulation.person.client.Client;
import com.mycompany.pandemysimulation.person.client.ClientFactory;
import com.mycompany.pandemysimulation.person.supplier.Supplier;
import com.mycompany.pandemysimulation.person.supplier.SupplierFactory;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
    
    @FXML
    private TextField vaccinateChance;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        vaccinateChance.setText(formatDouble(simulation.getWorldData().getVaccinateChance()));
        
        updateRates.setOnMouseClicked(event->{
            updateWorldData();
        });
        
        createClient.setOnMouseClicked(event->{
            Client client = ClientFactory.createRandomClient(simulation);
            simulation.addThreadAgent(client);
        });
        
        createSupplier.setOnMouseClicked(event->{
            Supplier supplier = SupplierFactory.createRandomSupplier(simulation);
            simulation.addThreadAgent(supplier);
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
            simulation.getWorldData().setVaccinateChance(Double.parseDouble(vaccinateChance.getText()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
