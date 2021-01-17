/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.shop.Shop;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author kacper
 */
public class ShopViewController<T extends Shop> extends SimulationObjectViewController<T>{

    public void update(){
        super.update();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
