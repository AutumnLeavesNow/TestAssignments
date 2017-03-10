/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.komarevtseva.multithreading;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Victoria
 */
public class WritingThread implements Runnable{
    private final Thread thrd;
    private final ArrayList<String> sharedList;
    private boolean checkLoop = false;
    WritingThread (String name, ArrayList<String> shrdList) {
        thrd = new Thread(this, name);
        sharedList = shrdList;  
    }
    public void start (){
        thrd.start();
    }
    public void stop(){
        checkLoop = true;
    }
    public boolean isAlive(){
        return thrd.isAlive();
    }
    @Override
    public void run () {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner("Hai \n maow \n boo \n.bye");
        String local;
        while (!checkLoop){
            try {
                local = scanner.nextLine();
                if (local.equalsIgnoreCase(".bye")){
                    checkLoop = true;
                }
                else{
                    synchronized(sharedList){
                        sharedList.add(local);
                    } 
                }
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                //Logger.getLogger(WritingThread.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Прерывание основного потока");
            }   
        }
        
    }
}
