/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core;

import java.util.Date;

/**
 *
 * @author kacper
 */
class DateKeeper {
    private Date startDate;

    protected DateKeeper() {
        startDate = new Date();
    }
    
    protected Date getCurDate(){
        Date curDate = new Date();
        long diff = curDate.getTime() - startDate.getTime();
        diff*=30000;
        curDate.setTime(startDate.getTime() + diff);
        return curDate;
    }
    
}
