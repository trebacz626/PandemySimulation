/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.App;
import com.mycompany.pandemysimulation.ui.MapPanelController;
import java.io.IOException;
import javafx.application.Platform;
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
    
    private InformationPanel informationPanel;
    
    public UIManager(Stage primaryStage) throws IOException{
        FXMLLoader loader = getFXMLLoader("mapPanel");
        Parent root = loader.load();
        mapPanelController =  (MapPanelController)loader.getController();
                
        mapPanelScene = new Scene(root);
        primaryStage.setScene(mapPanelScene);
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event ->Platform.exit());
        this.primaryStage = primaryStage;
        
//        FXMLLoader controlLoader = getFXMLLoader("controlPanel");
//        controlPanelScene = new Scene(controlLoader.load());
//        controlPanelController = (ControlPanelController) controlLoader.getController();
//        controlPanelStage = new Stage();
//        controlPanelStage.setScene(controlPanelScene);
//        controlPanelStage.setResizable(false);
//        informationPanelStage.setOnCloseRequest(event ->Platform.exit());
//        controlPanelStage.show();
//        
        informationPanel = new InformationPanel();

    }
    
    protected static FXMLLoader getFXMLLoader(String fxml){
        return new FXMLLoader(App.class.getResource("fxml/"+fxml + ".fxml"));
    }

    public MapPanelController getMapPanelController() {
        return mapPanelController;
    }

    public ControlPanelController getControlPanelController() {
        return controlPanelController;
    }

    public InformationPanel getInformationPanel() {
        return informationPanel;
    }
    
    public void update(){
        this.mapPanelController.draw();
        this.informationPanel.update();
    }
    
    
}
