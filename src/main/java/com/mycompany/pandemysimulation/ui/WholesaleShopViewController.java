/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.shop.wholesaleshop.WholesaleShop;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author kacper
 */
public class WholesaleShopViewController<T extends WholesaleShop> extends ShopViewController<T>{
    
    @FXML
    private Button addProduct;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        getTitleTextField().setText("Wholesale Shop");
    }
    
    @Override
    public void start(){
        super.start();
        addProduct.setOnMouseClicked(event->{
            System.out.println("Adding product");
            getSimulationObject().createProduct();
            System.out.println("Product added");
        });   
    }
}
