/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.List;
import java.util.Random;

/**
 *
 * @author kacper
 */
public final class Utils {
    private Utils(){
    }
    private static Random random = new Random();
    
    public static <T> T getRandomFromArray(T[] array){
        return array[random.nextInt(array.length)];
    }
    
    public static <T> T getRandomFromList(List<T> list){
        return list.get(random.nextInt(list.size()));
    }
}
