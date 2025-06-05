package es.urjc.pc; 

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*; 

//Ejercicio de introduccion a la programación concurrente
public class Ejercicio1 {

    private static float numRand; 
    /*  Para hacerlo infinito como requiere el ejercicio 2 solo hay que añadir una condicion booleana
        que bloquee al otro hilo cuando uno esté accediendo a la variable y ambas funciones meterlos en un bucle infinito
    */
    private static boolean consumir; 

    public static void productor(){
        while(true){
            //Da valor a la variable 
            numRand = (float) Math.random();
            //Desbloquea al consumidor  
            consumir = true; 
        }
    }

    public static void consumidor(){
        while(true){
            //Queda a la espera de que el productor desbloquee al consumidor 
            while(!consumir); 
            //Imprie el valor por pantalla 
            System.out.println(numRand); 
        }
    }

    public static void main(String[] args) {
        consumir = false; 
        createThread("productor");
        createThread("consumidor"); 

        startThreadsAndWait();
    }
}
