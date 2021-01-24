/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.shop;

import com.mycompany.pandemysimulation.core.MainLoopAgent;
import com.mycompany.pandemysimulation.core.ThreadAgent;
import com.mycompany.pandemysimulation.core.map.Location;
import com.mycompany.pandemysimulation.core.ui.VisibleComponent;
import com.mycompany.pandemysimulation.core.utils.Coordinates;
import com.mycompany.pandemysimulation.person.Person;
import com.mycompany.pandemysimulation.product.Product;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author kacper
 */
public abstract class Shop extends MainLoopAgent implements Location {

    private String name;
    private String address;
    private int maxClients;
    private SynchronizedStoreStorage warehouse;
    private int idX;
    private int idY;
    private int uniqueId;
    private List<Person> peopleInside;
    private Executor worker;
    private Date lastCheckDate;
    private int checkInterval;

    private static int curId = 0;

    private static synchronized int getNextId() {
        return curId++;
    }

    protected Shop(String name, String address, int maxClients, int maxProducts, int idX, int idY, VisibleComponent visibleComponent) {
        super(Coordinates.mapToWorld(idX), Coordinates.mapToWorld(idY), visibleComponent);
        this.name = name;
        this.address = address;
        this.maxClients = maxClients;
        this.warehouse = new SynchronizedStoreStorage(maxProducts);
        this.idX = idX;
        this.idY = idY;
        this.uniqueId = getNextId();
        this.peopleInside = new LinkedList<>();
        this.worker = Executors.newSingleThreadExecutor((Runnable r) -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
        this.checkInterval = 7;
    }

    @Override
    public synchronized void enter(ThreadAgent threadAgent) throws InterruptedException {
        this.peopleInside.add((Person) threadAgent);
    }

    @Override
    public synchronized void leave(ThreadAgent threadAgent1) {
        this.peopleInside.remove((Person) threadAgent1);
    }

    public boolean start() {
        this.lastCheckDate = getSimulation().getCurrentDate();
        return true;
    }

    public boolean update() {
        Date currentDate = getSimulation().getCurrentDate();
        long deltaCheckTime = currentDate.getTime() - lastCheckDate.getTime();
        long deltaCheckDays = TimeUnit.DAYS.convert(deltaCheckTime, TimeUnit.MILLISECONDS);
        if (true || deltaCheckDays > this.checkInterval) {
            worker.execute(() -> {
                productCheck();
            });
            lastCheckDate = currentDate;
        }
        return true;
    }

    protected void addProductSync(Product product) {
        worker.execute(() -> {
            try {
                this.warehouse.addProduct(product);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void productCheck() {
        Date curDate = getSimulation().getCurrentDate();
        warehouse.lockForInspection();
        List<Product> products = warehouse.getCopyOfProducts();
        for (Product product : products) {

            if (curDate.getTime() > product.getBeforeDate().getTime()) {
                try {
                    warehouse.removeExpiredProduct(product);
                } catch (Exception e) {
                }
            } else if (product.getBeforeDate().getTime() - TimeUnit.MILLISECONDS.convert(this.checkInterval, TimeUnit.DAYS) < curDate.getTime()) {
                //TODO decrease price
            }
        }
        warehouse.unlockAfterInspection();
    }

    public synchronized List<Person> getCopyOfPeopleInside() {
        return new LinkedList<>(peopleInside);
    }

    public SynchronizedStoreStorage getWarehouse() {
        return warehouse;
    }

    @Override
    public int getIdX() {
        return idX;
    }

    @Override
    public int getIdY() {
        return idY;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    protected int getClientCapacity() {
        return getSimulation().getWorldData().isLockdown() ? (int) (0.25 * maxClients) : maxClients;
    }

    @Override
    public List<Location> getGroup() {
        return Collections.singletonList(this);
    }

    @Override
    public boolean shouldGoThrough() {
        return false;
    }
}
