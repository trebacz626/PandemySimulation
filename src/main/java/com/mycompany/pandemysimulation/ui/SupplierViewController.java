/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.App;
import com.mycompany.pandemysimulation.person.Person;
import com.mycompany.pandemysimulation.shop.Shop;
import com.mycompany.pandemysimulation.person.Supplier;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author kacper
 */
public class SupplierViewController<T extends Supplier> extends PersonViewController<T>{
    @FXML
    private Text carBrand;

    @FXML
    private Text company;

    @FXML
    private Text uniqueId;
    
    @FXML
    private Button routeButton;

    @FXML
    private TextField route;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        title.setText("Supplier");
    }
    
    public void start(){
        List<String> shopIds = getSimulationObject().getRoute().stream().map(shop->String.valueOf(shop.getUniqueId())).collect(Collectors.toList());
        route.setText(String.join(",", shopIds));
    }
    
    public void update(){
        super.update();
        if(getSimulationObject() != null){
            carBrand.setText(getSimulationObject().getCarBrand().name());
            company.setText(getSimulationObject().getCompanyName().name());
            uniqueId.setText(String.valueOf(getSimulationObject().getUniqueId()));
            routeButton.setOnMouseClicked(event -> {
                List<Shop> backup = getSimulationObject().getRoute();
                try{
                    String text = route.getText();
                    String[] splitted = text.split(",");
                    List<Integer> shopIds = Arrays.stream(splitted).map(el->Integer.parseInt(el)).collect(Collectors.toList());
                    List<Shop> result = new LinkedList<>();
                    for(Integer shopId: shopIds){
                        Shop shop = App.simulation.getShopById(shopId);
                        if(shop == null)
                            return;
                        result.add(shop);
                    }
                    if(result.size() == 0)
                        return;
                    getSimulationObject().setRoute(result);
                }catch(Exception e){
                    getSimulationObject().setRoute(backup);
                }
            });
        }
    }
    
}
