/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author kacper
 */
public class WholesaleShop extends Shop{
    private LinkedList<Supplier> supplierQue;

    public WholesaleShop(String name, String address, int maxClients, int maxProducts, double xPos, double yPos, VisibleComponent visibleComponent) {
        super(name, address, maxClients, maxProducts, xPos, yPos, visibleComponent);
    }
    
    
    public void start(){
    
    }
    
    @Override
    public void update(){
        createProduct();
    }
    
    public void addSupplierToQue(Supplier supplier){
    
    }
    
    private void createProduct(){
        Product product = new Product("name", new Date(), Brand.AVON);
        if(!this.getWarehouse().isFull()){
            this.getWarehouse().addProduct(product);
        }
    }
    
    
    
    
    
    
}
