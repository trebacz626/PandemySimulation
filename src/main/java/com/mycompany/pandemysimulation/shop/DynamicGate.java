/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.shop;

/**
 *
 * @author kacper
 */
public class DynamicGate {

    private int capacity;
    private int counter;

    public DynamicGate(int capacity) {
        this.capacity = capacity;
        this.counter = 0;
    }

    public synchronized void enter() throws InterruptedException {
        while (counter >= capacity) {
            this.wait();
        }
        counter++;
    }

    public synchronized void leave() {
        counter--;
        this.notify();
    }

    public synchronized void setNewCapacity(int maxNumber) {
        System.out.println("counter "+maxNumber);
        this.capacity = maxNumber;
    }
}
