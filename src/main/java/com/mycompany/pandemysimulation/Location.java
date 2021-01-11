/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

/**
 *
 * @author kacper
 */
public interface Location {
    public int getIdX();
    public int getIdY();
    public void enter(ThreadAgent threadAgent);
    public void leave(ThreadAgent threadAgent);
}
