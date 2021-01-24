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
 * @author kacper
 */
public class DynamicGate {

    private int capacity;
    private List<Thread> threads;

    public DynamicGate(int capacity) {
        this.capacity = capacity;
        this.threads = new LinkedList<>();
    }

    public synchronized void enter() throws InterruptedException {
        while (threads.size() >= capacity) {
            this.wait();
        }
        threads.add(Thread.currentThread());
    }

    public synchronized void leave() {
        if(!threads.contains(Thread.currentThread()))throw new IllegalMonitorStateException();
        threads.remove(Thread.currentThread());
        this.notify();
    }

    public synchronized void setNewCapacity(int maxNumber) {
        this.capacity = maxNumber;
        this.notifyAll();
    }
}
