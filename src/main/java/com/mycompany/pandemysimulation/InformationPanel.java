/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

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
    private SimulationObject currentSO;
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
    
    
    public void showInformation(SimulationObject so) throws IOException{
        if (so instanceof Client){
            showClientInformation((Client)so);
        }else{
            showOtherInformation(so);
        }
    }
    
    private void showClientInformation(Client client) throws IOException{
        FXMLLoader loader = UIManager.getFXMLLoader("clientView");
        Scene scene = new Scene(loader.load());
        clientController = (ClientViewController) loader.getController();
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
