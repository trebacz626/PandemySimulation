/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author kacper
 */
public class RetailShopFactory {
    public static int getMaxClients(){
        return new Random().nextInt(16)+5;
    }
    
    public static int getMaxProducts(){
        return new Random().nextInt(160)+50;
    }
    
    
    public static RetailShop createRetailShop(UIManager uiManager, String name, String address, int xPos, int yPos, String imageName){
        VisibleComponent vc = new VisibleComponent(imageName, uiManager, 100, 100);
        int expiredSalePeriod = 7;
        return new RetailShop(expiredSalePeriod,10, new Date(),name, address, getMaxClients(), getMaxProducts(), xPos, yPos, vc);
    }
}