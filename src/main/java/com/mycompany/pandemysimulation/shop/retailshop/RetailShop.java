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
 * A Retail shop for normal people.
 * 
 * @author kacper
 */
public class RetailShop extends Shop implements Location {

    private int supplierCapacity;
    private int expiredSalePeriod;
    private Date lastExpiredDate;
    
    private DynamicGate clientGate;
    private DynamicGate supplierGate;
    
    /**
     *
     * @param supplierCapacity
     * @param expiredSalePeriod
     * @param lastExpiredDate
     * @param name
     * @param address
     * @param maxClients
     * @param maxProducts
     * @param coordX
     * @param coordY
     * @param visibleComponent
     */
    protected RetailShop(int supplierCapacity, int expiredSalePeriod, Date lastExpiredDate, String name, String address, int maxClients, int maxProducts, int coordX, int coordY, VisibleComponent visibleComponent) {
        super(name, address, maxClients, maxProducts, coordX, coordY, visibleComponent);
        this.supplierCapacity = supplierCapacity;
        this.expiredSalePeriod = expiredSalePeriod;
        this.lastExpiredDate = lastExpiredDate;
        clientGate = new DynamicGate(maxClients);
        supplierGate = new DynamicGate(supplierCapacity);
    }
    
    /**
     *
     * Method to call to enter a shop
     * 
     * @param threadAgent
     * @throws InterruptedException
     */
    @Override
    public void enter(ThreadAgent threadAgent) throws InterruptedException {
        if (threadAgent instanceof Client) {
            clientGate.enter();
        } else if(threadAgent instanceof Supplier){
            supplierGate.enter();
        }
        super.enter(threadAgent);
    }
    
    /**
     *
     * Method to call when leaving a shop.
     * 
     * @param threadAgent
     */
    @Override
    public void leave(ThreadAgent threadAgent) {
        super.leave(threadAgent);
        if (threadAgent instanceof Client) {
            clientGate.leave();
        } else if(threadAgent instanceof Supplier){
            supplierGate.leave();
        }
    }
    
    /**
     *
     * Every turn Retail Shops actualizes its gates capacity.
     * It also calls an update of parent class.
     * 
     * @return
     */
    @Override
    public boolean update(){
        super.update();
        clientGate.setNewCapacity(getClientCapacity());
        return true;
    }
}
