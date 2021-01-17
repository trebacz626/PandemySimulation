/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.person;

import com.mycompany.pandemysimulation.App;
import com.mycompany.pandemysimulation.Product;
import com.mycompany.pandemysimulation.Utils;
import com.mycompany.pandemysimulation.map.PathFinder;
import com.mycompany.pandemysimulation.ui.VisibleComponent;
import com.mycompany.pandemysimulation.shop.Shop;
import com.mycompany.pandemysimulation.shop.RetailShop;
import com.mycompany.pandemysimulation.map.Location;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

    public Supplier(CarBrand carBrand, int trunkCapacity, double gasCapacity, Company companyName, boolean sick, int shopsVisitedWhileSick, boolean vaccinated, Location nextStop, Location currentLocation, boolean waiting, double xPos, double yPos, VisibleComponent visibleComponent, PathFinder pathFinder) {
        super(sick, shopsVisitedWhileSick, vaccinated, nextStop, currentLocation, waiting, xPos, yPos, visibleComponent, pathFinder);
        this.carBrand = carBrand;
        this.gasCapacity = gasCapacity;
        this.companyName = companyName;
        this.trunk = new ProductStorage(trunkCapacity);
        this.uniqueId = getNextId();
    }
    
    @Override
    public boolean start(){
        if(!super.start())
            return false;
        this.route = new LinkedList<>();
        for(int i =0;i<3;i++){
            route.add(App.simulation.getRandomWholesaleShop(null));
        }
        for(int i = 0;i<5;i++){
            route.add(App.simulation.getRandomRetailShop(null));
        }
        Collections.shuffle(route);
        route.add(0,(Shop)this.getCurrentLocation());
        shopIndex=0;
        return true;
    }
    
    @Override
    protected synchronized Shop generateNextGoal() {
        return route.get((shopIndex++)%route.size());
        
    }
    
    @Override
    protected void processShop(Shop shop) {
        refuel();
        if(shop instanceof RetailShop){
            if(trunk.isEmpty()) return;
            int productsToStore = new Random().nextInt(trunk.getNumberOfProducts())+1;
            while(!shop.getWarehouse().isFull() && !trunk.isEmpty()){
                shop.getWarehouse().addProduct(trunk.getAndRemoveProduct());
            }
        }else{
            if(trunk.isFull()) return;
            List<String> names = shop.getWarehouse().getProductNames();
            if(names.isEmpty())return;
            while(!trunk.isFull()){
                Product product = shop.getWarehouse().getAndRemoveProduct(Utils.getRandomFromList(names));
                if(product != null){
                    trunk.addProduct(product);
                }
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
