/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import javafx.scene.image.Image;

/**
 *
 * Image Loading class
 * 
 * @author kacper
 */
public class ImageLoader {

    /**
     *
     * Loads an Image
     * 
     * @return
     */
    public static Image loadImage(String imageName, int sizeX, int sizeY) {
        return new Image(ImageLoader.class.getResource("image/" + "stoplight.jpg").toString(), sizeX, sizeY, false, false);
    }
}
