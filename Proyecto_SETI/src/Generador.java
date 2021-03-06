import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

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
	private int id;
	private BufferedReader bufferLectura;
	private int id_tarea;
	private int i;
	
	/**
	 * Lee lienas del fichero radiotelescopio.Por cada l�nea leida,
	 *  crear� una tarea nueva que a�adir� a la CTE.
	 *  Terminar� su ejecucia�n cuando no queden mas l�enas por leer
	 * @param colaCompartida
	 * @param id
	 * @param bufferLectura
	 */
	public Generador(BlockingQueue<Tarea> colaCompartida, int id, BufferedReader bufferLectura) {
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
				String mensaje = bufferLectura.readLine();
				if(mensaje != null){
					Tarea tarea = new Tarea(this.id_tarea, mensaje);
					this.cola.put(tarea);
					this.id_tarea++;
				}	
			}
		}catch(IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	

}
