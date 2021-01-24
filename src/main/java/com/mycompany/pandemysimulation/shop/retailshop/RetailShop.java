/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.shop.retailshop;

import com.mycompany.pandemysimulation.core.ThreadAgent;
import com.mycompany.pandemysimulation.core.map.Location;
import com.mycompany.pandemysimulation.core.ui.VisibleComponent;
import com.mycompany.pandemysimulation.person.client.Client;
import com.mycompany.pandemysimulation.person.supplier.Supplier;
import com.mycompany.pandemysimulation.shop.Shop;
import com.mycompany.pandemysimulation.sync.DynamicGate;
import java.util.Date;

/**
 *
 * @author kacper
 */
public class RetailShop extends Shop implements Location {

    private int supplierCapacity;
    private int expiredSalePeriod;
    private Date lastExpiredDate;
    
    private DynamicGate clientGate;
    private DynamicGate supplierGate;
    
    protected RetailShop(int supplierCapacity, int expiredSalePeriod, Date lastExpiredDate, String name, String address, int maxClients, int maxProducts, int idX, int idY, VisibleComponent visibleComponent) {
        super(name, address, maxClients, maxProducts, idX, idY, visibleComponent);
        this.supplierCapacity = supplierCapacity;
        this.expiredSalePeriod = expiredSalePeriod;
        this.lastExpiredDate = lastExpiredDate;
        clientGate = new DynamicGate(maxClients);
        supplierGate = new DynamicGate(supplierCapacity);
    }
    
    @Override
    public void enter(ThreadAgent threadAgent) throws InterruptedException {
        if (threadAgent instanceof Client) {
            clientGate.enter();
        } else if(threadAgent instanceof Supplier){
            supplierGate.enter();
        }
        super.enter(threadAgent);
    }
    
    @Override
    public void leave(ThreadAgent threadAgent) {
        super.leave(threadAgent);
        if (threadAgent instanceof Client) {
            clientGate.leave();
        } else if(threadAgent instanceof Supplier){
            supplierGate.leave();
        }
    }
    
    @Override
    public boolean update(){
        super.update();
        clientGate.setNewCapacity(getClientCapacity());
        return true;
    }
}
