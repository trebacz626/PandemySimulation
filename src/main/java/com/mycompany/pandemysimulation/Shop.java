/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.LinkedList;

/**
 *
 * @author kacper
 */
public abstract class Shop extends MainLoopAgent{
    private String name;
    private String address;
    private int maxClients;
    private StoreStorage warehouse;

    public Shop(String name, String address, int maxClients, int maxProducts, double xPos, double yPos, VisibleComponent visibleComponent) {
        super(xPos, yPos, visibleComponent);
        this.name = name;
        this.address = address;
        this.maxClients = maxClients;
        this.warehouse = new StoreStorage(maxProducts);
    }

    @Override
    public void start() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}
