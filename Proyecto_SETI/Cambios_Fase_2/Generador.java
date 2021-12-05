import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

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
	
	
	/**
	 * Lee lienas del fichero radiotelescopio.Por cada l�nea leida,
	 *  crear� una tarea nueva que a�adir� a la CTE.
	 *  Terminar� su ejecucia�n cuando no queden mas l�enas por leer
	 * @param colaCompartida
	 * @param id
	 * @param bufferLectura
	 */
	public Generador(BlockingQueue<Tarea> colaCompartida, AtomicInteger id, BufferedReader bufferLectura) {
		this.cola = colaCompartida;
		this.id = id;
		this.bufferLectura = bufferLectura;
		
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
			

				if(mensaje != null){
					Tarea tarea = new Tarea(this.id.incrementAndGet(), this.mensaje); ///CONCURRENCIA. Bloquea la CPU para incrementar el identificador de la tarea;
					try{
						this.cola.put(tarea); //CONCURRENCIA?¿? ---> PueDe haber concurrencia, pero la clase BlockingQueue soluciona esto 			
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
														
				}	
			}

		}

}
