/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.deadlock;

import com.mycompany.pandemysimulation.core.Location;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kacper
 */
class Node {
    
    Location location;
    List<Node> children;

    Node parent;
    
    public Node(Node parent, Location location){
        this.parent = parent;
        this.location = location;
        this.children = new LinkedList<>();
    }
    
    public void addChild(Node child){
        this.children.add(child);
    }

    public Location getLocation() {
        return location;
    }

    public List<Node> getChildren() {
        return children;
    }

    public Node getParent() {
        return parent;
    }
    
    
}
