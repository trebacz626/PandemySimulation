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
 * Visible Component of Simulation Object
 * 
 * @author kacper
 */
public class VisibleComponent extends AbstractVisibleComponent implements EventHandler<MouseEvent>{
    private Image image;
    private ImageView imageView;
    private String name;
    
    private int sizeX;
    private int sizeY;
    
    /**
     *
     * Constructor
     * 
     * @param image image representing simulation object
     * @param sizeX size of image in x axis
     * @param sizeY size of image in y axis
     * @param name  name of visible component
     */
    public VisibleComponent(Image image, int sizeX, int sizeY, String name){
        this.image = image;
        imageView = new ImageView(image);
        imageView.setFitHeight(sizeX);
        imageView.setFitWidth(sizeY);
        this.name = name;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /**
     *
     * Constructor with default name
     * 
     * @param image image representing simulation object
     * @param sizeX size of image in x axis
     * @param sizeY size of image in y axis
     */
    
    public VisibleComponent(Image image, int sizeX, int sizeY){
        this(image, sizeX, sizeY, "simulationObjectView");
    }
    
    /**
     *
     * @return returns image of Visible Component
     */
    public Image getImage(){
        return image;
    }
    
    /**
     *
     * @return returns Image View of Visual Component
     */
    public ImageView getImageView(){
        return imageView;
    }

    /**
     *
     * @param simulationObject set simulationObject for VisualComponent
     */
    @Override
    public void setSimulationObject(SimulationObject simulationObject){
        super.setSimulationObject(simulationObject);
        this.imageView.setOnMouseClicked(this);
    }
    
    /**
     *  Method that updates VisibleComponent using information from its SimulationObject
     */
    @Override
    public void update(){
        this.imageView.setLayoutX(Coordinates.toUIX(getSimulationObject().getxPos()) - this.sizeX/2);
        this.imageView.setLayoutY(Coordinates.toUIY(getSimulationObject().getyPos()) - this.sizeY/2);
    }

    /**
     *
     * @param event mouse click handler
     */
    @Override
    public void handle(MouseEvent event) {
        getUIManager().showInformation(getSimulationObject());
    }
    
    /**
     *
     * @return get sizeX of image
     */
    public int getSizeX(){
        return sizeX;
    }
    
    /**
     *
     * @return get sizeY of image
     */
    public int getSizeY(){
        return sizeY;
    }

    /**
     *
     * @return returns name of VisibleComponent
     */
    public String getName() {
        return name;
    }
}
