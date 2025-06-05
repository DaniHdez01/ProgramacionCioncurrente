package es.urjc;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import es.urjc.etsii.code.concurrency.SimpleSemaphore;
/*
 * a) Se quiere implementar una aplicación para descargar ficheros. 
 * La aplicación debe tener la capacidad de descargar un único fichero, 
 * pero debe ser capaz de descargar varios fragmentos del fichero de manera concurrente para aprovechar de forma más eficiente la red. 
 * Para simplificar la aplicación, consideramos que un fichero se representa en memoria como un array de enteros. Internamente, 
 * la aplicación dispone de una serie de procesos que van descargando los diferentes fragmentos del fichero (posiciones del array). 
 * 
 * Los procesos están ejecutando tres acciones: primero se determina el siguiente fragmento a descargar, 
 * a continuación se descarga ese fragmento, y por último se guarda el fragmento descargado en el array que representa el fichero.  
 * La solución se puede implementar con espera activa o con semáforos. 
 * La descarga de los distintos fragmentos se simulará con el método: 
 * 
 */
public class Ejercicio9 {
    
    private static final int N_FRAGMENTOS = 10; 
    private static final int N_HILOS = 3; 
    private static int nFragmento = 0; 
    private static volatile int[] fichero = new int[N_FRAGMENTOS]; 
    /*Para este ejercicio se produce una zona critica donde puede haber condicion de carrera: 
     *  1. En primer lugar, cada nodo tiene que saber qué fragemnto le toca por lo que hay que utilizar 
     *     un semaforo para calcular el indice de cada nodo
     * 
     *  SIN EMBARGO, como cada hilo escribe en una posicion unica, no hay que proteger la escritura 
     *  en el array 
     */
    private static SimpleSemaphore controladorData = new SimpleSemaphore(1);


    private static int descargaDatos(int numFragmento) { 
        sleepRandom(1000); 
        return numFragmento * 2; 
    } 

    private static void mostrarFichero() { 
        println("--------------------------------------------------"); 
        print("File = ["); 
        for (int i = 0; i < N_FRAGMENTOS; i++) { 
            print(fichero[i] + ","); 
        } 
        println("]"); 
    } 

    public static void downloader() { 

        // Mientras hay fragmentos que descargar...   
       
            while(nFragmento<N_FRAGMENTOS){
                controladorData.acquire(); 
                fragAct= nFragmento; 
                nFragmento++; 
                controladorData.release(); 
                //Descargar los datos del siguiente fragmento    
                int dato = descargaDatos(fragAct); 
                //Almacenar los datos en el array
                fichero[fragAct] = dato; 
                
            }
            
        }   
        
    

    public static void main(String[] args) {
         createThreads(N_HILOS, "downloader"); 
         startThreadsAndWait(); 
         mostrarFichero(); 
        
        } 
}
