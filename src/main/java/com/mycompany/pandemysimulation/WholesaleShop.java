/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author kacper
 */
public class WholesaleShop extends Shop{
    private LinkedList<Supplier> supplierQue;
    private int idX;
    private int idY;

    private Semaphore suppliersGate;
    
    public WholesaleShop(String name, String address, int maxClients, int maxProducts, int idX, int idY, VisibleComponent visibleComponent) {
        super(name, address, maxClients, maxProducts, idX, idY, visibleComponent);
        suppliersGate = new Semaphore(maxClients);
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

    @Override
    public void enter(ThreadAgent threadAgent) {
        try{
            if(threadAgent instanceof Supplier){
                suppliersGate.acquire();
            }
        }catch(Exception e){
            System.err.println(e);
        }
    }

    @Override
    public void leave(ThreadAgent threadAgent) {
        try{
            if(threadAgent instanceof Supplier){
                suppliersGate.release();
            }
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    
    
    
    
    
}
