/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.shop;

import com.mycompany.pandemysimulation.core.utils.Utils;
import com.mycompany.pandemysimulation.product.Product;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * A synchronized Store for Shop. Stores products along with its prices.
 * 
 * @author kacper
 */
public class SynchronizedStoreStorage {

    private HashMap<Product, Double> products;
    private int maxCapacity;
    private int occupiedPlace;

    private boolean isInspected;

    /**
     *
     * @param maxCapacity
     */
    public SynchronizedStoreStorage(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.products = new HashMap<>();
        isInspected = false;
        occupiedPlace = 0;
    }

    /**
     *
     * Adds a product and sets a price for it.
     * 
     * @param prooduct
     * @throws InterruptedException
     */
    public synchronized void addProduct(Product prooduct) throws InterruptedException {
        while (occupiedPlace >= maxCapacity || isInspected) {
            wait();
        }
        products.put(prooduct, new Random().nextDouble() * 100 + 1);
        occupiedPlace++;
        notify();
    }

    /**
     *
     * Removes and returns and random product.
     * 
     * @return
     * @throws InterruptedException
     */
    public synchronized Product takeRandomProduct() throws InterruptedException {
        while (occupiedPlace == 0 || isInspected) {
            wait();
        }
        Product product = Utils.getRandomFromHashMap(products);
        products.remove(product);
        occupiedPlace--;
        notify();
        return product;
    }

    /**
     *
     * @return
     */
    public int getSize() {
        return products.size();
    }

    /**
     *
     * @return
     */
    public boolean isFull() {
        return products.size() == maxCapacity;
    }

    /**
     *
     * Locks Storage for inspection. It means that nobody else can use the shop storage.
     * 
     */
    protected synchronized void lockForInspection() {
        isInspected = true;
    }

    /**
     *
     * Unlocks Storage after inspection.
     * 
     */
    protected synchronized void unlockAfterInspection() {
        isInspected = false;
        notifyAll();
    }

    /**
     *
     * Removes a given product.
     * 
     * @param product
     * @throws InterruptedException
     */
    protected void removeProduct(Product product) throws InterruptedException {
        products.remove(product);
        occupiedPlace--;
    }
    /**
     * 
     * @param product
     * @return 
     */
    protected double getPrice(Product product){
        return products.get(product);
    }
    
    /**
     * 
     * @param product
     * @param price 
     */
    protected void updatePrice(Product product, double price){
        products.replace(product, price);
    }

    /**
     *
     * Returns a copy of products
     * 
     * @return
     */
    public synchronized List<Product> getCopyOfProducts() {
        return new LinkedList<>(products.keySet());
    }
}
