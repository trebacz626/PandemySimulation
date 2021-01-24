/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.core.Simulation;
import com.mycompany.pandemysimulation.core.SimulationObject;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author kacper
 */
public class InformationPanel {
    
    private Simulation simulation;
    private SimulationObject currentSo;
    private SimulationObjectViewController simulationObjectViewController;
    
    private Stage stage;
    
    public InformationPanel(Simulation simulation) throws IOException{
        this.simulation = simulation;
        FXMLLoader loader = UIManager.getFXMLLoader("simulationObjectView");
        Scene scene = new Scene(loader.load());
        simulationObjectViewController = loader.<SimulationObjectViewController<SimulationObject>>getController();
        stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(event ->Platform.exit());
        stage.show();
    }
    
    
    public void showInformation(SimulationObject so){
        try{
            currentSo = so;
            showInformation(currentSo.getVisibleComponent().getViewName());
        }catch(Exception e ){
            e.printStackTrace();
            Platform.exit();
        }
    }
    
    public void update(){
        if(currentSo == null){
            return;
        }
        simulationObjectViewController.update();
    }
    
    private void showInformation(String viewName) throws IOException{
        FXMLLoader loader = UIManager.getFXMLLoader(viewName);
        Scene scene = new Scene(loader.load());
        simulationObjectViewController = loader.<SimulationObjectViewController<SimulationObject>>getController();
        simulationObjectViewController.setInformationPanel(this);
        simulationObjectViewController.setSimulation(simulation);
        simulationObjectViewController.setSimulationObject(currentSo);
        simulationObjectViewController.start();
        stage.setScene(scene);
        stage.setOnCloseRequest(event ->Platform.exit());
        stage.show();
    }
    
    protected void setSimulation(Simulation simulation){
        this.simulation = simulation;
    }
    
    protected void onRemove() throws IOException{
        currentSo = null;
        showInformation("simulationObjectView");
    }
}
