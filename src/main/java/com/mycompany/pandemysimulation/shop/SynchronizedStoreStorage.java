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
 * @author kacper
 */
public class SynchronizedStoreStorage {

    private HashMap<Product, Double> products;
    private int maxCapacity;
//    private int freePlace;
    private int occupiedPlace;

    private boolean isInspected;

    public SynchronizedStoreStorage(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.products = new HashMap<>();
        isInspected = false;
        occupiedPlace = 0;
//        freePlace = maxCapacity;
    }

    public synchronized void addProduct(Product prooduct) throws InterruptedException {
        while (occupiedPlace >= maxCapacity || isInspected) {
            wait();
        }
        products.put(prooduct, new Random().nextDouble() * 100 + 1);
        occupiedPlace++;
        notify();
    }

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

    public int getSize() {
        return products.size();
    }

    public boolean isFull() {
        return products.size() == maxCapacity;
    }

    protected synchronized void lockForInspection() {
        isInspected = true;
    }

    protected synchronized void unlockAfterInspection() {
        isInspected = false;
        notifyAll();
    }

    protected void removeExpiredProduct(Product product) throws InterruptedException {
        products.remove(product);
        occupiedPlace--;
    }

    public synchronized List<Product> getCopyOfProducts() {
        return new LinkedList<>(products.keySet());
    }
}
