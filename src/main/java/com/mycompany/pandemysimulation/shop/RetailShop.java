/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.shop;

import com.mycompany.pandemysimulation.person.Client;
import com.mycompany.pandemysimulation.person.Supplier;
import com.mycompany.pandemysimulation.ui.VisibleComponent;
import com.mycompany.pandemysimulation.map.Location;
import com.mycompany.pandemysimulation.core.ThreadAgent;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

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
    
    public RetailShop(int supplierCapacity, int expiredSalePeriod, Date lastExpiredDate, String name, String address, int maxClients, int maxProducts, int idX, int idY, VisibleComponent visibleComponent) {
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
        } else {
            supplierGate.enter();
        }
        super.enter(threadAgent);
    }
    
    @Override
    public void leave(ThreadAgent threadAgent) {
        super.leave(threadAgent);
        if (threadAgent instanceof Client) {
            clientGate.leave();
        } else {
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
