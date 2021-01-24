/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.person.client;

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

public final class ClientFactory {
    
    private final static String[] names = {"Adam", "Kacper"};
    private final static String[] surnames = {"Tusk", "Duda"};
    private final static String[] imageNames = {"bill_clinton.png", "trump-circle.png", "images.jpeg"};
    
    
    public static Client createRandomClient(Simulation simulation){
        Location currentLocation = simulation.getMapManager().getMap().getSpawnPointPedestrian();
        PathFinder pathFinder = simulation.getMapManager().getPedestrianPathFinder();
        String imageName = Utils.getRandomFromArray(imageNames);
        VisibleComponent vc = new VisibleComponent(new Image(App.class.getResource("image/"+imageName).toString(),Coordinates.mapTileSize,Coordinates.mapTileSize,false,false), 20, 20, "clientView");
        String name = Utils.getRandomFromArray(names);
        String surname = Utils.getRandomFromArray(names);
        Random generator = new Random();
        boolean isSick = 0.5 > generator.nextDouble();
        boolean isVaccinated = simulation.getWorldData().getVaccinateChance() > generator.nextDouble();
        boolean isWearingMask = 0.5 > generator.nextDouble();
        
        
        return new Client(Coordinates.mapToWorld(currentLocation.getIdX()),Coordinates.mapToWorld(currentLocation.getIdY()), vc, name, surname, isSick,isVaccinated, isWearingMask, null, currentLocation, 5, pathFinder);
    }
}
