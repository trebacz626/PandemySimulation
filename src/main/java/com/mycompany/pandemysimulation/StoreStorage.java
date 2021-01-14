/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author kacper
 */
public class StoreStorage {
    private HashMap<String, List<Product>> products;
    private Set<String> lockedKeys;
    private int maxCapacity;
    private int curCapacity;
    
    private Object producerSynchronizer;
    private Object counterSynchronizer;
    
    
    public StoreStorage(int maxCapacity){
        this.maxCapacity = maxCapacity;
        this.curCapacity = 0;
        this.products = new HashMap<>();
        producerSynchronizer = new Object();
        counterSynchronizer = new Object();
        lockedKeys = new HashSet<>();
    }
    
    public void addProductSync(Product product){
        String productName = product.getName();
                var shelf = getOrCreateShelf(productName);
                shelf.add(product);
    }
    
    public void addProduct(Product product){
        synchronized(producerSynchronizer){
            synchronized(counterSynchronizer){
                try{
                    while(curCapacity >= maxCapacity){
                        counterSynchronizer.wait();
                    }
                }catch(Exception e){
                    System.out.println(e);
                    return;
                }
            }
            try{
                String productName = product.getName();
                lock(productName);
                var shelf = getOrCreateShelf(productName);
                shelf.add(product);
                synchronized(counterSynchronizer){
                    curCapacity++;
                    counterSynchronizer.notifyAll();
                }
            }finally{
                unlock(product.getName());
            }
        }
    }
    
    public Product getAndRemoveProduct(String productName){
        synchronized(counterSynchronizer){
                try{
                    while(curCapacity == 0){
                        counterSynchronizer.wait();
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
            }
            Product returnedProduct;
            try{
                lock(productName);
                var shelf = getOrCreateShelf(productName);
                if(shelf.size() == 0){
                    unlock(productName);
                    return null;
                }
                returnedProduct = shelf.remove(0);
                unlock(productName);
                synchronized(counterSynchronizer){
                    curCapacity--;
                    counterSynchronizer.notifyAll();
                }
                return returnedProduct;
            }catch(Exception e){
                unlock(productName);
                return null;
            }
    }
    
    private List<Product> getOrCreateShelf(String productName){
        var shelf = products.get(productName);
        if(shelf == null){
            shelf = new LinkedList<>();
            products.put(productName, shelf);
        }
        return shelf;
    }
    
    
    private void lock(String key) {
        synchronized (lockedKeys) {
            while (!lockedKeys.add(key)) {
                try {
                    lockedKeys.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void unlock(String key) {
        synchronized (lockedKeys) {
            lockedKeys.remove(key);
            lockedKeys.notifyAll();
        }
    }
    
    public List<String> getProductNames(){
        return new ArrayList(products.keySet());
    }
    
    public List<Product> getListOfProducts(){
        LinkedList<Product> returned = new LinkedList<Product>();
        for(String prodName: products.keySet()){
            lock(prodName);
            returned.addAll(products.get(prodName));
            unlock(prodName);
        }
        return returned;
    }
    
    public boolean isFull(){
        synchronized(counterSynchronizer){
            return curCapacity >= maxCapacity;
        }
    }
    
}

