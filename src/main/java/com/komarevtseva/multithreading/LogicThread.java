/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.komarevtseva.multithreading;

import java.util.ArrayList;

/**
 *
 * @author Victoria
 */
public class LogicThread implements Runnable{
    private final Thread thrd;
    private final ArrayList<String> sharedList;
    private boolean checkLoop = false;
    LogicThread (String name, ArrayList<String> shrdList) {
        thrd = new Thread(this, name);
        sharedList = shrdList;  
    }
    public void start (){
        thrd.start();
    }
    public void stop(){
        checkLoop = true;
    }
    @Override
    public void run () {
        while(!checkLoop ){
            if (!sharedList.isEmpty()){
                try {
                    synchronized(sharedList){
                        String min = sharedList.get(0);
                        Short minimal = Translator.toNum(min);
                        for (String item : sharedList){
                            if (Translator.compStrings(item, minimal)){
                                min = item;
                                minimal = Translator.toNum(min);
                            }
                        }
                        System.out.println("Removing " + min);
                        sharedList.remove(min);
                    }
                    Thread.sleep(5000);
                }catch (InterruptedException ex) {
                //Logger.getLogger(WritingThread.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Прерывание основного потока");
                }    
            }
            else {
                try {
                    Thread.sleep(5000);
                }catch (InterruptedException ex) {
                    //Logger.getLogger(WritingThread.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Прерывание основного потока");
                    }
            }
            
            
        }
        
    }
}
