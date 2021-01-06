/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.LinkedList;
import javafx.util.Pair;

/**
 *
 * @author kacper
 */
public class WorldGraph {
    private LinkedList<Location> locations;
    private LinkedList<Pair<Integer,Integer>> edges;
    
    public WorldGraph(LinkedList<Location> locations,LinkedList<Pair<Integer,Integer>> edges){
        this.locations = locations;
        this.edges = edges;
    }
    
    public LinkedList<Location> getAdjacentVertices(Location location){
        return null;
    }
    
    public boolean isConnection(int leftId, int rightId){
        return edges.contains(new Pair(leftId, rightId));
    }
    
}
