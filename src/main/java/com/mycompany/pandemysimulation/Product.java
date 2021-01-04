/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.Date;

/**
 *
 * @author kacper
 */
public class Product {
    private int id;
    private String name;
    private Date beforeDate;
    private Brand brand; 

    public Product(int id, String name, Date beforeDate, Brand brand) {
        this.id = id;
        this.name = name;
        this.beforeDate = beforeDate;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBeforeDate() {
        return beforeDate;
    }

    public Brand getBrand() {
        return brand;
    }
    
    
    
}
