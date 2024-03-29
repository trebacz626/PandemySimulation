/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.core.Simulation;
import com.mycompany.pandemysimulation.core.SimulationObject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 * A controller for Simulation Object View Controller
 *
 * @author kacper
 * @param <T>
 */
public class SimulationObjectViewController<T extends SimulationObject> implements Initializable {

    @FXML
    private Text title;
    @FXML
    private ImageView profilrImage;
    @FXML
    private Text posX;
    @FXML
    private Text posY;

    private Simulation simulation;
    private T curSimulationObject;
    private InformationPanel informationPanel;

    /**
     *
     * Initial set op of view controller.
     * 
     */
    protected void start() {

    }

    /**
     *
     * Updates view state according to SimulationObject data
     * 
     */
    protected void update() {
        if (curSimulationObject != null) {
            posX.setText(String.valueOf((int) curSimulationObject.getxPos()));
            posY.setText(String.valueOf((int) curSimulationObject.getyPos()));
            profilrImage.setImage(curSimulationObject.getVisibleComponent().getImage());
        }
    }

    /**
     *
     * @param so
     */
    protected void setSimulationObject(T so) {
        curSimulationObject = so;
        update();
    }

    /**
     *
     * @param simulation
     */
    protected void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    /**
     *
     * @param panel
     */
    protected void setInformationPanel(InformationPanel panel) {
        this.informationPanel = panel;
    }

    /**
     *
     * @return
     */
    protected T getSimulationObject() {
        return curSimulationObject;
    }

    /**
     *
     * @return
     */
    protected Simulation getSimulation() {
        return simulation;
    }

    /**
     *
     * @return
     */
    protected InformationPanel getInfromationPanel() {
        return informationPanel;
    }

    /**
     *
     */
    protected void removeSimulationObject() {
        curSimulationObject = null;
    }

    /**
     *
     * @return
     */
    protected Text getTitleTextField() {
        return title;
    }

    /**
     *
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

}
