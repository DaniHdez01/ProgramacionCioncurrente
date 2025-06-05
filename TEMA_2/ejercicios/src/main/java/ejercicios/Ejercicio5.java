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

public class Ejercicio5 {
    
    
    public static void visitarMuseo(){
        while(true){ //Como el enuncido dice que volvemos a entrar lo metemos en un bucle infinito
            enterMutex(); 
            System.out.println("Hola");
            System.out.println("Que bonito");
            System.out.println("Adios");
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
