package es.urjc.pc;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

import es.urjc.etsii.code.concurrency.SimpleSemaphore; 

/*
 * Se pide implementar un programa concurrente con las siguientes características: 
 *   El programa tendrá 4 procesos que escriben una única letra indefinidamente. Un proceso escribirá la letra A, otra la B, otra la C y otro la D. 
 *   Cada proceso escribe su letra y espera a que los demás hayan escrito también su letra para poder continuar. 
 *   Uno de los procesos tiene que escribir un guión – después de que todos hayan escrito su letra y antes de que empiecen a escribirlas de nuevo. 
 *   La implementación se realizará con semáforos. La salida por pantalla del programa con 4 procesos sería: 
 *         ACDB-BACD-DCBA-ABCD-BCAD … 
 */


public class Ejercicio15 {
    private static char [] letras = {'A', 'B', 'C', 'D', '-'}; 
    private static final int N_HILOS = 5; 
    private static SimpleSemaphore semaforo = new SimpleSemaphore (0); 

    public static void imprimeLetra(int n){
        System.out.println(letras[n]);
    }

    public static void imprimirLetras(int nHilo){
        imprimeLetra(nHilo);

        if (nHilo<N_HILOS-1){
            semaforo.acquire(); 
        } else {
            for(int i =0; i<N_HILOS; i++){
                semaforo.release(); 
            }
        }
    }

    public static void main(String[] args) {
        for(int i =0; i<N_HILOS; i++){
            createThread("imprimirLetras", i); 
        }
        startThreadsAndWait(); 
    }
}
