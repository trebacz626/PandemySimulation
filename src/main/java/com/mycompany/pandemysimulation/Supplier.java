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
public class Supplier extends Person{
    private CarBrand carBrand;
    private double gas;
    private double gasCapacity;
    private Company companyName;
    private LinkedList<Location> listOfStops;
    private ProductStorage trunk;

    public Supplier(CarBrand carBrand, int trunkCapacity, double gasCapacity, Company companyName, LinkedList<Location> listOfStops, boolean sick, int shopsVisitedWhileSick, boolean vaccinated, Location nextStop, Location currentLocation, boolean waiting, double xPos, double yPos, VisibleComponent visibleComponent) {
        super(sick, shopsVisitedWhileSick, vaccinated, nextStop, currentLocation, waiting, xPos, yPos, visibleComponent);
        this.carBrand = carBrand;
        this.gasCapacity = gasCapacity;
        this.companyName = companyName;
        this.listOfStops = listOfStops;
        this.trunk = new ProductStorage(trunkCapacity);
    }

    
    public LinkedList<Product> getProducts(){
        return null;
    }
    
    public void refueal(){
        this.gas=this.gasCapacity;
    }
    
    public void leaveProducts(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public void updateRoute(){
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }
    
    
    @Override
    protected Location getNextLocation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected LinkedList<Location> searchForPath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    protected void start() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    protected void update() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public String toString() {
        return "Supplier{" + "carBrand=" + carBrand + ", gas=" + gas + ", gasCapacity=" + gasCapacity + ", companyName=" + companyName + ", listOfStops=" + listOfStops + ", trunk=" + trunk + '}';
    }
    
    
}
