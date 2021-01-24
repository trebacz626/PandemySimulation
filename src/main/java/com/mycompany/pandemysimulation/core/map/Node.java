/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core.map;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kacper
 */
class Node {
    
    private List<Location> locations;
    private List<Location> children;
    
    public Node(List<Location> locations){
        this.locations = locations;
        this.children = new LinkedList<>();
    }
    
    public void addChild(Location child){
        this.children.add(child);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public List<Location> getChildren() {
        return children;
    }
    
    public int getIdx(){
        return locations.stream().map(location-> location.getIdX()).min(Integer::compare).orElseThrow();
    }
    
    public int getIdy(){
        return locations.stream().map(location-> location.getIdY()).min(Integer::compare).orElseThrow();
    }

    public void setChildren(List<Location> children) {
        this.children = children;
    }
    
    public void setChild(Location child){
        children.add(child);
    }
}
