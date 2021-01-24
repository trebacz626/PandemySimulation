/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.map;

import com.mycompany.pandemysimulation.core.map.Direction;
import java.util.Collections;

/**
 *
 * @author kacper
 */
public class ComplexMap extends MapCreator{
    public MapBuilder getMapBuilder(){
        return new MapBuilder(53, 27)
                .addTwoWayRoadX(2, 45, 0)
                .addTwoWayRoadX(2, 45, 25)
                .addTwoWayRoadY(2, 24, 0)
                .addTwoWayRoadY(2, 24, 46)
                
                //Left Circle
                .addTwoWayPavementY(6, 20, 7)
                .addOnePavement(7, 21, Collections.singletonList(Direction.Righ))
                .addOnePavement(8, 21, Collections.singletonList(Direction.Up))
                .addOnePavement(7, 5, Collections.singletonList(Direction.Down))
                .addOnePavement(8, 5, Collections.singletonList(Direction.Left))
                
                //Center left shop
                .addPavementDirection(7, 10, Direction.Left)
                .addOnePavement(6, 10, Collections.singletonList(Direction.Left))
                .addPavementY(10, 12, 5)
                .addOnePavement(5, 13, Collections.singletonList(Direction.Left))
                .addPavementY(14, 15, 4)
                .addPavementX(4, 6, 16)
                .addRetailShop(4, 13, "Biedronka", "Nihilstyczna 1")
                .addPavementDirection(4, 13, Direction.Down)
                //--roads
                .addRoadDirection(1, 15, Direction.Righ)
                .addOneRoad(2, 15, Collections.singletonList(Direction.Righ))
                .addRoadY(15,14, 3)
                .addOneRoad(3,13, Collections.singletonList(Direction.Righ))
                .addRoadDirection(4, 13, Direction.Up)
                .addRoadY(12,10,4)
                .addRoadX(4,2,9)
                
                //Up Left Shop
                .addPavementDirection(7, 5, Direction.Left)
                .addOnePavement(6, 5, Collections.singletonList(Direction.Left))
                .addPavementY(5, 6, 5)
                .addOnePavement(5, 7, Collections.singletonList(Direction.Left))
                .addPavementX(4, 6, 8)
                .addRetailShop(4, 7, "Lidl", "Nihilstyczna 1")
                .addPavementDirection(4, 7, Direction.Down)
                .addRoadX(1,3,7)
                .addRoadDirection(4, 7, Direction.Up)
                .addRoadY(6, 2, 4)
                
                //Down Left Shop
                .addPavementDirection(7, 18, Direction.Left)
                .addOnePavement(6, 18 , Collections.singletonList(Direction.Left))
                .addPavementY(18,19,5)
                .addOnePavement(5, 20, Collections.singletonList(Direction.Left))
                .addPavementX(4, 6, 21)
                .addRetailShop(4, 20, "Stokrotka", "Nowhere never")
                .addPavementDirection(4, 20, Direction.Down)
                .addRoadX(1,2,23)
                .addRoadY(23,21,3)
                .addOneRoad(3, 20, Collections.singletonList(Direction.Righ))
                .addRoadDirection(4, 20, Direction.Up)
                .addRoadY(19, 18, 4)
                .addRoadX(4,2,17)
                
                
                .addPavementDirection(8, 13, Direction.Righ)
                .addTwoWayPavementX(9, 35, 12)
                .addPavementY(9, 15, 11)
                .addPavementX(11, 30, 16)
                .addPavementY(16, 10, 30)
                .addPavementX(30, 11, 9)
                .add2x2PavementIntersection(11, 12)
                
                //center bottom left
                .addPavementDirection(12, 16, Direction.Down)
                .addOnePavement(12, 17, Collections.singletonList(Direction.Down))
                .addPavementX(12, 14, 18)
                .addOnePavement(15, 18, Collections.singletonList(Direction.Down))
                .addRetailShop(15, 19, "nie wiem 0", "eloooo")
                .addPavementDirection(15, 19, Direction.Righ)
                .addPavementY(19, 17, 16)
                .addRoadY(21,20,11)
                .addRoadX(11,14,19)
                .addRoadDirection(15, 19, Direction.Down)
                .addOneRoad(15, 20, Collections.singletonList(Direction.Down))
                //center bottom center
                .addPavementDirection(18, 16, Direction.Down)
                .addOnePavement(18, 17, Collections.singletonList(Direction.Down))
                .addPavementX(18, 20, 18)
                .addOnePavement(21, 18, Collections.singletonList(Direction.Down))
                .addRetailShop(21, 19, "nie wiem", "eloooo")
                .addPavementDirection(21, 19, Direction.Righ)
                .addPavementY(19, 17, 22)
                .addRoadY(21,20,17)
                .addRoadX(17,20,19)
                .addRoadDirection(21, 19, Direction.Down)
                .addOneRoad(21, 20, Collections.singletonList(Direction.Down))
                //center bottom right
                .addPavementDirection(24, 16, Direction.Down)
                .addOnePavement(24, 17, Collections.singletonList(Direction.Down))
                .addPavementX(24, 26, 18)
                .addOnePavement(27, 18, Collections.singletonList(Direction.Down))
                .addRetailShop(27, 19, "nie wiem 1", "eloooo")
                .addPavementDirection(27, 19, Direction.Righ)
                .addPavementY(19, 17, 28)
                .addRoadY(21,20,23)
                .addRoadX(23,26,19)
                .addRoadDirection(27, 19, Direction.Down)
                .addOneRoad(27, 20, Collections.singletonList(Direction.Down))
                //center upper left
                .addPavementY(9,8,16)
                .addPavementX(16, 14, 7)
                .addOnePavement(13, 7, Collections.singletonList(Direction.Up))
                .addRetailShop(13, 6, "hello", "mello street")
                .addPavementDirection(13, 6, Direction.Left)
                .addPavementY(6, 8 ,12)
                .addRoadY(4,5,17)
                .addRoadX(17,14,6)
                .addRoadDirection(13, 6, Direction.Up)
                .addOneRoad(13, 5, Collections.singletonList(Direction.Up))
                //center upper center
                .addPavementY(9,8,22)
                .addPavementX(22, 20, 7)
                .addOnePavement(19, 7, Collections.singletonList(Direction.Up))
                .addRetailShop(19, 6, "hello", "mello street")
                .addPavementDirection(19, 6, Direction.Left)
                .addPavementY(6, 8 ,18)
                .addRoadY(4,5,23)
                .addRoadX(23,20,6)
                .addRoadDirection(19, 6, Direction.Up)
                .addOneRoad(19, 5, Collections.singletonList(Direction.Up))
                //center upper right
                .addPavementY(9,8,28)
                .addPavementX(28, 26, 7)
                .addOnePavement(25, 7, Collections.singletonList(Direction.Up))
                .addRetailShop(25, 6, "hello", "mello street")
                .addPavementDirection(25, 6, Direction.Left)
                .addPavementY(6, 8 ,24)
                .addRoadY(4,5,29)
                .addRoadX(29,26,6)
                .addRoadDirection(25, 6, Direction.Up)
                .addOneRoad(25, 5, Collections.singletonList(Direction.Up))
                
                //Right Part
                .add2x2PavementIntersection(29, 12)
                
                //last Shop
                .addRetailShop(35, 13, "name", "address")
                .addPavementDirection(35, 13, Direction.Up)
                .addRoadX(33,34,14)
                .addOneRoad(35, 14, Collections.singletonList(Direction.Up))
                .addRoadDirection(35,13,Direction.Righ)
                .addRoadY(13, 12, 36)
                .addRoadX(36,34, 11)
                
                //
                .addRoadY(25, 22, 9)
                .addRoadX(9, 32, 21)
                .addRoadY(21,15,33)
                .addRoadX(33, 37, 16)
                .addRoadY(16, 10, 38)
                .addRoadX(38, 34, 9)
                .addRoadY(11, 5,33)
                .addRoadX(33,10,4)
                .addRoadY(4,2,9)
                
                
                .addTwoWayRoadX(39, 45, 12)
                .addRoadDirection(38, 13, Direction.Left)
                .addRoadDirection(46, 12, Direction.Down)
                
                .add2x2RoadIntersection(0, 0)
                .add2x2RoadIntersection(46, 0)
                .add2x2RoadIntersection(0, 25)
                .add2x2RoadIntersection(46, 25)
                .add2x2RoadIntersection(46, 12)
                .add2x2RoadIntersection(8, 0)
                .add2x2RoadIntersection(8, 25)
                
                //Wholesale
                .addRoadX(46, 36, 18)
                .addRoadY(18,22, 35)
                .addRoadX(35,29,23)
                .addRoadY(23,24,28)
                .add2x2RoadIntersection(27, 25)
                .addWholesaleShop(35, 23, "Makro", "Nowhere")
                .addRoadDirection(46, 18, Direction.Left)
                
                .addRoadX(46,36,7)
                .addRoadY(7,3 , 35)
                .addRoadX(35,25,2)
                .addOneRoad(24, 2, Collections.singletonList(Direction.Up))
                .addWholesaleShop(35, 4, "Some Shop", "Gdańsk")
        
                .addRoadX(47,51,24)
                .addRoadY(24,8,52)
                .addRoadX(52, 48, 7)
                .addWholesaleShop(52, 14, "Last One Standing", "Groszkowa 2")
                
                .addOnePavement(9, 10, Collections.singletonList(Direction.Left))
                .markSpawnPointPedestrian(9, 10)
                
                .addOneRoad(20, 24, Collections.singletonList(Direction.Down))
                .markSpawnPointRoad(20, 24)
                ;
    }
}
