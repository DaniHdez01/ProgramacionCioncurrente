package es.urjc.pc;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

import java.util.concurrent.Semaphore; 

public class Ejercicio13 {   
    private static final int NUM_TRENES = 5;   
    private static Semaphore semaforoTramoA; 
    private static Semaphore semaforoTramoB = new Semaphore(1); 
    private static Semaphore semaforoTramoC = new Semaphore(1); 

    public static void tren(int numTren) {        
        try{
            sleepRandom(500);   
            semaforoTramoA.acquire();
            recorrerTramoA(numTren);   
        
            semaforoTramoB.acquire();
            semaforoTramoA.release();
            sleepRandom(500);   
            recorrerTramoB(numTren); 
            semaforoTramoC.acquire();
            semaforoTramoB.release();     
            sleepRandom(500);   
            recorrerTramoC(numTren); 
        
            semaforoTramoC.release();
        } catch (InterruptedException e){

        }
    }   
    
    private static void recorrerTramoA(int numTren) {   
        printlnI("Entra TA T" + numTren);   
        sleepRandom(500);   


        printlnI("Sale TA T" + numTren);  
        semaforoTramoA.release();
    
    }
    private static void recorrerTramoB(int numTren) { 
        printlnI("Entra TB T" + numTren); 
        sleepRandom(500); 
        printlnI("Sale TB T" + numTren); 
    } 
    private static void recorrerTramoC(int numTren) { 
        printlnI("Entra TC T" + numTren); 
        sleepRandom(500); 
        printlnI("Sale TC T" + numTren); 
    } 
    public static void main(String args[]) { 
        semaforoTramoA = new Semaphore(1); 
        semaforoTramoB = new Semaphore(1); 
        semaforoTramoC = new Semaphore(1); 
        for (int i = 0; i < NUM_TRENES; i++) { 
            createThread("tren", i); 
        } 
        startThreadsAndWait(); 
    } 
} 
    
    
