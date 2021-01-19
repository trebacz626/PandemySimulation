/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.App;
import com.mycompany.pandemysimulation.utils.Coordinates;
import com.mycompany.pandemysimulation.core.SimulationObject;
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
        image = new Image(App.class.getResource("image/"+imageName).toString(), sizeX, sizeY, false, false);
        imageView = new ImageView(image);
        imageView.setFitHeight(sizeX);
        imageView.setFitWidth(sizeY);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    
    public VisibleComponent(Image image, int sizeX, int sizeY){
        imageView = new ImageView(image);
        imageView.setFitHeight(sizeX);
        imageView.setFitWidth(sizeY);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
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
        this.imageView.setLayoutX(Coordinates.toUIX(simulationObject.getxPos()) - this.sizeX/2);
        this.imageView.setLayoutY(Coordinates.toUIY(simulationObject.getyPos()) - this.sizeY/2);
    }

    @Override
    public void handle(MouseEvent event) {
        App.uiManager.getInformationPanel().showInformation(simulationObject);
    }
    
    public int getSizeX(){
        return sizeX;
    }
    
    public int getSizeY(){
        return sizeY;
    }
    
}
