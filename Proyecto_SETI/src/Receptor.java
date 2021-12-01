import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

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
    private int id_buscar;
    private int resultado_buscar;
    
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

        Resultado resultado = colaResultados.take();
        this.id_buscar = resultado.getID();
        this.resultado_buscar = resultado.getResultado()


        Resultado resultado1 = colaResultados.take();
        int id1 = resultado1.getID();
        int resul1 = resultado1.getResultado();

        Resultado resultado2 = colaResultados.take();
        int id2 = resultado2.getID();
        int resul2 = resultado2.getResultado();
        

        if(id1 != this.id_buscar){
            tareasEnProceso.put(resultado1.getID(), resultado1.getResultado());   
        }

        if(id2 != this.id_buscar){
            tareasEnProceso.put(resultado2.getID(), resultado2.getResultado());  
        }
        
        if(id1 == this.id_buscar && id2 == this.id_buscar){
            if(this.resultado_buscar == resul1 && resultado_buscar == resul2){
                archivo_resultado.println(this.resultado_buscar);
                
            }
        }                
        
    }    


}

