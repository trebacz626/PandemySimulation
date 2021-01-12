/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.Collections;

/**
 *
 * @author kacper
 */
public class SimpleMap {
    public static MapBuilder getMap(){
        MapBuilder mapBuilder = new MapBuilder(48, 27)
                .addTwoWayPavementX(2, 13, 0).addTwoWayPavementX(2,13, 7).addTwoWayPavementX(2,13, 14)
                .addTwoWayPavementY(2, 13, 0).addTwoWayPavementY(2, 13, 7).addTwoWayPavementY(2,13,14)
                .add2x2PavementIntersection(0, 0).add2x2PavementIntersection(14, 0).add2x2PavementIntersection(0,14).add2x2PavementIntersection(14, 14).add2x2PavementIntersection(7, 7)
                .add2x2PavementIntersection(7, 0).add2x2PavementIntersection(0, 7).add2x2PavementIntersection(14, 7).add2x2PavementIntersection(7, 14)
                .addPavementDirection(5, 14, Direction.Up)
                .addOnePavement(3, 13, Collections.singletonList(Direction.Down))
                .addOnePavement(5, 13, Collections.singletonList(Direction.Up))
                .addPavementX(5, 4, 12)
                .addOnePavement(3, 12, Collections.singletonList(Direction.Down))
                .addRetailShop(4, 12, "Biedronka", "Nihilstyczna 1")
                //
                .addPavementDirection(12, 14, Direction.Up)
                .addOnePavement(10, 13, Collections.singletonList(Direction.Down))
                .addOnePavement(12, 13, Collections.singletonList(Direction.Up))
                .addPavementX(12, 11, 12)
                .addOnePavement(10, 12, Collections.singletonList(Direction.Down))
                .addRetailShop(11, 12, "Lidl", "Nihilstyczna 2")
                //
                .addPavementDirection(12, 7, Direction.Up)
                .addOnePavement(10, 6, Collections.singletonList(Direction.Down))
                .addOnePavement(12, 6, Collections.singletonList(Direction.Up))
                .addPavementX(12, 11, 5)
                .addOnePavement(10, 5, Collections.singletonList(Direction.Down))
                .addRetailShop(11, 5, "Stokrotka", "Nihilstyczna 2")
                //
                .addPavementDirection(5, 7, Direction.Up)
                .addOnePavement(3, 6, Collections.singletonList(Direction.Down))
                .addOnePavement(5, 6, Collections.singletonList(Direction.Up))
                .addPavementX(5, 4, 5)
                .addOnePavement(3, 5, Collections.singletonList(Direction.Down))
                .addRetailShop(4, 5, "Żabka", "Nihilstyczna 4")
                .build();
        return mapBuilder;
    }
}
