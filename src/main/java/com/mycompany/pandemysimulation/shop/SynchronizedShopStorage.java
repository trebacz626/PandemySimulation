/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.shop;

import com.mycompany.pandemysimulation.Product;
import com.mycompany.pandemysimulation.utils.Utils;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author kacper
 */
public class SynchronizedShopStorage {

    private List<Product> products;
    private int curCapacity;
    private int maxCapacity;

    private Lock inspectionLock;
    private Object counterMonitor;

    public SynchronizedShopStorage(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.products = new LinkedList<>();
        this.curCapacity = 0;
        counterMonitor = new Object();
        inspectionLock = new ReentrantLock();
    }

    public void addProduct(Product prooduct) throws InterruptedException {
        synchronized (counterMonitor) {
            while (curCapacity >= maxCapacity) {
                counterMonitor.wait();
            }
        }
        inspectionLock.lock();
        products.add(prooduct);
        inspectionLock.unlock();
        synchronized (counterMonitor) {
            curCapacity++;
            counterMonitor.notify();
        }
    }

    public Product takeRandomProduct() throws InterruptedException {
        synchronized (counterMonitor) {
            while (curCapacity == 0) {
                counterMonitor.wait();
            }
        }
        inspectionLock.lock();
        Product product = Utils.getRandomFromList(products);
        products.remove(product);
        inspectionLock.unlock();
        synchronized (counterMonitor) {
            curCapacity--;
            counterMonitor.notify();
        }
        return product;
    }

    public int getNumberOfProducts() {
        return curCapacity;
    }
    
    public boolean isFull(){
        synchronized(counterMonitor){
            return curCapacity == maxCapacity;
        }
    }

    protected void lockForInspection() {
        inspectionLock.lock();
    }

    protected void unlockAfterInspection() {
        inspectionLock.unlock();
    }

    protected void removeExpiredProduct(Product product) {
        synchronized(counterMonitor){
            products.remove(product);
            curCapacity--;
        }
    }

    protected List<Product> getCopyOfProducts() {
        return new LinkedList<>(products);
    }

}
