/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.Date;

/**
 *
 * @author kacper
 */
public class WorldData {
    private static WorldData instance;
    private int numberOfPeople;
    private int numberOfSickPeople;
    private double chanceOfInfection;
    private double chanceOfInfectionWithVaccine;
    private double chanceOfInfectionWithMask;
    private Date currentTime;
    private boolean lockdown;
    private double maxVaccinated;
    
    public static WorldData getInstance(){
        if(WorldData.instance == null){
            WorldData.instance = new WorldData();
        }
        return WorldData.instance;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public int getNumberOfSickPeople() {
        return numberOfSickPeople;
    }

    public double getChanceOfInfection() {
        return chanceOfInfection;
    }

    public double getChanceOfInfectionWithVaccine() {
        return chanceOfInfectionWithVaccine;
    }

    public double getChanceOfInfectionWithMask() {
        return chanceOfInfectionWithMask;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public boolean isLockdown() {
        return lockdown;
    }

    public double getMaxVaccinated() {
        return maxVaccinated;
    }

    public static void setInstance(WorldData instance) {
        WorldData.instance = instance;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public void setNumberOfSickPeople(int numberOfSickPeople) {
        this.numberOfSickPeople = numberOfSickPeople;
    }

    public void setChanceOfInfection(double chanceOfInfection) {
        this.chanceOfInfection = chanceOfInfection;
    }

    public void setChanceOfInfectionWithVaccine(double chanceOfInfectionWithVaccine) {
        this.chanceOfInfectionWithVaccine = chanceOfInfectionWithVaccine;
    }

    public void setChanceOfInfectionWithMask(double chanceOfInfectionWithMask) {
        this.chanceOfInfectionWithMask = chanceOfInfectionWithMask;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public void setLockdown(boolean lockdown) {
        this.lockdown = lockdown;
    }

    public void setMaxVaccinated(double maxVaccinated) {
        this.maxVaccinated = maxVaccinated;
    }
    
    
    
}
