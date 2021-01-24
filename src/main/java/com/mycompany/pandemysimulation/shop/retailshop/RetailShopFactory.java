/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.shop.retailshop;

import com.mycompany.pandemysimulation.App;
import com.mycompany.pandemysimulation.core.ui.VisibleComponent;
import com.mycompany.pandemysimulation.core.utils.Coordinates;
import java.util.Date;
import java.util.Random;
import javafx.scene.image.Image;

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
    
    
    public static RetailShop createRetailShop(String name, String address, int xPos, int yPos, String imageName){
        VisibleComponent vc = new VisibleComponent(new Image(App.class.getResource("image/"+imageName).toString(),Coordinates.mapTileSize,Coordinates.mapTileSize,false,false), Coordinates.mapTileSize, Coordinates.mapTileSize,"shopView");
        int expiredSalePeriod = 7;
        return new RetailShop(expiredSalePeriod,10, new Date(),name, address, getMaxClients(), getMaxProducts(), xPos, yPos, vc);
    }
}
