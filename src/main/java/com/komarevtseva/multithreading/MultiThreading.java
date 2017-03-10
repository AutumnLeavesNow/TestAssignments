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
public class MultiThreading {
    public static void main(String[] args){
        // TODO code application logic here
        ArrayList<String> sharedList= new ArrayList<> ();
        WritingThread mt1 = new WritingThread("numberInput", sharedList);
        mt1.start();
        try {
            Thread.sleep(5000);
        }catch (InterruptedException ex) {
                //Logger.getLogger(WritingThread.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Прерывание основного потока");
            }   
        LogicThread mt2 = new LogicThread("numberOutput", sharedList);
        mt2.start();
        while (mt1.isAlive());
        mt2.stop();
        System.out.println("End of input");
        
    }
    
}
