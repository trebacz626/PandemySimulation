/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

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
    private LinkedList<Shop> listOfShops;
    private int shopIndex;
    private ProductStorage trunk;
    private PathFinder pathFinder;

    public Supplier(CarBrand carBrand, int trunkCapacity, double gasCapacity, Company companyName, boolean sick, int shopsVisitedWhileSick, boolean vaccinated, Location nextStop, Location currentLocation, boolean waiting, double xPos, double yPos, VisibleComponent visibleComponent, PathFinder pathFinder) {
        super(sick, shopsVisitedWhileSick, vaccinated, nextStop, currentLocation, waiting, xPos, yPos, visibleComponent, pathFinder);
        this.carBrand = carBrand;
        this.gasCapacity = gasCapacity;
        this.companyName = companyName;
        this.trunk = new ProductStorage(trunkCapacity);
    }
    
    @Override
    public boolean start(){
        if(!super.start())
            return false;
        this.listOfShops = new LinkedList<>();
        for(int i =0;i<3;i++){
            listOfShops.add(App.simulation.getRandomWholesaleShop(null));
        }
        
        for(int i = 0;i<5;i++){
            listOfShops.add(App.simulation.getRandomRetailShop(null));
        }
        Collections.shuffle(listOfShops);
        listOfShops.addFirst((Shop)this.getCurrentLocation());
        shopIndex=0;
        return true;
    }
    
    @Override
    protected Shop generateNextGoal() {
        return listOfShops.get((shopIndex++)%listOfShops.size());
        
    }
    
    @Override
    protected void processShop(Shop shop) {
//        System.out.println("Process: "+carBrand+" "+companyName);
        if(shop instanceof RetailShop){
            if(trunk.isEmpty()) return;
            int productsToStore = new Random().nextInt(trunk.getNumberOfProducts())+1;
            while(!shop.getWarehouse().isFull() && !trunk.isEmpty()){
//                System.out.println("Adding");
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
    
    public void refueal(){
        this.gas=this.gasCapacity;
    }


    @Override
    public String toString() {
        return "Supplier{" + "carBrand=" + carBrand + ", gas=" + gas + ", gasCapacity=" + gasCapacity + ", companyName=" + companyName + ", listOfStops=" + listOfShops + ", trunk=" + trunk + '}';
    }
    
}
