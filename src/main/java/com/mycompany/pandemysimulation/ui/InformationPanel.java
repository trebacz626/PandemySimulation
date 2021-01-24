/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.core.SimulationAgent;
import com.mycompany.pandemysimulation.person.client.Client;
import com.mycompany.pandemysimulation.person.supplier.Supplier;
import com.mycompany.pandemysimulation.core.SimulationObject;
import com.mycompany.pandemysimulation.shop.Shop;
import com.mycompany.pandemysimulation.shop.wholesaleshop.WholesaleShop;
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
    
    private SimulationObject currentSo;
    private SimulationObjectViewController simulationObjectViewController;
    
    private Stage stage;
    
    public InformationPanel() throws IOException{
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
        simulationObjectViewController.setSimulationObject(currentSo);
        simulationObjectViewController.start();
        stage.setScene(scene);
        stage.setOnCloseRequest(event ->Platform.exit());
        stage.show();
    }
}
