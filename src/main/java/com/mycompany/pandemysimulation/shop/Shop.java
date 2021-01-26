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
 * Shop is a MainLoppAgent that stores and sells products.
 * 
 * @author kacper
 */
public abstract class Shop extends MainLoopAgent implements Location {

    private String name;
    private String address;
    private int maxClients;
    private SynchronizedStoreStorage warehouse;
    private int coordX;
    private int coordY;
    private int uniqueId;
    private List<Person> peopleInside;
    private Executor worker;
    private Date lastCheckDate;
    private int checkInterval;

    private static int curId = 0;

    private static synchronized int getNextId() {
        return curId++;
    }

    /**
     *
     * @param name
     * @param address
     * @param maxClients
     * @param maxProducts
     * @param coordX
     * @param coordY
     * @param visibleComponent
     */
    protected Shop(String name, String address, int maxClients, int maxProducts, int coordX, int coordY, VisibleComponent visibleComponent) {
        super(Coordinates.mapToWorld(coordX), Coordinates.mapToWorld(coordY), visibleComponent);
        this.name = name;
        this.address = address;
        this.maxClients = maxClients;
        this.warehouse = new SynchronizedStoreStorage(maxProducts);
        this.coordX = coordX;
        this.coordY = coordY;
        this.uniqueId = getNextId();
        this.peopleInside = new LinkedList<>();
        this.worker = Executors.newSingleThreadExecutor((Runnable r) -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
        this.checkInterval = 7;
    }

    /**
     * Method to call to enter a shop
     * 
     * @param threadAgent
     * @throws InterruptedException
     */
    @Override
    public synchronized void enter(ThreadAgent threadAgent) throws InterruptedException {
        this.peopleInside.add((Person) threadAgent);
    }

    /**
     *Method to call to leave a shop
     * 
     * @param threadAgent1
     */
    @Override
    public synchronized void leave(ThreadAgent threadAgent1) {
        this.peopleInside.remove((Person) threadAgent1);
    }

    /**
     * Start setuo method
     * @return
     */
    @Override
    protected boolean start() {
        this.lastCheckDate = getSimulation().getCurrentDate();
        return true;
    }

    /**
     *
     * Update method to that checks for expired products.
     * 
     * @return
     */
    @Override
    protected boolean update() {
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

    /**
     *
     * Add product without blocking a thread.
     * 
     * @param product
     */
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
                    warehouse.removeProduct(product);
                } catch (Exception e) {
                }
            } else if (product.getBeforeDate().getTime() - TimeUnit.MILLISECONDS.convert(this.checkInterval, TimeUnit.DAYS) < curDate.getTime()) {
                double currentPrice = warehouse.getPrice(product);
                warehouse.updatePrice(product, currentPrice/2);
            }
        }
        warehouse.unlockAfterInspection();
    }

    /**
     *
     * Returns a copy of people inside.
     * @return
     */
    public synchronized List<Person> getCopyOfPeopleInside() {
        return new LinkedList<>(peopleInside);
    }

    /**
     *
     * @return
     */
    public SynchronizedStoreStorage getWarehouse() {
        return warehouse;
    }

    /**
     *
     * @return
     */
    @Override
    public int getCoordX() {
        return coordX;
    }

    /**
     *
     * @return
     */
    @Override
    public int getCoordY() {
        return coordY;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @return
     */
    public int getUniqueId() {
        return uniqueId;
    }

    /**
     *
     * @return
     */
    protected int getClientCapacity() {
        return getSimulation().getWorldData().isLockdown() ? (int) (0.25 * maxClients) : maxClients;
    }

    /**
     *
     * Returns a Group of Locations, here just object itself.
     * 
     * @return
     */
    @Override
    public List<Location> getGroup() {
        return Collections.singletonList(this);
    }

    /**
     *
     * Tells if agent can go through, here is cannot.
     * 
     * @return
     */
    @Override
    public boolean shouldGoThrough() {
        return false;
    }
}
