/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.person;

import com.mycompany.pandemysimulation.product.Product;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * A simple Storage for products
 * 
 * @author kacper
 */
public class ProductStorage {

    private LinkedList<Product> listOfProducts;
    private int maxCapacity;

    /**
     *
     * @param maxCapacity
     */
    protected ProductStorage(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.listOfProducts = new LinkedList<Product>();
    }

    /**
     *
     * @param product
     */
    public void addProduct(Product product) {
        listOfProducts.add(product);
    }

    /**
     *
     * @return
     */
    public int getCapacity() {
        return maxCapacity;
    }

    /**
     *
     * Returns a free place in Storage.
     * 
     * @return
     */
    public int getFreePlace() {
        return maxCapacity - listOfProducts.size();
    }

    /**
     * Removes n products.
     * @param n
     */
    public void removeNProducts(int n) {
        listOfProducts = new LinkedList<>(listOfProducts.subList(0, listOfProducts.size() - n));
    }

    /**
     *
     * Removes and returns first product in storage.
     * @return
     */
    public Product getAndRemoveProduct() {
        return listOfProducts.removeFirst();
    }

    /**
     *
     * @return
     */
    public boolean isEmpty() {
        return listOfProducts.isEmpty();
    }

    /**
     *
     * @return
     */
    public boolean isFull() {
        return listOfProducts.size() == maxCapacity;
    }
}
