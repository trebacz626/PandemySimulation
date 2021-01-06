/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author kacper
 */
public class VisibleComponent implements EventHandler<MouseEvent>{
    private SimulationObject simulationObject;
    private Image image;
    private ImageView imageView;
    private UIManager uiManager;
    
    public VisibleComponent(String imageName, UIManager uiManager, int sizeX, int sizeY){
        image = new Image(getClass().getResource("image/"+imageName).toString(), sizeX, sizeY, false, false);
        imageView = new ImageView(image);
        imageView.setFitHeight(sizeX);
        imageView.setFitWidth(sizeY);
        this.uiManager = uiManager;
    }
    
    public Image getImage(){
        return image;
    }
    
    public ImageView getImageView(){
        return imageView;
    }
    
//    public void setImageView(ImageView imageView){
//        this.imageView = imageView;
//    }
    
    public void setSimulationObject(SimulationObject so){
        this.simulationObject = so;
        this.imageView.setOnMouseClicked(this);
        update();
    }
    
    public void update(){
        this.imageView.setLayoutX(Coordinates.toUIX(simulationObject.getxPos()));
        this.imageView.setLayoutY(Coordinates.toUIY(simulationObject.getyPos()));
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("mouse click detected! "+event.getSource());
        System.out.println(this.simulationObject.toString());
        uiManager.getInformationPanelController().displayInfo(simulationObject);
    }
    
}
