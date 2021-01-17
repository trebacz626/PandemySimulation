/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import com.mycompany.pandemysimulation.core.MainLoopAgent;
import com.mycompany.pandemysimulation.map.Location;
import com.mycompany.pandemysimulation.core.ThreadAgent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kacper
 */
public abstract class Shop extends MainLoopAgent implements Location{
    private String name;
    private String address;
    private int maxClients;
    private StoreStorage warehouse;
    private int idX;
    private int idY;

    public Shop(String name, String address, int maxClients, int maxProducts, int idX, int idY, VisibleComponent visibleComponent) {
        super(Coordinates.mapToWorld(idX), Coordinates.mapToWorld(idY), visibleComponent);
        this.name = name;
        this.address = address;
        this.maxClients = maxClients;
        this.warehouse = new StoreStorage(maxProducts);
        this.idX = idX;
        this.idY = idY;
    }
    
    public boolean start(){
        return true;
    }
    
    public boolean update(){
        return true;
    }

    @Override
    public int getIdX() {
        return idX;
    }

    @Override
    public int getIdY() {
        return idY;
    } 

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
    
    public LinkedList<Product> getProducts(){
        return null;
    }
    
    public void lockdown(){
    
    }
    
    public void unlock(){
    
    }
    
    protected StoreStorage getWarehouse(){
        return this.warehouse;
    }
    
    @Override
    public String toString() {
        String text =  getName()+ " " + getxPos() + " "+ getyPos()+"\n Products: "; 
        for( Product product : warehouse.getListOfProducts()){
            text+=product.getName()+"\n";
        }
        return text;
    }
    
    @Override
    public List<Location> getGroup() {
        return Collections.singletonList(this);
    }
}
