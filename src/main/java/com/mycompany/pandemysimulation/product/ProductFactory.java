/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.product;

import com.mycompany.pandemysimulation.core.Simulation;
import com.mycompany.pandemysimulation.core.utils.Utils;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Factory of products
 * @author kacper
 */
public class ProductFactory {

    private static String[] names = {"Kinder Bueno", "Milk", "Bread", "Ketchup", "Vaccine", "Tofu", "Fish", "Chips", "Meat", "Cynamon", "Banana", "Apple", "Porsche", "Macbook"};

    /**
     *
     * Creates random product.
     * 
     * @param simulation
     * @return
     */
    public static Product randomProduct(Simulation simulation) {
        Date date = simulation.getCurrentDate();
        date.setTime(date.getTime() + TimeUnit.MILLISECONDS.convert(new Random().nextInt(30) + 5, TimeUnit.DAYS));
        return new Product(Utils.getRandomFromArray(names), date, Utils.getRandomFromArray(Brand.values()));
    }
}
