/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.shop.wholesaleshop;

import com.mycompany.pandemysimulation.App;
import com.mycompany.pandemysimulation.core.ui.VisibleComponent;
import com.mycompany.pandemysimulation.core.utils.Coordinates;
import java.util.Random;
import javafx.scene.image.Image;

/**
 *
 * @author kacper
 */
public final class WholesaleShopFactory {
    public static int getMaxClients(){
        return new Random().nextInt(16)+5;
    }
    
    public static int getMaxProducts(){
        return new Random().nextInt(160)+50;
    }
    
    
    public static WholesaleShop createWholesaleShop(String name, String address, int xPos, int yPos, String imageName){
        VisibleComponent vc = new VisibleComponent(new Image(App.class.getResource("image/"+imageName).toString(),Coordinates.mapTileSize,Coordinates.mapTileSize,false,false), Coordinates.mapTileSize, Coordinates.mapTileSize, "wholesaleShopView");
        return new WholesaleShop(name, address, getMaxClients(), getMaxProducts(), xPos, yPos, vc);
    }
}
