/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.sync;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * A synchronized Gate with dynamically changed capacity.
 * 
 * @author kacper
 */
public class DynamicGate {

    private int capacity;
    private List<Thread> threads;

    /**
     *
     * @param capacity
     */
    public DynamicGate(int capacity) {
        this.capacity = capacity;
        this.threads = new LinkedList<>();
    }

    /**
     *
     * Method to enter a gate
     * @throws InterruptedException
     */
    public synchronized void enter() throws InterruptedException {
        while (threads.size() >= capacity) {
            this.wait();
        }
        threads.add(Thread.currentThread());
    }

    /**
     *
     * Method to leave a gate
     * 
     */
    public synchronized void leave() {
        if (!threads.contains(Thread.currentThread())) {
            throw new IllegalMonitorStateException();
        }
        threads.remove(Thread.currentThread());
        this.notify();
    }

    /**
     *
     * Changes maxCapacity of a gate
     * 
     * @param maxNumber
     */
    public synchronized void setNewCapacity(int maxNumber) {
        this.capacity = maxNumber;
        this.notifyAll();
    }
}
