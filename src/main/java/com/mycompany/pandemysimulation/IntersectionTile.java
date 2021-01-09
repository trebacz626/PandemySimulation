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
public class IntersectionTile extends Tile{
    private Intersection intersection;
    public IntersectionTile(int idX, int idY, TileType tileType, Intersection intersection){
        super(idX,idY,tileType);
        this.intersection = intersection;
    }
    
    public void enter(ThreadAgent threadAgent){
        intersection.enter(threadAgent);
        super.enter(threadAgent);
    }
    
    public void leave(ThreadAgent threadAgent){
        intersection.leave(threadAgent);
        super.leave(threadAgent);
    }
}
