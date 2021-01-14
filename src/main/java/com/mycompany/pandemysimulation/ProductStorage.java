/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kacper
 */
public class ProductStorage {
    private LinkedList<Product> listOfProducts;
    private int maxCapacity;

    public ProductStorage(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.listOfProducts = new LinkedList<Product>();
    }
    
    public void addProduct(Product product){
        listOfProducts.add(product);
    }
    
    public int getCapacity(){
        return maxCapacity;
    }
    
    public int getFreePlace(){
        return maxCapacity - listOfProducts.size();
    }
    
    public void removeNProducts(int n){
        listOfProducts = new LinkedList<>(listOfProducts.subList(0, listOfProducts.size()-n));
    }
    
    public Product getAndRemoveProduct(){
        return listOfProducts.removeFirst();
    }
    
    public List<Product> getListOfProducts(){
        return listOfProducts;
    }
    
    public boolean isEmpty(){
        return listOfProducts.isEmpty();
    }
    
    public boolean isFull(){
        return listOfProducts.size() == maxCapacity;
    }
    
    public int getNumberOfProducts(){
        return listOfProducts.size();
    }
}
