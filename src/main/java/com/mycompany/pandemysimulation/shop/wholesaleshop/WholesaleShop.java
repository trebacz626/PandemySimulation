/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.shop.wholesaleshop;

import com.mycompany.pandemysimulation.core.ThreadAgent;
import com.mycompany.pandemysimulation.core.ui.VisibleComponent;
import com.mycompany.pandemysimulation.person.supplier.Supplier;
import com.mycompany.pandemysimulation.product.Product;
import com.mycompany.pandemysimulation.product.ProductFactory;
import com.mycompany.pandemysimulation.shop.Shop;
import com.mycompany.pandemysimulation.sync.DynamicGate;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author kacper
 */
public class WholesaleShop extends Shop {

    private LinkedList<Supplier> supplierQue;
    private int idX;
    private int idY;
    private DynamicGate suppliersGate;
    private Date lastProductionDate;
    private int dailyProduction;

    protected WholesaleShop(String name, String address, int maxClients, int maxProducts, int idX, int idY, VisibleComponent visibleComponent) {
        super(name, address, maxClients, maxProducts, idX, idY, visibleComponent);
        suppliersGate = new DynamicGate(maxClients);
        this.dailyProduction = 50;
    }

    @Override
    public boolean start() {
        super.start();
        lastProductionDate = getSimulation().getCurrentDate();
        return true;
    }

    @Override
    public boolean update() {
        super.update();
        suppliersGate.setNewCapacity(getClientCapacity());
        Date currentDate = getSimulation().getCurrentDate();
        long deltaProductionTime = currentDate.getTime() - lastProductionDate.getTime();
        long deltaProductionDays = TimeUnit.DAYS.convert(deltaProductionTime, TimeUnit.MILLISECONDS);
        if (deltaProductionDays >= 1) {
            for (int i = 0; i < this.dailyProduction; i++) {
               createProduct();
            }
            lastProductionDate = currentDate;
        }
        return true;
    }

    public void createProduct() {
        if(this.getWarehouse().isFull()) return;
        Date date = getSimulation().getCurrentDate();
        date.setTime(date.getTime() + TimeUnit.MILLISECONDS.convert(60, TimeUnit.DAYS));
        Product product = ProductFactory.randomProduct(getSimulation());
        this.addProductSync(product);
    }

    @Override
    public void enter(ThreadAgent threadAgent) throws InterruptedException {
        if (threadAgent instanceof Supplier) {
            suppliersGate.enter();
        }
    }

    @Override
    public void leave(ThreadAgent threadAgent) {
        if (threadAgent instanceof Supplier) {
            suppliersGate.leave();
        }
    }

}
