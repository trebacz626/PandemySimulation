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
    private ProductStorage warehouse;

    public Shop(String name, String address, int maxClients, int maxProducts, double xPos, double yPos, VisibleComponent visibleComponent) {
        super(xPos, yPos, visibleComponent);
        this.name = name;
        this.address = address;
        this.maxClients = maxClients;
        this.warehouse = new ProductStorage(maxProducts);
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
    
    @Override
    public String toString() {
        return getName()+ " " + getxPos() + " "+ getyPos();
    }
}
