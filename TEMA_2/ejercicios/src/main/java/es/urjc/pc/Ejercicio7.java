package es.urjc.pc;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
/*
 * Existen 3 personas en el mundo, 1 museo, y sólo cabe una persona dentro del museo. 
 * Las personas realizan cuatro acciones dentro del museo: 
 *  Cuando entran al museo saludan: “hola!” 
 *  Cuando ven el museo se sorprenden: “qué bonito!” y “alucinante!” 
 *  Cuando se van del museo se despiden: “adios” 
 *  Cuando salen del museo se van a dar un “paseo” 
 * 
 * 
 */

public class Ejercicio7 {
    /*
     * Modificaciones para el ejercicio 6: Ahora entran infinitas personas y se van contando los que estan dentro del museo 
     */
    /*
     * Modificaciones para el ejercicio 7: Ahora, si una persona es lña primera en entrar obtendrá un regalo
     */
    private static int personas; 
    public static void visitarMuseo(){
        while(true){ //Como el enuncido dice que volvemos a entrar lo metemos en un bucle infinito

            /*
             * Ahora al tener que modificar la variable la seccion critica cambia y ahhora son 2
             * La prinmera al entrar al museo y tener que sumar 1 al numero de personas que hay 
             * La segunda al tener que salir del museo restando 1 al numero total de personas 
             */
            enterMutex(); 
            personas++; 
            System.out.println("Hola, somos: " + personas);
            if(personas ==1){
                System.out.println("Tengo regalo"); 
            }
            exitMutex(); 
            System.out.println("Que bonito!"); // Added exclamation for consistency

            System.out.println("Alucinante!"); // Added missing action

            enterMutex(); 
            personas--; 
            System.out.println("Adios a las " + personas + " personas");
            exitMutex();
        
            System.out.println("Paseo");
        }
    }

    public static void main(String[] args) {
            //IMPORTANTE: Podemos crear tantos hilos como queramos sin usar un bucle for con createThreads e indicando los hilos que querenmos y la funcion que vamos a ejecutar
            createThreads(3, "visitarMuseo"); 
    

        startThreadsAndWait();
    }
    
}