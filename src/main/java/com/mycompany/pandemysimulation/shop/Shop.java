/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.shop;

import com.mycompany.pandemysimulation.App;
import com.mycompany.pandemysimulation.utils.Coordinates;
import com.mycompany.pandemysimulation.Product;
import com.mycompany.pandemysimulation.ui.VisibleComponent;
import com.mycompany.pandemysimulation.core.MainLoopAgent;
import com.mycompany.pandemysimulation.map.Location;
import com.mycompany.pandemysimulation.core.ThreadAgent;
import com.mycompany.pandemysimulation.person.Person;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 * @author kacper
 */
public abstract class Shop extends MainLoopAgent implements Location {

    private String name;
    private String address;
    private int maxClients;
    private SynchronizedShopStorage warehouse;
    private int idX;
    private int idY;
    private int uniqueId;
    private List<Person> peopleInside;
    private Executor executor;

    private static int curId = 0;

    private static synchronized int getNextId() {
        return curId++;
    }

    public Shop(String name, String address, int maxClients, int maxProducts, int idX, int idY, VisibleComponent visibleComponent) {
        super(Coordinates.mapToWorld(idX), Coordinates.mapToWorld(idY), visibleComponent);
        this.name = name;
        this.address = address;
        this.maxClients = maxClients;
        this.warehouse = new SynchronizedShopStorage(maxProducts);
        this.idX = idX;
        this.idY = idY;
        this.uniqueId = getNextId();
        this.peopleInside = new LinkedList<>();
        this.executor = Executors.newSingleThreadExecutor((Runnable r) -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }

    @Override
    public synchronized void enter(ThreadAgent threadAgent) throws InterruptedException{
        this.peopleInside.add((Person) threadAgent);
    }

    @Override
    public synchronized void leave(ThreadAgent threadAgent1) {
        this.peopleInside.remove((Person)threadAgent1);
    }
    
    public synchronized List<Person> getCopyOfPeopleInside(){
        return new LinkedList<>(peopleInside);
    }

    public boolean start() {
        return true;
    }

    public boolean update() {
        executor.execute(()->{ 
            remanent();
        });
        return true;
    }
    
    
    public void addProductSync(Product product){
        executor.execute(()->{
            try {
                this.warehouse.addProduct(product);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }
    
    public SynchronizedShopStorage getWarehouse(){
        return warehouse;
    }
    
    private void remanent(){
        warehouse.lockForInspection();
        List<Product> products = warehouse.getCopyOfProducts();
        for(Product product: products){
            if(new Random().nextDouble() >0.5){
                warehouse.removeExpiredProduct(product);
            }
        }
        warehouse.unlockAfterInspection();
    }

    @Override
    public int getIdX() {
        return idX;
    }

    @Override
    public int getIdY() {
        return idY;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getUniqueId() {
        return uniqueId;
    }
    
    protected int getClientCapacity(){
        return App.simulation.getWorldData().isLockdown() ? (int)(0.25*maxClients) : maxClients;
    }

    @Override
    public String toString() {
        String text = getName() + " " + getxPos() + " " + getyPos() + " ID: " + getUniqueId() + " \n Number of products: "+warehouse.getNumberOfProducts();
        text+="Visitors:\n";
        for(Person visitor: getCopyOfPeopleInside()){
            text+=visitor.isSick()+" ";
        }
        return text;
    }

    @Override
    public List<Location> getGroup() {
        return Collections.singletonList(this);
    }
}
