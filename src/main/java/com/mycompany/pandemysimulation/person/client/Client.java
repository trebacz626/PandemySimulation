/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.person.client;

import com.mycompany.pandemysimulation.core.map.Location;
import com.mycompany.pandemysimulation.core.map.PathFinder;
import com.mycompany.pandemysimulation.core.ui.VisibleComponent;
import com.mycompany.pandemysimulation.person.Person;
import com.mycompany.pandemysimulation.person.ProductStorage;
import com.mycompany.pandemysimulation.product.Product;
import com.mycompany.pandemysimulation.shop.Shop;
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

    protected Client( double xPos, double yPos, VisibleComponent visibleComponent,String pesel, String firstName, String lastName, boolean sick, boolean vaccinated, boolean wearingMask, Location nextStop, Location currentLocation, int maxCartCapacity, PathFinder pathFinder) {
        super(sick, vaccinated,wearingMask, nextStop, currentLocation, xPos, yPos, visibleComponent, pathFinder);
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
