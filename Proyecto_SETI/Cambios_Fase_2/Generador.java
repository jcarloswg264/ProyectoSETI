import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Lee lienas del fichero radiotelescopio.Por cada l�nea leida,
 *  crear� una tarea nueva que a�adir� a la CTE.
 *  Terminar� su ejecucia�n cuando no queden mas l�enas por leer
 * @param colaCompartida
 * @param id
 * @param bufferLectura
 */
public class Generador implements Runnable{

	private BlockingQueue<Tarea> cola;
	private AtomicInteger id;
	private BufferedReader bufferLectura;
	private String mensaje;
	private int tareas_activas;
	private int max_tareas;
	Lock cerrojo;
	Condition lleno;
	
	
	/**
	 * Lee lienas del fichero radiotelescopio.Por cada l�nea leida,
	 *  crear� una tarea nueva que a�adir� a la CTE.
	 *  Terminar� su ejecucia�n cuando no queden mas l�enas por leer
	 * @param colaCompartida
	 * @param id
	 * @param bufferLectura
	 * @param lleno
	 * @param cerrojo
	 * @param max_tareas
	 * @param tareas_activas
	 */
	public Generador(BlockingQueue<Tarea> colaCompartida, AtomicInteger id, BufferedReader bufferLectura, int tareas_activas, int max_tareas, Lock cerrojo, Condition lleno) {
		this.cola = colaCompartida;
		this.id = id;
		this.bufferLectura = bufferLectura;
		this.cerrojo = cerrojo;
		this.tareas_activas = tareas_activas;
		this.max_tareas = max_tareas;
		this.lleno = lleno;
		
		System.out.println("Se ha creado el hilo generador corectamente");
		System.out.println("ID = " + this.id);		
	}

	
	
	@Override
	public void run() {
		while(true){
			try {
				synchronized(bufferLectura){ //CONCURRENCIA. BLOQUEA LA CPU 
				this.mensaje = bufferLectura.readLine();
			}catch(IOException e) {
					e.printStackTrace();
				}
			}	
			
			if(mensaje != null){
				Tarea tarea = new Tarea(this.id.incrementAndGet(), this.mensaje); ///CONCURRENCIA. Bloquea la CPU para incrementar el identificador de la tarea;				
			}
			
			
            ////////REGION CRITICA EXCLUSION MUTUA/////
			cerrojo.lock();
			try{
				while(tareas_activas == max_tareas){
					lleno.await();
					System.out.println("La cola de tareas está petada.");
				}
				tareas_activas++;
			}finally{
				cerrojo.unlock();
			}
			//////FIN REGION CRITICA///

			try{
				this.cola.put(tarea); //CONCURRENCIA?¿? ---> PueDe haber concurrencia, pero la clase BlockingQueue soluciona esto 			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			

		}

}
