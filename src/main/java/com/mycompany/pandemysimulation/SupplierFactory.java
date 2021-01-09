/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author kacper
 */

public final class SupplierFactory {
    
    private final static String[] names = {"Adam", "Kacper"};
    private final static String[] surnames = {"Tusk", "Duda"};
    private final static String[] imageNames = {"car-icon-27.png", "brum.png", "pagani.png"};
    
    private final static String getRandomPesel(){
        return "00000000000";
    }
    
    public static Supplier createRandomSupplier(){
        VisibleComponent vc = new VisibleComponent(Utils.getRandomFromArray(imageNames), 30, 30);
        Company comp = Utils.getRandomFromArray(Company.values());
        CarBrand brand = Utils.getRandomFromArray(CarBrand.values());
        LinkedList<Location> listOfStops = new LinkedList<Location>();
        int trunkCapacity = new Random().nextInt(300)+100;
        double gasCapacity = new Random().nextDouble()*500;
        
        
        return new Supplier(brand, trunkCapacity, gasCapacity, comp, listOfStops, false, 0, false,null,null, false, 0, 0, vc);
    }
}
