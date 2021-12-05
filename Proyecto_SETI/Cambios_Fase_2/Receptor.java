import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

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
    
    /**
     * 
     * @param colaResultados
     */
    public Receptor(BlockingQueue<Resultado> colaResultados, PrintWriter archivo_resultado,Map<Integer, Integer> tareasEnProceso){
        this.colaResultados = colaResultados;
        this.archivo_resultado = archivo_resultado;
        this.tareasEnProceso = tareasEnProceso;
        


    }

    @Override
    public void run() {

        while(true){

            Resultado resultado = colaResultados.take();
                if(tareasEnProceso.containsKey(resultado.getID())){
                   if(tareasEnProceso.get((resultado)==0)){
                       tareasEnProceso.replace(resultado.getID(), resultado.getID());
                   }else{
                        if(tareasEnProceso.get((resultado) != resultado.getResultado())){
                            tareasEnProceso.remove(resultado.getID());
                        }   
                   }
                    
                }
        }   

}

