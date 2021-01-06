/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author kacper
 */
public class RetailShop extends Shop{
    private int clientCapacity;
    private int expiredSalePeriod;
    private Date lastExpiredDate;
    private LinkedList<Supplier> supplierQue;
    private LinkedList<Client> clientQue;

    public RetailShop(int clientCapacity, int expiredSalePeriod, Date lastExpiredDate, String name, String address, int maxClients, int maxProducts, double xPos, double yPos, VisibleComponent visibleComponent) {
        super(name, address, maxClients, maxProducts, xPos, yPos, visibleComponent);
        this.clientCapacity = clientCapacity;
        this.expiredSalePeriod = expiredSalePeriod;
        this.lastExpiredDate = lastExpiredDate;
        this.supplierQue = new LinkedList<Supplier>();
        this.clientQue = new LinkedList<Client>();
    }
    
    public void start(){
    
    }
    
    public void update(){
    
    }

    public int getClientCapacity() {
        return clientCapacity;
    }
    
    
    public void addClientToQue(Client client){
    
    }
    
    public void addSuplierToQue(Supplier supplier){
    
    }
    
   private void makeSale(){
   
   }
   
   private void removeExpiredProducts(){
   
   }

    @Override
    public String toString() {
        return "RetailShop{" + "clientCapacity=" + clientCapacity + ", expiredSalePeriod=" + expiredSalePeriod + ", lastExpiredDate=" + lastExpiredDate + '}';
    }
   
   
   
}
