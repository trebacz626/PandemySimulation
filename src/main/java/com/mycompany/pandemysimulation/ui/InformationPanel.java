/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.Client;
import com.mycompany.pandemysimulation.SimulationObject;
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
    private ClientViewController clientController;
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
        if (currentSo instanceof Client){
            clientController.update();
        }else{
            informationController.update();
        }
    }
    
    private void showClientInformation(Client client) throws IOException{
        FXMLLoader loader = UIManager.getFXMLLoader("clientView");
        Scene scene = new Scene(loader.load());
        clientController = loader.<ClientViewController<Client>>getController();
        clientController.setSimulationObject(client);
        stage.setScene(scene);
        stage.setOnCloseRequest(event ->Platform.exit());
        stage.show();
    }
    
    private void showOtherInformation(SimulationObject so) throws IOException{
        FXMLLoader loader = UIManager.getFXMLLoader("informationPanel");
        Scene scene = new Scene(loader.load());
        informationController = (InformationPanelController) loader.getController();
        stage.setScene(scene);
        stage.setOnCloseRequest(event ->Platform.exit());
        stage.show();
        informationController.displayInfo(so);
    }
}
