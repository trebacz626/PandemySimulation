/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.person.supplier;

import com.mycompany.pandemysimulation.App;
import com.mycompany.pandemysimulation.core.Simulation;
import com.mycompany.pandemysimulation.core.map.Location;
import com.mycompany.pandemysimulation.core.map.PathFinder;
import com.mycompany.pandemysimulation.core.ui.VisibleComponent;
import com.mycompany.pandemysimulation.core.utils.Coordinates;
import com.mycompany.pandemysimulation.core.utils.Utils;
import java.util.Random;
import javafx.scene.image.Image;

/**
 *
 * @author kacper
 */

public final class SupplierFactory {
    
    private final static String[] names = {"Adam", "Kacper"};
    private final static String[] surnames = {"Tusk", "Duda"};
    private final static String[] imageNames = {"car-icon-27.png", "brum.png", "pagani.png"};
    
    private static long pesel = 11111111111L;
    private synchronized static String getRandomPesel(){
        return String.valueOf(pesel++);
    }
    
    public static Supplier createRandomSupplier(Simulation simulation){
        Location currentLocation = simulation.getMapManager().getMap().getSpawnPointRoad();
        PathFinder pathFinder = simulation.getMapManager().getRoadPathFinder();
        String imageName = Utils.getRandomFromArray(imageNames);
        VisibleComponent vc = new VisibleComponent(new Image(App.class.getResource("image/"+imageName).toString(),Coordinates.mapTileSize,Coordinates.mapTileSize,false,false), 20, 20, "supplierView");
        Company comp = Utils.getRandomFromArray(Company.values());
        CarBrand brand = Utils.getRandomFromArray(CarBrand.values());
        int trunkCapacity = new Random().nextInt(20)+10;
        double gasCapacity = new Random().nextDouble()*500;
        
        Random generator = new Random();
        boolean isSick = 0.5 > generator.nextDouble();
        boolean isVaccinated = simulation.getWorldData().getVaccinateChance() > generator.nextDouble();
        boolean isWearingMask = 0.5 > generator.nextDouble();
        
        
        return new Supplier(brand, trunkCapacity, gasCapacity, comp, isSick, isVaccinated, isWearingMask,null,currentLocation, Coordinates.mapToWorld(currentLocation.getIdX()),Coordinates.mapToWorld(currentLocation.getIdY()), vc, pathFinder);
    }
}
