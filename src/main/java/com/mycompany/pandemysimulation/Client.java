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
public class Client extends Person{
    private String pesel;
    private String firstName;
    private String lastName;
    private ProductStorage cart;

    public Client( double xPos, double yPos, VisibleComponent visibleComponent,String pesel, String firstName, String lastName, boolean sick, int shopsVisitedWhileSick, boolean vaccinated, Location nextStop, Location currentLocation, boolean waiting, int maxCartCapacity, PathFinder pathFinder) {
        super(sick, shopsVisitedWhileSick, vaccinated, nextStop, currentLocation, waiting, xPos, yPos, visibleComponent, pathFinder);
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cart = new ProductStorage(maxCartCapacity);
    }

    
    
    
    private void consume(int n){
        cart.removeNProducts(n);
    }
    
    @Override
    protected Location generateNextGoal() {
        return App.simulation.getRandomShop((Shop)this.getCurrentLocation());
        
    }

//    @Override
//    protected void start() {
//        targetX = (new Random()).nextDouble()*300;
//        targetY = (new Random()).nextDouble()*300;
//        lastTime = System.currentTimeMillis();
//        
//    }
//
//    @Override
//    protected void update() {
//        long curTime = System.currentTimeMillis();
//        double deltaTimeInSec = ((double)curTime - lastTime)/1000;
//        double speed = 50;
//        double deltaX = speed*deltaTimeInSec;
//        this.xPos += this.xPos < targetX ? deltaX : -deltaX;
//        double deltaY = speed*deltaTimeInSec;
//        this.yPos += this.yPos < targetY ? deltaY : -deltaY;
//        if( Math.abs(xPos - targetX) < 5 && Math.abs(yPos - targetY) <5){
//            targetX = (new Random()).nextDouble()*300;
//            targetY = (new Random()).nextDouble()*300;
//        }
//        lastTime = curTime;
//        try{Thread.sleep(40);}catch(Exception e){};
//    }
    
    
    public void buyProducts(){
    
    }

    public String getPesel() {
        return pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Client{" + "pesel=" + pesel + ", firstName=" + firstName + ", lastName=" + lastName + ", cart=" + cart + ", targetX=" + targetX + ", targetY=" + targetY + ", lastTime=" + lastTime + '}';
    }
    
    
    
    
    
    
    
}
