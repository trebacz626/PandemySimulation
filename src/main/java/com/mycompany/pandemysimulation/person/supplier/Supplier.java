/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.person.supplier;

import com.mycompany.pandemysimulation.core.map.Location;
import com.mycompany.pandemysimulation.core.map.PathFinder;
import com.mycompany.pandemysimulation.core.ui.VisibleComponent;
import com.mycompany.pandemysimulation.person.Person;
import com.mycompany.pandemysimulation.person.ProductStorage;
import com.mycompany.pandemysimulation.product.Product;
import com.mycompany.pandemysimulation.shop.Shop;
import com.mycompany.pandemysimulation.shop.retailshop.RetailShop;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kacper
 */
public class Supplier extends Person{
    private CarBrand carBrand;
    private double gas;
    private double gasCapacity;
    private Company companyName;
    private List<Shop> route;
    private int shopIndex;
    private ProductStorage trunk;
    private PathFinder pathFinder;
    private int uniqueId;
    
    private static int curId=0;
    
    private static synchronized int getNextId(){
        return curId++;
    }

    protected Supplier(CarBrand carBrand, int trunkCapacity, double gasCapacity, Company companyName, boolean sick, boolean vaccinated, boolean wearingMask, Location nextStop, Location currentLocation, double xPos, double yPos, VisibleComponent visibleComponent, PathFinder pathFinder) {
        super(sick, vaccinated,wearingMask, nextStop, currentLocation, xPos, yPos, visibleComponent, pathFinder);
        this.carBrand = carBrand;
        this.gasCapacity = gasCapacity;
        this.companyName = companyName;
        this.trunk = new ProductStorage(trunkCapacity);
        this.uniqueId = getNextId();
        this.route = new LinkedList<>();
    }
    
    @Override
    public boolean start(){
        if(!super.start())
            return false;
        for(int i =0;i<3;i++){
            route.add(this.getRandomWholesaleShop(null));
        }
        for(int i = 0;i<5;i++){
            route.add(this.getRandomRetailShop(null));
        }
        Collections.shuffle(route);
        shopIndex=0;
        return true;
    }
    
    @Override
    protected synchronized Shop generateNextGoal() {
        return route.get((shopIndex++)%route.size());
        
    }
    
    @Override
    protected void processShop(Shop shop) throws InterruptedException {
        refuel();
        if(shop instanceof RetailShop){
            if(trunk.isEmpty()) return;
            while(!shop.getWarehouse().isFull() && !trunk.isEmpty()){
                shop.getWarehouse().addProduct(trunk.getAndRemoveProduct());
            }
        }else{
            while(!trunk.isFull()){
                Product product = shop.getWarehouse().takeRandomProduct();
                trunk.addProduct(product);
            }
        }
    }
    
    public LinkedList<Product> getProducts(){
        return null;
    }
    
    private void refuel(){
        this.gas=this.gasCapacity;
    }
    
    protected void onMove(){
        this.gas-=1;
    }


    @Override
    public String toString() {
        return "Supplier{" + "carBrand=" + carBrand + ", gas=" + gas + ", gasCapacity=" + gasCapacity + ", companyName=" + companyName + ", listOfStops=" + route + ", trunk=" + trunk + '}';
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public Company getCompanyName() {
        return companyName;
    }

    public int getUniqueId() {
        return uniqueId;
    }
    
    public List<Shop> getRoute(){
        return route;
    }
    
    public synchronized void setRoute(List<Shop> route){
        this.route = route;
    }
    
}
