/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.Random;

/**
 *
 * @author kacper
 */
public final class WholesaleFactory {
    public static int getMaxClients(){
        return new Random().nextInt(16)+5;
    }
    
    public static int getMaxProducts(){
        return new Random().nextInt(160)+50;
    }
    
    
    public static WholesaleShop createWholesaleShop(String name, String address, int xPos, int yPos, String imageName){
        VisibleComponent vc = new VisibleComponent(imageName, 200, 200);
        return new WholesaleShop(name, address, getMaxClients(), getMaxProducts(), xPos, yPos, vc);
    }
}
