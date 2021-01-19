/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.person;

import com.mycompany.pandemysimulation.utils.Coordinates;
import com.mycompany.pandemysimulation.utils.Utils;
import com.mycompany.pandemysimulation.map.PathFinder;
import com.mycompany.pandemysimulation.ui.VisibleComponent;
import com.mycompany.pandemysimulation.map.Location;
import java.util.Random;

/**
 *
 * @author kacper
 */

public final class ClientFactory {
    
    private final static String[] names = {"Adam", "Kacper"};
    private final static String[] surnames = {"Tusk", "Duda"};
    private final static String[] imageNames = {"bill_clinton.png", "trump-circle.png", "images.jpeg"};
    
    private final static String getRandomPesel(){
        return "00000000000";
    }
    
    public static Client createRandomClient(Location currentLocation, PathFinder pathFinder){
        VisibleComponent vc = new VisibleComponent(Utils.getRandomFromArray(imageNames), 20, 20);
        String name = Utils.getRandomFromArray(names);
        String surname = Utils.getRandomFromArray(names);
        
        return new Client(Coordinates.mapToWorld(currentLocation.getIdX()),Coordinates.mapToWorld(currentLocation.getIdY()), vc, getRandomPesel(), name, surname, 0.5 > new Random().nextDouble(), 0,false, 0.5 >new Random().nextDouble(), null, currentLocation, false, 5, pathFinder);
    }
}
