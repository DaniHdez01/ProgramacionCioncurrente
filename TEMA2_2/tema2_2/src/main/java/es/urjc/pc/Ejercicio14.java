package es.urjc.pc; 

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

import es.urjc.etsii.code.concurrency.SimpleSemaphore; 

public class Ejercicio14 {
    private static int N_PROCESOS = 5;
    private static int llegados = 0; 
    private static SimpleSemaphore semaforo = new SimpleSemaphore(0); 
    //ACUERDATE SIEMPRE de hacer estaticas todas las funciones y variables compartidas 


    public static void imprimirLetras(){
        println("A"); //Imprime primero la A
         llegados++; //Contanmos uno que ha llegado
         //IMPORTANTE: Sumarlos antes de comprobarlos que si no sse produce un interbloqueo(Se quedan lso procesos bloqueados)
        if(llegados<N_PROCESOS){ //Si no han llegado todos
            llegados++; //Contanmos uno que ha llegado
            semaforo.acquire(); //Bloqueamos este proceso y que espere 
        } 
        else{ //Si han llegado todos 
            for(int i =0; i<N_PROCESOS; i++){
                semaforo.release(); //El ultimo proceso es el encargado de desbloquear a todos 
            }
        }
        println("B"); 

        

    }

    public static void main(String[] args) {
        createThreads(N_PROCESOS,"imprimirLetras");
        startThreadsAndWait(); 
    }

        


}
