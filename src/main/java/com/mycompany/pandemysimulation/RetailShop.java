/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation;

import com.mycompany.pandemysimulation.core.Location;
import com.mycompany.pandemysimulation.core.ThreadAgent;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author kacper
 */
public class RetailShop extends Shop implements Location{
    private int clientCapacity;
    private int expiredSalePeriod;
    private Date lastExpiredDate;
    private LinkedList<Supplier> supplierQue;
    private LinkedList<Client> clientQue;
    
    private Semaphore clientSempahore;
    private Semaphore supplierSemaphore;

    public RetailShop(int clientCapacity, int expiredSalePeriod, Date lastExpiredDate, String name, String address, int maxClients, int maxProducts, int idX, int idY, VisibleComponent visibleComponent) {
        super(name, address, maxClients, maxProducts, idX, idY, visibleComponent);
        this.clientCapacity = clientCapacity;
        this.expiredSalePeriod = expiredSalePeriod;
        this.lastExpiredDate = lastExpiredDate;
        this.supplierQue = new LinkedList<Supplier>();
        this.clientQue = new LinkedList<Client>();
        this.clientSempahore = new Semaphore(maxClients);
        this.supplierSemaphore = new Semaphore(1);
    }
    
    public void start(){
    
    }
    
    public void update(){
    
    }

    @Override
    public void enter(ThreadAgent threadAgent) {
        try{
            if(threadAgent instanceof Client){
                clientSempahore.acquire();
            }else{
                supplierSemaphore.acquire();
            }
        }catch(Exception e){
            System.err.println(e);
        }
    }

    @Override
    public void leave(ThreadAgent threadAgent) {
        if(threadAgent instanceof Client){
                clientSempahore.release();
            }else{
                supplierSemaphore.release();
            }
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
}
