/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.person.supplier;

import com.mycompany.pandemysimulation.core.map.Location;
import com.mycompany.pandemysimulation.core.map.PathFinder;
import com.mycompany.pandemysimulation.core.ui.VisibleComponent;
import com.mycompany.pandemysimulation.person.Person;
import com.mycompany.pandemysimulation.person.ProductStorage;
import com.mycompany.pandemysimulation.product.Product;
import com.mycompany.pandemysimulation.shop.Shop;
import com.mycompany.pandemysimulation.shop.retailshop.RetailShop;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Supplier is a Person that travels between Shops on predefined route. 
 * 
 * @author kacper
 */
public class Supplier extends Person {

    private CarBrand carBrand;
    private double gas;
    private double gasCapacity;
    private Company companyName;
    private List<Shop> route;
    private int shopIndex;
    private PathFinder pathFinder;
    private int uniqueId;

    private static int curId = 0;

    private static synchronized int getNextId() {
        return curId++;
    }

    /**
     *
     * Creator
     * 
     * @param carBrand
     * @param trunkCapacity
     * @param gasCapacity
     * @param companyName
     * @param sick
     * @param vaccinated
     * @param wearingMask
     * @param nextStop
     * @param currentLocation
     * @param xPos
     * @param yPos
     * @param visibleComponent
     * @param pathFinder
     */
    protected Supplier(CarBrand carBrand, int trunkCapacity, double gasCapacity, Company companyName, boolean sick, boolean vaccinated, boolean wearingMask, Location currentLocation, double xPos, double yPos, VisibleComponent visibleComponent, PathFinder pathFinder) {
        super(trunkCapacity, sick, vaccinated, wearingMask, currentLocation, xPos, yPos, visibleComponent, pathFinder);
        this.carBrand = carBrand;
        this.gasCapacity = gasCapacity;
        this.companyName = companyName;
        this.uniqueId = getNextId();
        this.route = new LinkedList<>();
    }

    /**
     *
     * On start a supplier creates a random route.
     * 
     * @return
     */
    @Override
    public boolean start() {
        if (!super.start()) {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            route.add(this.getRandomWholesaleShop(null));
        }
        for (int i = 0; i < 5; i++) {
            route.add(this.getRandomRetailShop(null));
        }
        Collections.shuffle(route);
        shopIndex = 0;
        return true;
    }

    /**
     *
     * Returns a next Shop from route.
     * 
     * @return
     */
    @Override
    protected synchronized Shop generateNextGoal() {
        return route.get((shopIndex++) % route.size());

    }

    /**
     *
     * In each Wholesale Shop Supplier takes products till it fills up its truck.
     * When it is in retail shops adds Products to shop till shop is full or supplier is empty.
     * 
     * @param shop
     * @throws InterruptedException
     */
    @Override
    protected void processShop(Shop shop) throws InterruptedException {
        refuel();
        if (shop instanceof RetailShop) {
            if (getProductStorage().isEmpty()) {
                return;
            }
            while (!shop.getWarehouse().isFull() && !getProductStorage().isEmpty()) {
                shop.getWarehouse().addProduct(getProductStorage().getAndRemoveProduct());
            }
        } else {
            while (!getProductStorage().isFull()) {
                Product product = shop.getWarehouse().takeRandomProduct();
                getProductStorage().addProduct(product);
            }
        }
    }

    private void refuel() {
        this.gas = this.gasCapacity;
    }

    /**
     *
     * On move supplier burns gas.
     * 
     */
    protected void onMove() {
        this.gas -= 1;
    }

    /**
     *
     * @return
     */
    public CarBrand getCarBrand() {
        return carBrand;
    }

    /**
     *
     * @return
     */
    public Company getCompanyName() {
        return companyName;
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
     * Returns suppliers route.
     * 
     * @return
     */
    public List<Shop> getRoute() {
        return route;
    }

    /**
     *
     * Sets suppliers route.
     * 
     * @param route
     */
    public synchronized void setRoute(List<Shop> route) {
        this.route = route;
    }

}
