/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.shop;

import com.mycompany.pandemysimulation.App;
import com.mycompany.pandemysimulation.person.Brand;
import com.mycompany.pandemysimulation.Product;
import com.mycompany.pandemysimulation.person.Supplier;
import com.mycompany.pandemysimulation.ui.VisibleComponent;
import com.mycompany.pandemysimulation.core.ThreadAgent;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
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

    public WholesaleShop(String name, String address, int maxClients, int maxProducts, int idX, int idY, VisibleComponent visibleComponent) {
        super(name, address, maxClients, maxProducts, idX, idY, visibleComponent);
        suppliersGate = new DynamicGate(maxClients);
        this.dailyProduction = 50;
    }

    @Override
    public boolean start() {
        super.start();
        lastProductionDate = App.simulation.getCurrentDate();
        return true;
    }

    @Override
    public boolean update() {
        super.update();
        suppliersGate.setNewCapacity(getClientCapacity());
        Date currentDate = App.simulation.getCurrentDate();
        long deltaProductionTime = currentDate.getTime() - lastProductionDate.getTime();
        long deltaProductionDays = TimeUnit.DAYS.convert(deltaProductionTime, TimeUnit.MILLISECONDS);
        if (deltaProductionDays >= 1) {
            for (int i = 0; i < this.dailyProduction; i++) {
//              for (int i = 0; i < 5; i++) {
               createProduct();
            }
            lastProductionDate = currentDate;
        }
        return true;
    }

    public void addSupplierToQue(Supplier supplier) {

    }

    private void createProduct() {
        if(this.getWarehouse().isFull()) return;
        Date date = App.simulation.getCurrentDate();
        date.setTime(date.getTime() + TimeUnit.MILLISECONDS.convert(60, TimeUnit.DAYS));
        Product product = new Product("name", date, Brand.AVON);
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
