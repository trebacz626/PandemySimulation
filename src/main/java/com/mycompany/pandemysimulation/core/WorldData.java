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
public class WorldData {
    private int numberOfPeople;
    private int numberOfSickPeople;
    private double transmissionRate;
    private double vaccineRate;
    private double maskEffect;
    private double lockDownThreshold;
    private int shopVisitedWhileSick;
    
//    private Date currentTime;
    private boolean lockdown;   

    public WorldData(double transmissionRate, double vaccineRate, double maskEffect, double lockDownThreshold, int shopVisitedWhileSick) {
        this.transmissionRate = transmissionRate;
        this.vaccineRate = vaccineRate;
        this.maskEffect = maskEffect;
        this.lockDownThreshold = lockDownThreshold;
        this.shopVisitedWhileSick = shopVisitedWhileSick;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public int getNumberOfSickPeople() {
        return numberOfSickPeople;
    }

    public double getTransmissionRate() {
        return transmissionRate;
    }

    public double getVaccineRate() {
        return vaccineRate;
    }

    public double getMaskEffect() {
        return maskEffect;
    }

    public double getLockDownThreshold() {
        return lockDownThreshold;
    }

    public int getShopVisitedWhileSick() {
        return shopVisitedWhileSick;
    }

//    public Date getCurrentTime() {
//        return currentTime;
//    }

    public boolean isLockdown() {
        return lockdown;
    }

    protected void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
        updateLockdown();
    }

    protected void setNumberOfSickPeople(int numberOfSickPeople) {
        this.numberOfSickPeople = numberOfSickPeople;
        updateLockdown();
    }

    public void setTransmissionRate(double transmissionRate) {
        this.transmissionRate = transmissionRate;
    }

    public void setVaccineRate(double vaccineRate) {
        this.vaccineRate = vaccineRate;
    }

    public void setMaskEffect(double maskEffect) {
        this.maskEffect = maskEffect;
    }

    public void setLockDownThreshold(double lockDownThreshold) {
        this.lockDownThreshold = lockDownThreshold;
        updateLockdown();
    }

    public void setShopVisitedWhileSick(int shopVisitedWhileSick) {
        this.shopVisitedWhileSick = shopVisitedWhileSick;
    }
    
    public double getSickPercentage(){
        if(this.numberOfPeople == 0) return 0;
        return (double) this.numberOfSickPeople/ (double)this.numberOfPeople;
    }
    
    private void updateLockdown(){
        if(getSickPercentage() > lockDownThreshold)
            this.lockdown = false;
        else
            this.lockdown = true;
    }
    
    
}
