package es.urjc.pc;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*; 

/*
 * Programa formado por un proceso servidor y otro proceso cliente. 
 * El proceso cliente hace una petición al proceso servidor (en forma de número aleatorio) y espera su respuesta, cuando la recibe, la procesa. 
 * El proceso servidor no hace nada hasta que recibe una petición, momento en el que suma 1 al número enviado en la petición y contesta con ese valor. 
 * El proceso cliente procesa la respuesta mostrándola por pantalla.
 */
public class Ejercicio3 {
    public static boolean peticion; 
    /*Para el ejercicio 4 bastará con meter las instrucciones de ambos threads en un bucle infinito */
    public static void Cliente(){
        while(true){
        System.out.println("Enviando peticion..."); 
        peticion = true; 
        } 
    }
    public static void Servidor(){
        while(true){
            while(!peticion){
                System.out.println("Esperando peticion");
            }
            System.out.println("Petición recibida");
        }
    }
        
    public static void main(String[] args) {
        createThread("Cliente");
        createThread("Servidor"); 
        
        startThreadsAndWait();
    }
}
