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
import com.mycompany.pandemysimulation.product.Product;
import com.mycompany.pandemysimulation.shop.Shop;
import java.util.Random;

/**
 *
 * A client is an Person agent that travels between random Shops. In each shop
 * it buys a random number of products.
 *
 * @author kacper
 */
public class Client extends Person {

    private static long lastPesel = 11111111111L;

    private synchronized static String getNextPesel() {
        return String.valueOf(lastPesel++);
    }

    private String pesel;
    private String firstName;
    private String lastName;

    /**
     *
     * Constructor
     *
     * @param xPos
     * @param yPos
     * @param visibleComponent
     * @param firstName
     * @param lastName
     * @param sick tells if client is sick
     * @param vaccinated tells if client is vaccinated
     * @param wearingMask tells if wearing a mask
     * @param currentLocation
     * @param maxCartCapacity
     * @param pathFinder
     */
    protected Client(double xPos, double yPos, VisibleComponent visibleComponent, String firstName, String lastName, boolean sick, boolean vaccinated, boolean wearingMask, Location currentLocation, int maxCartCapacity, PathFinder pathFinder) {
        super(maxCartCapacity, sick, vaccinated, wearingMask, currentLocation, xPos, yPos, visibleComponent, pathFinder);
        this.pesel = getNextPesel();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private void consume(int n) {
        getProductStorage().removeNProducts(n);
    }

    /**
     *
     * Generates a next Shop that Client is going to go to
     *
     * @return
     */
    @Override
    protected Shop generateNextGoal() {
        return this.getRandomRetailShop(this.getCurrentShop());

    }

    /**
     *
     * Actions that clients takes in shop. Here client buys a random number of
     * products.
     *
     * @param shop
     * @throws InterruptedException
     */
    @Override
    protected void processShop(Shop shop) throws InterruptedException {
        int productsTobuy = new Random().nextInt(getProductStorage().getCapacity()) + 1;
        if (getProductStorage().getFreePlace() < productsTobuy) {
            getProductStorage().removeNProducts(productsTobuy - getProductStorage().getFreePlace());
        }
        int boughtProducts = 0;
        while (boughtProducts < productsTobuy) {
            Product product = shop.getWarehouse().takeRandomProduct();
            getProductStorage().addProduct(product);
            boughtProducts++;
        }
    }

    /**
     *
     * @return
     */
    public String getPesel() {
        return pesel;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

}
