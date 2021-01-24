/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core.map;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kacper
 */
class Node {
    
    private List<Location> locations;
    private List<Location> children;
    
    protected Node(List<Location> locations){
        this.locations = locations;
        this.children = new LinkedList<>();
    }
    
    protected void addChild(Location child){
        this.children.add(child);
    }

    protected List<Location> getLocations() {
        return locations;
    }

    protected List<Location> getChildren() {
        return children;
    }
    
    protected int getIdx(){
        return locations.stream().map(location-> location.getIdX()).min(Integer::compare).orElseThrow();
    }
    
    protected int getIdy(){
        return locations.stream().map(location-> location.getIdY()).min(Integer::compare).orElseThrow();
    }

    protected void setChildren(List<Location> children) {
        this.children = children;
    }
    
    protected void setChild(Location child){
        children.add(child);
    }
}
