/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.COVIDSimulation;
import com.mycompany.pandemysimulation.core.Simulation;
import com.mycompany.pandemysimulation.core.SimulationObject;
import com.mycompany.pandemysimulation.core.ui.AbstractUIManager;
import com.mycompany.pandemysimulation.core.ui.VisibleComponent;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * Class that handles UI related issues. Serves as a bridge between Simulation and UI.
 * 
 * @author kacper
 */
public class UIManager extends AbstractUIManager {

    /**
     *
     * @param fxml
     * @return
     */
    protected static FXMLLoader getFXMLLoader(String fxml) {
//        try{
//            URL localPackage = UIManager.class.getResource("");
//    URL urlLoader = UIManager.class.getProtectionDomain().getCodeSource().getLocation();
//    String localDir = localPackage.getPath();
//    String loaderDir = urlLoader.getPath();
//    System.out.printf("loaderDir = %s\n localDir = %s\n", loaderDir, localDir);
//        }catch(Exception e){
//            e.printStackTrace();
//            System.err.println("eeeee");
//        }; 
//        
//        return new FXMLLoader(UIManager.class.getClassLoader().getResource("target/classes/com/mycompany/pandemysimulation/fxml/"+fxml + ".fxml"));
        return new FXMLLoader(COVIDSimulation.class.getResource("fxml/" + fxml + ".fxml"));
    }

    private MapPanelController mapPanelController;
    private Scene mapPanelScene;
    private Stage primaryStage;

    private ControlPanelController controlPanelController;
    private Scene controlPanelScene;
    private Stage controlPanelStage;

    private InformationPanel informationPanel;

    /**
     *
     * @param primaryStage
     */
    public UIManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     *
     * Sets up 3 main views of the Simulation: map, control Panel, information Panel,
     * 
     * @throws IOException
     */
    public void start() throws IOException {
        FXMLLoader loader = getFXMLLoader("mapPanel");
        Parent root = loader.load();
        mapPanelController = (MapPanelController) loader.getController();

        mapPanelScene = new Scene(root);
        primaryStage.setScene(mapPanelScene);
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> Platform.exit());

        FXMLLoader controlLoader = getFXMLLoader("controlPanel");
        controlPanelScene = new Scene(controlLoader.load());
        controlPanelController = (ControlPanelController) controlLoader.getController();
        controlPanelController.setSimulation(getSimulation());
        controlPanelController.start();
        controlPanelStage = new Stage();
        controlPanelStage.setScene(controlPanelScene);
        controlPanelStage.setResizable(false);
        controlPanelStage.setOnCloseRequest(event -> Platform.exit());
        controlPanelStage.setX(1600);
        controlPanelStage.setY(0);
        controlPanelStage.show();
        informationPanel = new InformationPanel(getSimulation());
    }

    /**
     *
     * Updates UI state
     * 
     */
    public void update() {
        this.mapPanelController.draw();
        this.informationPanel.update();
        this.controlPanelController.update();
    }

    /**
     *
     * Shows Information of SimulationObject.
     * 
     * @param simulationObject
     */
    @Override
    public void showInformation(SimulationObject simulationObject) {
        informationPanel.showInformation(simulationObject);
    }

    /**
     * Adds a VisibleComponent.
     * @param visibleComponent
     */
    @Override
    public void addVisibleComponent(VisibleComponent visibleComponent) {
        super.addVisibleComponent(visibleComponent);
        mapPanelController.addVisibleComponent(visibleComponent);
    }

    /**
     *  Removes a VisibleComponent.
     * @param visibleComponent
     */
    @Override
    public void removeVisibleComponent(VisibleComponent visibleComponent) {
        mapPanelController.removeVisibleComponent(visibleComponent);
        if (visibleComponent == informationPanel.getCurrentObject().getVisibleComponent()) {
            informationPanel.onRemove();
        }
    }
}
