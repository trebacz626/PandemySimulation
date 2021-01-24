/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.shop;

import com.mycompany.pandemysimulation.product.Product;
import com.mycompany.pandemysimulation.utils.Utils;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/**
 *
 * @author kacper
 */
public class SynchronizedStoreStorage {
    private List<Product> products;
    private int maxCapacity;
//    private int freePlace;
    private int occupiedPlace;
    
    
    private boolean isInspected;

    public SynchronizedStoreStorage(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.products = new LinkedList<>();
        isInspected = false;
        occupiedPlace = 0;
//        freePlace = maxCapacity;
    }

    public synchronized void addProduct(Product prooduct) throws InterruptedException {
        while(occupiedPlace >= maxCapacity || isInspected){
            wait();
        }
        products.add(prooduct);
        occupiedPlace++;
        notify();
    }

    public synchronized Product takeRandomProduct() throws InterruptedException {
        while(occupiedPlace == 0 || isInspected){
            wait();
        }
        Product product = Utils.getRandomFromList(products);
        products.remove(product);
        occupiedPlace--;
        notify();
        return product;
    }

    public int getNumberOfProducts() {
        return occupiedPlace;//products.size();
    }
    
    public int getSize(){
        return products.size();
    }
    
    public boolean isFull(){
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

    protected List<Product> getCopyOfProducts() {
        return new LinkedList<>(products);
    }
}
