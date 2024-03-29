/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.shop.retailshop;

import com.mycompany.pandemysimulation.core.ui.VisibleComponent;
import com.mycompany.pandemysimulation.core.utils.Coordinates;
import com.mycompany.pandemysimulation.ui.UIManager;
import java.util.Date;
import java.util.Random;

/**
 *
 * Factory of Retail Shops
 * 
 * @author kacper
 */
public class RetailShopFactory {

    private static int getMaxClients(){
        return new Random().nextInt(16)+5;
    }

    private static int getMaxProducts(){
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
    public static RetailShop createRetailShop(String name, String address, int xPos, int yPos, String imageName){
        VisibleComponent vc = new VisibleComponent(UIManager.loadImage(imageName, Coordinates.mapUnitSize, Coordinates.mapUnitSize), Coordinates.mapUnitSize, Coordinates.mapUnitSize,"shopView");
        int expiredSalePeriod = 7;
        return new RetailShop(expiredSalePeriod,10, new Date(),name, address, getMaxClients(), getMaxProducts(), xPos, yPos, vc);
    }
}
