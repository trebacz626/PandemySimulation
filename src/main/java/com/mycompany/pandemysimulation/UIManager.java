/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author kacper
 */
public class UIManager {
    private MapPanelController mapPanelController;
    private Scene mapPanelScene;
    private Stage primaryStage;
    
    private ControlPanelController controlPanelController;
    private Scene controlPanelScene;
    private Stage controlPanelStage;
    
    private InformationPanelController informationPanelController;
    private Scene informationPanelScene;
    private Stage informationPanelStage;
    
    public UIManager(Stage primaryStage) throws IOException{
        FXMLLoader loader = getFXMLLoader("mapPanel");
        Parent root = loader.load();
        mapPanelController =  (MapPanelController)loader.getController();
                
        mapPanelScene = new Scene(root);
        primaryStage.setScene(mapPanelScene);
        primaryStage.show();
        this.primaryStage = primaryStage;
        
//        FXMLLoader controlLoader = getFXMLLoader("controlPanel");
//        controlPanelScene = new Scene(controlLoader.load());
//        controlPanelController = (ControlPanelController) controlLoader.getController();
//        controlPanelStage = new Stage();
//        controlPanelStage.setScene(controlPanelScene);
//        controlPanelStage.show();
//        
        FXMLLoader informationLoader = getFXMLLoader("informationPanel");
        informationPanelScene = new Scene(informationLoader.load());
        informationPanelController = (InformationPanelController) informationLoader.getController();
        informationPanelStage = new Stage();
        informationPanelStage.setScene(informationPanelScene);
        informationPanelStage.show();
    }
    
    private static FXMLLoader getFXMLLoader(String fxml){
        return new FXMLLoader(App.class.getResource("fxml/"+fxml + ".fxml"));
    }

    public MapPanelController getMapPanelController() {
        return mapPanelController;
    }

    public ControlPanelController getControlPanelController() {
        return controlPanelController;
    }

    public InformationPanelController getInformationPanelController() {
        return informationPanelController;
    }
    
    
}
