/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.map;

import com.mycompany.pandemysimulation.core.ThreadAgent;
import java.util.List;

/**
 *
 * @author kacper
 */
public interface Location {
    public int getIdX();
    public int getIdY();
    public void enter(ThreadAgent threadAgent) throws InterruptedException;
    public void leave(ThreadAgent threadAgent);
    public List<Location> getGroup();
}
