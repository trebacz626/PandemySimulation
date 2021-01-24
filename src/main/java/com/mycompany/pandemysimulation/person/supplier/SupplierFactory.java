/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.person.supplier;

import com.mycompany.pandemysimulation.utils.Coordinates;
import com.mycompany.pandemysimulation.utils.Utils;
import com.mycompany.pandemysimulation.map.PathFinder;
import com.mycompany.pandemysimulation.ui.VisibleComponent;
import com.mycompany.pandemysimulation.map.Location;
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
    
    public static Supplier createRandomSupplier(Location currentLocation, PathFinder pathFinder){
        VisibleComponent vc = new VisibleComponent(Utils.getRandomFromArray(imageNames), 20, 20, "supplierView");
        Company comp = Utils.getRandomFromArray(Company.values());
        CarBrand brand = Utils.getRandomFromArray(CarBrand.values());
        int trunkCapacity = new Random().nextInt(20)+10;
        double gasCapacity = new Random().nextDouble()*500;
        
        
        return new Supplier(brand, trunkCapacity, gasCapacity, comp, false, 0, false, 0.5 >new Random().nextDouble(),null,currentLocation, false, Coordinates.mapToWorld(currentLocation.getIdX()),Coordinates.mapToWorld(currentLocation.getIdY()), vc, pathFinder);
    }
}
