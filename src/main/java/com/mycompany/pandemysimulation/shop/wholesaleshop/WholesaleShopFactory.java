/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.shop.wholesaleshop;

import com.mycompany.pandemysimulation.COVIDSimulation;
import com.mycompany.pandemysimulation.core.ui.VisibleComponent;
import com.mycompany.pandemysimulation.core.utils.Coordinates;
import java.util.Random;
import javafx.scene.image.Image;

/**
 *
 * @author kacper
 */
public final class WholesaleShopFactory {

    /**
     *
     * @return
     */
    public static int getMaxClients(){
        return new Random().nextInt(16)+5;
    }
    
    /**
     *
     * @return
     */
    public static int getMaxProducts(){
        return new Random().nextInt(160)+50;
    }
    
    /**
     *
     * @param name
     * @param address
     * @param xPos
     * @param yPos
     * @param imageName
     * @return
     */
    public static WholesaleShop createWholesaleShop(String name, String address, int xPos, int yPos, String imageName){
        VisibleComponent vc = new VisibleComponent(new Image(COVIDSimulation.class.getResource("image/"+imageName).toString(),Coordinates.mapUnitSize,Coordinates.mapUnitSize,false,false), Coordinates.mapUnitSize, Coordinates.mapUnitSize, "wholesaleShopView");
        return new WholesaleShop(name, address, getMaxClients(), getMaxProducts(), xPos, yPos, vc);
    }
}
