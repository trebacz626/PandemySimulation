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

    private DynamicGate suppliersGate;
    
    public WholesaleShop(String name, String address, int maxClients, int maxProducts, int idX, int idY, VisibleComponent visibleComponent) {
        super(name, address, maxClients, maxProducts, idX, idY, visibleComponent);
        suppliersGate = new DynamicGate(maxClients);
    }
    
    @Override
    public boolean update(){
        super.update();
        suppliersGate.setNewCapacity(getClientCapacity());
        createProduct();
        return true;
    }
    
    public void addSupplierToQue(Supplier supplier){
    
    }
    
    private void createProduct(){
        Product product = new Product("name", new Date(), Brand.AVON);
        this.addProductSync(product);
    }

    @Override
    public void enter(ThreadAgent threadAgent) throws InterruptedException{
            if(threadAgent instanceof Supplier){
                suppliersGate.enter();
            }
    }

    @Override
    public void leave(ThreadAgent threadAgent) {
            if(threadAgent instanceof Supplier){
                suppliersGate.leave();
            }
    }
    
    
    
    
    
    
}
