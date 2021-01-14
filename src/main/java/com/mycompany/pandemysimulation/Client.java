/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import com.mycompany.pandemysimulation.core.Location;
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
    protected Shop generateNextGoal() {
        return App.simulation.getRandomRetailShop((Shop)this.getCurrentLocation());
        
    }
    
    protected void processShop(Shop shop){
        int productsTobuy = new Random().nextInt(cart.getCapacity())+1;
        if(cart.getFreePlace() < productsTobuy){
            cart.removeNProducts(productsTobuy-cart.getFreePlace());
        }
        int boughtProducts = 0;
        List<String> names = shop.getWarehouse().getProductNames();
//        System.out.println(names);
        if(names.isEmpty())return;
        while(boughtProducts < productsTobuy){
            Product product = shop.getWarehouse().getAndRemoveProduct(Utils.getRandomFromList(names));
            if(product != null){
                cart.addProduct(product);
            }
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
