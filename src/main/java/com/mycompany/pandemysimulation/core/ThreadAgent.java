/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core;

import com.mycompany.pandemysimulation.core.ui.VisibleComponent;

/**
 *
 * An Agent that works on a separate thread
 *
 * @author kacper
 */
public abstract class ThreadAgent extends SimulationAgent implements Runnable {

    private Thread thread;

    /**
     *
     * Constructor
     *
     * @param xPos pos x of agent
     * @param yPos pos y of agent
     * @param visibleComponent visible component of agent
     */
    protected ThreadAgent(double xPos, double yPos, VisibleComponent visibleComponent) {
        super(xPos, yPos, visibleComponent);
    }

    /**
     *
     * Performs start operation of agent and than performs update operations
     * till it returns false
     *
     */
    @Override
    public final void run() {
        thread = Thread.currentThread();
        if (!this.start()) {
            return;
        }
        while (this.update());
    }

    /**
     *
     * Kills a thread agent. If any thread agent is interrupted it should die.
     * 
     */
    public void kill() {
        thread.interrupt();
    }

}
