package es.urjc.pc; 
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;
public class Ej13ApB{
    private static final int NUM_TRENES = 5;   
    private static final int NUM_TRAMOS = 7; 
    private static SimpleSemaphore [] semaforoTramos; 


    public static void tren(int numTren) {        

        for(int i =0; i<NUM_TRAMOS; i++){
            sleepRandom(500);
            recorrerTramo(numTren, semaforoTramos[i], i); 
            sleepRandom(500);
        }


    }   
    
    private static void recorrerTramo(int numTren, SimpleSemaphore semaforoTramo, int nTramo) {   
        semaforoTramo.acquire();
        System.out.println("Tren numero: "+numTren + "Entrando al tramo: "+nTramo);
        semaforoTramo.release();
    }
   
    public static void main(String args[]) { 
        for(int i = 0; i<NUM_TRAMOS; i++){
            semaforoTramos[i] = new SimpleSemaphore(1); 
        }
        for (int i = 0; i < NUM_TRENES; i++) { 
            createThread("tren", i); 
        } 
        startThreadsAndWait(); 
    } 


}
    
    

