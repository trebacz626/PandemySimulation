/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core.ui;

import com.mycompany.pandemysimulation.core.SimulationObject;
import com.mycompany.pandemysimulation.core.utils.Coordinates;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author kacper
 */
public class VisibleComponent extends AbstractVisibleComponent implements EventHandler<MouseEvent>{
    private Image image;
    private ImageView imageView;
    private String informationPanelName;
    
    private int sizeX;
    private int sizeY;
    
    public VisibleComponent(Image image, int sizeX, int sizeY, String informationPanelName){
        this.image = image;
        imageView = new ImageView(image);
        imageView.setFitHeight(sizeX);
        imageView.setFitWidth(sizeY);
        this.informationPanelName = informationPanelName;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    
//        public VisibleComponent(String imageName, int sizeX, int sizeY, String informationPanelName){
//        image = new Image(App.class.getResource("image/"+imageName).toString(), sizeX, sizeY, false, false);
//        imageView = new ImageView(image);
//        imageView.setFitHeight(sizeX);
//        imageView.setFitWidth(sizeY);
//        this.informationPanelName = informationPanelName;
//        this.sizeX = sizeX;
//        this.sizeY = sizeY;
//    }
    
//    public VisibleComponent(String imageName, int sizeX, int sizeY){
//        image = new Image(App.class.getResource("image/"+imageName).toString(), sizeX, sizeY, false, false);
//        imageView = new ImageView(image);
//        imageView.setFitHeight(sizeX);
//        imageView.setFitWidth(sizeY);
//        this.informationPanelName = "simulationObjectView";
//        this.sizeX = sizeX;
//        this.sizeY = sizeY;
//    }
    
    public VisibleComponent(Image image, int sizeX, int sizeY){
        this.image = image;
        imageView = new ImageView(image);
        imageView.setFitHeight(sizeX);
        imageView.setFitWidth(sizeY);
        this.informationPanelName = "simulationObjectView";
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    
    public Image getImage(){
        return image;
    }
    
    public ImageView getImageView(){
        return imageView;
    }
    @Override
    public void setSimulationObject(SimulationObject so){
        super.setSimulationObject(so);
        this.imageView.setOnMouseClicked(this);
    }
    
    public void update(){
        this.imageView.setLayoutX(Coordinates.toUIX(getSimulationObject().getxPos()) - this.sizeX/2);
        this.imageView.setLayoutY(Coordinates.toUIY(getSimulationObject().getyPos()) - this.sizeY/2);
    }

    @Override
    public void handle(MouseEvent event) {
        getUIManager().showInformation(getSimulationObject());
    }
    
    public int getSizeX(){
        return sizeX;
    }
    
    public int getSizeY(){
        return sizeY;
    }

    public String getViewName() {
        return informationPanelName;
    }
}
