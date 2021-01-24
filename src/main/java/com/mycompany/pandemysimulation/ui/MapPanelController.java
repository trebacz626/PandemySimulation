/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.core.ui.VisibleComponent;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author kacper
 */
public class MapPanelController implements Initializable {

    
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView background;
    
    @FXML
    private AnchorPane layer1;
    
    private LinkedList<VisibleComponent> visibleComponents;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        visibleComponents = new LinkedList();
    }

    public void addVisibleComponent(VisibleComponent vc){
        visibleComponents.add(vc);
        layer1.getChildren().add(vc.getImageView());
    }
    
    public void removeVisibleComponent(VisibleComponent vc){
        visibleComponents.remove(vc);
        layer1.getChildren().remove(vc.getImageView());
    }
    
    public void draw(){
        for(VisibleComponent vc : this.visibleComponents){
            vc.update();
        }
    }
}
