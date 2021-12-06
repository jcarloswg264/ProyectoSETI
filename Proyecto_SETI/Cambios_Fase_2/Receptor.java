import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @brief Deciden si la tarea ha sido procesada con éxito, o bien se ha producido un error de cálculo en algún voluntario. Para ello, comparan los
    resultados enviados por los voluntarios: si los 3 resultados son idénticos, la tarea ha finalizado con éxito, y así se refleja en el fichero de resultados; si
    algún resultado es diferente, esta tarea ha fallado, así que se debe reintentar obtener un resultado correcto creando una nueva tarea (que contenga el dato
    que no se procesó correctamente), y depositándola en la CTE. Las tareas en proceso se van eliminando de la TTP cuando finaliza su procesamiento.
 */
public class Receptor implements Runnable{    
   
    private BlockingQueue<Resultado> colaResultados;
    private Map<Integer, Integer> tareasEnProceso;
    private PrintWriter archivo_resultado;
    private AtomicInteger id_buscar;
    private AtomicInteger resultado_buscar;
    private int tareas_activas;
    private Lock cerrojo;
    private Condition lleno;
    
    /**
     * 
     * @param colaResultados
     */
    public Receptor(BlockingQueue<Resultado> colaResultados, PrintWriter archivo_resultado,Map<AtomicInteger, Integer> tareasEnProceso2, int tareas_activas, Lock cerrojo, Condition lleno){
        this.colaResultados = colaResultados;
        this.archivo_resultado = archivo_resultado;
        this.tareasEnProceso = tareasEnProceso2;
        this.tareas_activas = tareas_activas;
        this.cerrojo = cerrojo;
        this.lleno = lleno;
        


    }

    
    /**
     * @Override
     * @brief Para que una tarea se correcta todos los voluntarios han de estar de acuerdo
     */
    public void run() {

        while(true){

            Resultado resultado = colaResultados.take();
            if(tareasEnProceso.containsKey(resultado.getID())){
                //se modifica en el mapa el resultado asociado a ese identificador
                tareasEnProceso.replace(resultado.getID(), resultado.getResultado());                     
            }
                
            ///¿¿Como valoramos tarea?//


            ////////REGION CRITICA EXCLUSION MUTUA/////
            //Como hemos comprobao que la tarea es correcta decimos que puede haber una nueva tarea en el sistema.
            cerrojo.lock();
            try{
                tareas_activas--; //aviso a todos los hilos que hay sitio para poder seguir encolando tareas;
                    lleno.signalAll();                    
            }finally{
                cerrojo.unlock();
            }
            ////////FIN REGION CRITICA/////

                
            ////CONCURRENCIA////
            synchronized(archivo_resultado){
                archivo_resultado.println("El resultado es: " + resultado.getResultado() + "ID: " + resultado.getID());
                archivo_resultado.flush();
            }
        }   

}

