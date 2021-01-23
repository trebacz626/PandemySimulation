/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.person;

import com.mycompany.pandemysimulation.App;
import com.mycompany.pandemysimulation.Product;
import com.mycompany.pandemysimulation.utils.Utils;
import com.mycompany.pandemysimulation.map.PathFinder;
import com.mycompany.pandemysimulation.ui.VisibleComponent;
import com.mycompany.pandemysimulation.shop.Shop;
import com.mycompany.pandemysimulation.map.Location;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
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

    public Client( double xPos, double yPos, VisibleComponent visibleComponent,String pesel, String firstName, String lastName, boolean sick, int shopsVisitedWhileSick, boolean vaccinated, boolean wearingMask, Location nextStop, Location currentLocation, boolean waiting, int maxCartCapacity, PathFinder pathFinder) {
        super(sick, shopsVisitedWhileSick, vaccinated,wearingMask, nextStop, currentLocation, waiting, xPos, yPos, visibleComponent, pathFinder);
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cart = new ProductStorage(maxCartCapacity);
    }

    private void consume(int n){
        cart.removeNProducts(n);
    }
    
    @Override
    protected Shop generateNextGoal() {
        return this.getRandomRetailShop(this.getCurrentShop());
        
    }
    
    protected void processShop(Shop shop) throws InterruptedException{
        int productsTobuy = new Random().nextInt(cart.getCapacity())+1;
        if(cart.getFreePlace() < productsTobuy){
            cart.removeNProducts(productsTobuy-cart.getFreePlace());
        }
        int boughtProducts = 0;
        while(boughtProducts < productsTobuy){
            Product product = shop.getWarehouse().takeRandomProduct();
            cart.addProduct(product);
            boughtProducts++;
        }
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
        return "Client{" + "pesel=" + pesel + ", firstName=" + firstName + ", lastName=" + lastName + ", cart=" + cart + ", lastTime=" + lastTime + '}';
    }
   
}
