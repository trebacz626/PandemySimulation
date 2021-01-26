/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.product;

import java.util.Date;

/**
 *
 * Product that is sold in Shops
 * 
 * @author kacper
 */
public class Product {

    private int id;
    private String name;
    private Date beforeDate;
    private Brand brand;

    private static int curId = 0;

    private static synchronized int getNextId() {
        return curId++;
    }

    /**
     *
     * @param name name of product
     * @param beforeDate best before date
     * @param brand brand of product
     */
    protected Product(String name, Date beforeDate, Brand brand) {
        this.id = getNextId();
        this.name = name;
        this.beforeDate = beforeDate;
        this.brand = brand;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
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
    public Date getBeforeDate() {
        return beforeDate;
    }

    /**
     *
     * @return
     */
    public Brand getBrand() {
        return brand;
    }

}
