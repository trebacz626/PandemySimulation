/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

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
    
    public static Client createRandomClient(UIManager uiManager){
        VisibleComponent vc = new VisibleComponent(Utils.getRandomFromArray(imageNames), uiManager, 30, 30);
        String name = Utils.getRandomFromArray(names);
        String surname = Utils.getRandomFromArray(names);
        
        return new Client(0,0, vc, getRandomPesel(), name, surname, false, 0,false, null, null, false, 5);
    }
}
