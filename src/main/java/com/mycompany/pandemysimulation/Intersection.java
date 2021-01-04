/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.LinkedList;

/**
 *
 * @author kacper
 */
public class Intersection<T> extends SimulationObject{
    //TODO consider
    private Location shopA;
    private Location shopB;
    private Location shopC;
    private Location shopD;
    //********************
    private LinkedList<T> waitingQueue;
    
    public void enter(T enteringObject){
        
    }   
    
    public Intersection(double posX, double posY, VisibleComponent visibleComponent){
        super(posX,posY,visibleComponent);
    }
    
}
