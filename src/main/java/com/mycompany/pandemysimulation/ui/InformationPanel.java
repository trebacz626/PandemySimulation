/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.Client;
import com.mycompany.pandemysimulation.Supplier;
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
    private SimulationObject currentSo;
    private SimulationObjectViewController simulationObjectViewController;
    private InformationPanelController informationController;
    
    private Stage stage;
    
    public InformationPanel() throws IOException{
        FXMLLoader loader = UIManager.getFXMLLoader("informationPanel");
        Scene scene = new Scene(loader.load());
        informationController = (InformationPanelController) loader.getController();
        stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(event ->Platform.exit());
        stage.show();
    }
    
    
    public void showInformation(SimulationObject so){
        try{
        currentSo = so;
        if (currentSo instanceof Client){
            showClientInformation((Client)so);
        }else if(currentSo instanceof  Supplier){
            showSupplierInformation((Supplier)so);
        }else{
            showOtherInformation(so);
        }
        }catch(Exception e ){
            Platform.exit();
        }
    }
    
    public void update(){
        if(currentSo == null){
            return;
        }
        if (currentSo instanceof Client || currentSo instanceof Supplier){
            simulationObjectViewController.update();
        }else{
            informationController.update();
        }
    }
    
    private void showClientInformation(Client client) throws IOException{
        FXMLLoader loader = UIManager.getFXMLLoader("clientView");
        Scene scene = new Scene(loader.load());
        simulationObjectViewController = loader.<ClientViewController<Client>>getController();
        simulationObjectViewController.setSimulationObject(client);
        simulationObjectViewController.start();
        showInformation(scene);
    }
    
    private void showSupplierInformation(Supplier supplier) throws IOException{
        FXMLLoader loader = UIManager.getFXMLLoader("supplierView");
        Scene scene = new Scene(loader.load());
        simulationObjectViewController = loader.<SupplierViewController<Supplier>>getController();
        simulationObjectViewController.setSimulationObject(supplier);
        simulationObjectViewController.start();
        showInformation(scene);
    }
    
    private void showOtherInformation(SimulationObject so) throws IOException{
        FXMLLoader loader = UIManager.getFXMLLoader("informationPanel");
        Scene scene = new Scene(loader.load());
        informationController = (InformationPanelController) loader.getController();
        showInformation(scene);
        informationController.displayInfo(so);
    }
    
    private void showInformation(Scene scene){
        stage.setScene(scene);
        stage.setOnCloseRequest(event ->Platform.exit());
        stage.show();
    }
}
