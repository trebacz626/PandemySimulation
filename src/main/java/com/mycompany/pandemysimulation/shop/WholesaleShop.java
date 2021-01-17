/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.shop;

import com.mycompany.pandemysimulation.person.Brand;
import com.mycompany.pandemysimulation.Product;
import com.mycompany.pandemysimulation.person.Supplier;
import com.mycompany.pandemysimulation.ui.VisibleComponent;
import com.mycompany.pandemysimulation.core.ThreadAgent;
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
    
    @Override
    public boolean update(){
        createProduct();
        return true;
    }
    
    public void addSupplierToQue(Supplier supplier){
    
    }
    
    private void createProduct(){
        Product product = new Product("name", new Date(), Brand.AVON);
        if(!this.getWarehouse().isFull()){
//            System.out.println("creating");
            this.getWarehouse().addProduct(product);
        }
    }

    @Override
    public void enter(ThreadAgent threadAgent) throws InterruptedException{
            if(threadAgent instanceof Supplier){
                suppliersGate.acquire();
            }
    }

    @Override
    public void leave(ThreadAgent threadAgent) {
            if(threadAgent instanceof Supplier){
                suppliersGate.release();
            }
    }
    
    
    
    
    
    
}
