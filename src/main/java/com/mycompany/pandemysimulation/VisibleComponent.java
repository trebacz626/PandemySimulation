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
    
    private int sizeX;
    private int sizeY;
    
    public VisibleComponent(String imageName, int sizeX, int sizeY){
        image = new Image(getClass().getResource("image/"+imageName).toString(), sizeX, sizeY, false, false);
        imageView = new ImageView(image);
        imageView.setFitHeight(sizeX);
        imageView.setFitWidth(sizeY);
    }
    
    public Image getImage(){
        return image;
    }
    
    public ImageView getImageView(){
        return imageView;
    }
    
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
        App.uiManager.getInformationPanel().showInformation(simulationObject);
    }
    
    public int getSizeX(){
        return sizeX;
    }
    
    public int getSizeY(){
        return sizeY;
    }
    
}
