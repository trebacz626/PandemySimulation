/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.shop.Shop;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 *
 * @author kacper
 */
public class ShopViewController<T extends Shop> extends SimulationObjectViewController<T>{

    @FXML
    private Text address;

    @FXML
    private Text name;

    @FXML
    private Text products;
    
    @FXML
    private Text shopId;
    
    public void update(){
        super.update();
        shopId.setText(String.valueOf(getSimulationObject().getUniqueId()));
        address.setText(getSimulationObject().getAddress());
        name.setText(getSimulationObject().getName());
        List<String> productNames = getSimulationObject().getWarehouse().getCopyOfProducts().stream().map(prod->prod.getName()).collect(Collectors.toList());
        products.setText(String.join("\n", productNames));
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        getTitleTextField().setText("Shop");
    }
    
}
