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

/**
 *
 * @author kacper
 */
public class SynchronizedShopStorage {

    private List<Product> products;
//    private int curCapacity;
    private int maxCapacity;

    private Lock inspectionLock;
    private Semaphore occupiedSemaphore;
    private Semaphore freeSemaphore;

    public SynchronizedShopStorage(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.products = new LinkedList<>();
//        this.curCapacity = 0;
        inspectionLock = new ReentrantLock();
        occupiedSemaphore = new Semaphore(0);
        freeSemaphore = new Semaphore(this.maxCapacity);
    }

    public void addProduct(Product prooduct) throws InterruptedException {
        freeSemaphore.acquire();
        inspectionLock.lock();
        products.add(prooduct);
        inspectionLock.unlock();
        occupiedSemaphore.release();

    }

    public Product takeRandomProduct() throws InterruptedException {

        occupiedSemaphore.acquire();
        inspectionLock.lock();
        Product product = Utils.getRandomFromList(products);
        products.remove(product);
        inspectionLock.unlock();
        freeSemaphore.release();
        return product;
    }

    public int getNumberOfProducts() {
        return products.size();
    }
    
    public boolean isFull(){
        return products.size() == maxCapacity;
    }

    protected void lockForInspection() {
        inspectionLock.lock();
    }

    protected void unlockAfterInspection() {
        inspectionLock.unlock();
    }

    protected void removeExpiredProduct(Product product) throws InterruptedException {
        occupiedSemaphore.acquire();
        products.remove(product);
        freeSemaphore.release();
    }

    protected List<Product> getCopyOfProducts() {
        return new LinkedList<>(products);
    }

}
