/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.core.data;

/**
 *
 *
 * Global parameters of the world
 *
 *  transmissionRate number 0-1 rate with which virus spreads
 *  vaccineRate number 0-1 rate with vaccine influences a chance of
 * getting a disease
 *  maskEffect number 0-1 rate with vaccine influences a chance of getting
 * and spreading a disease
 *  lockDownThreshold number 0-1 threshold after which lockdown should be
 * introduced
 *  locationsVisitedWhileSick number of locations an agent should visit
 * until it stops being ill
 *  vaccinateChance chance that a newly created agent will be vaccinated
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
    private int locationsVisitedWhileSick;
    private double vaccinateChance;

    private boolean lockdown;

    /**
     * Constructor
     *
     * @param transmissionRate number 0-1 rate with which virus spreads
     * @param vaccineRate number 0-1 rate with vaccine influences a chance of
     * getting a disease
     * @param maskEffect number 0-1 rate with vaccine influences a chance of
     * getting and spreading a disease
     * @param lockDownThreshold number 0-1 threshold after which lockdown should
     * be introduced
     * @param locationsVisitedWhileSick number of locations an agent should
     * visit until it stops being ill
     * @param vaccinateChance chance that a newly created agent will be
     * vaccinated
     */
    public WorldData(double transmissionRate, double vaccineRate, double maskEffect, double lockDownThreshold, int locationsVisitedWhileSick, double vaccinateChance) {
        this.transmissionRate = transmissionRate;
        this.vaccineRate = vaccineRate;
        this.maskEffect = maskEffect;
        this.lockDownThreshold = lockDownThreshold;
        this.locationsVisitedWhileSick = locationsVisitedWhileSick;
        this.vaccinateChance = vaccinateChance;
    }

    /**
     *
     * Returns number of people in simulation
     *
     * @return
     */
    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    /**
     *
     * Returns number of sick people in simulation
     *
     * @return
     */
    public int getNumberOfSickPeople() {
        return numberOfSickPeople;
    }

    /**
     *
     * returns transmission rate
     *
     * @return
     */
    public double getTransmissionRate() {
        return transmissionRate;
    }

    /**
     *
     * returns vaccine rate
     *
     * @return
     */
    public double getVaccineRate() {
        return vaccineRate;
    }

    /**
     *
     * Returns mask effect
     * @return
     */
    public double getMaskEffect() {
        return maskEffect;
    }

    /**
     *
     * Returns lockdown threshold
     * 
     * @return
     */
    public double getLockDownThreshold() {
        return lockDownThreshold;
    }

    /**
     *
     * Returns 
     * @return
     */
    public int getLocationsVisitedWhileSick() {
        return locationsVisitedWhileSick;
    }

    /**
     *
     * Returns vaccination chance
     * @return
     */
    public double getVaccinateChance() {
        return vaccinateChance;
    }

    /**
     *
     * tells if there is a lockdown
     * 
     * @return
     */
    public boolean isLockdown() {
        return lockdown;
    }

    /**
     *
     * sets number of people
     * 
     * @param numberOfPeople
     */
    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
        updateLockdown();
    }

    /**
     *
     * sets number of sick people
     * 
     * @param numberOfSickPeople
     */
    public void setNumberOfSickPeople(int numberOfSickPeople) {
        this.numberOfSickPeople = numberOfSickPeople;
        updateLockdown();
    }

    /**
     *
     * sets transmission rate
     * 
     * @param transmissionRate
     */
    public void setTransmissionRate(double transmissionRate) {
        this.transmissionRate = transmissionRate;
    }

    /**
     *
     * sets vaccinate rate
     * 
     * @param vaccineRate
     */
    public void setVaccineRate(double vaccineRate) {
        this.vaccineRate = vaccineRate;
    }

    /**
     *
     * sets mask effect
     * 
     * @param maskEffect
     */
    public void setMaskEffect(double maskEffect) {
        this.maskEffect = maskEffect;
    }

    /**
     *
     * sets lockdown threshold
     * 
     * @param lockDownThreshold
     */
    public void setLockDownThreshold(double lockDownThreshold) {
        this.lockDownThreshold = lockDownThreshold;
        updateLockdown();
    }

    /**
     *
     * sets shop visited while seek
     * 
     * @param shopVisitedWhileSick
     */
    public void setShopVisitedWhileSick(int shopVisitedWhileSick) {
        this.locationsVisitedWhileSick = shopVisitedWhileSick;
    }

    /**
     *
     * sets vaccination chance
     * 
     * @param vaccinateChance
     */
    public void setVaccinateChance(double vaccinateChance) {
        this.vaccinateChance = vaccinateChance;
    }

    /**
     *
     * returns a percentage of sick people
     * 
     * @return
     */
    public double getSickPercentage() {
        if (this.numberOfPeople == 0) {
            return 0;
        }
        return (double) this.numberOfSickPeople / (double) this.numberOfPeople;
    }

    private void updateLockdown() {
        if (getSickPercentage() > lockDownThreshold) {
            this.lockdown = true;
        } else {
            this.lockdown = false;
        }
    }

}
