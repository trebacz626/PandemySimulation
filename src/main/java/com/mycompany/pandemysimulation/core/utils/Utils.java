/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * 
 * Utilities for project
 *
 * @author kacper
 */
public final class Utils {
    private Utils(){
    }
    private static Random random = new Random();
    
    /**
     *
     * Get random element from array of type T
     * 
     * @param <T>   type of element in array 
     * @param array
     * @return      random element from array
     */
    public static <T> T getRandomFromArray(T[] array){
        return array[random.nextInt(array.length)];
    }
    
    /**
     *
     * @param <T> type of element in list 
     * @param list
     * @return      random element from list
     */
    public static <T> T getRandomFromList(List<T> list){
        if(list.isEmpty())return null;
        return list.get(random.nextInt(list.size()));
    }
    
    /**
     *
     * @param <T> type of element key in  hash map
     * @param <H> type of element value in hash map
     * @param map
     * @return  random key of element from hashmap
     */
    public static <T,H> T getRandomFromHashMap(HashMap<T, H> map){
        if(map.isEmpty())return null;
        List<T> keys = new ArrayList<>(map.keySet());
        return keys.get(random.nextInt(keys.size()));
    }
    
    /**
     *  Checks if any element of array is True
     * @param array
     * @return
     */
    public static boolean isAnyTrue(boolean[] array){
        for(boolean b : array) if(!b) return false;
        return true;
    }
    
    /**
     *
     * Check if all elements in array are False
     * @param array
     * @return
     */
    public static boolean areAllFalse(boolean[] array){
        for(boolean b : array) if(b) return false;
        return true;
    }
}
