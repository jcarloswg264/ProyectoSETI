import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * Lee lienas del fichero radiotelescopio.Por cada línea leida,
 *  creará una tarea nueva que añadirá a la CTE.
 *  Terminará su ejecuciaón cuando no queden mas líenas por leer
 * @param colaCompartida
 * @param id
 * @param bufferLectura
 */
public class Generador implements Runnable{

	private BlockingQueue<String> cola;
	private int id;
	private BufferedReader bufferLectura;
	
	/**
	 * Lee lienas del fichero radiotelescopio.Por cada línea leida,
	 *  creará una tarea nueva que añadirá a la CTE.
	 *  Terminará su ejecuciaón cuando no queden mas líenas por leer
	 * @param colaCompartida
	 * @param id
	 * @param bufferLectura
	 */
	public Generador(BlockingQueue<String> colaCompartida, int id, BufferedReader bufferLectura) {
		this.cola = colaCompartida;
		this.id = id;
		this.bufferLectura = bufferLectura;
		
		System.out.println("Se ha creado el hilo generador corectamente");
		System.out.println("ID = " + this.id);		
	}
	
	
	@Override
	public void run() {
		
		try {
			while(this.bufferLectura.ready()) {
				this.cola.put(bufferLectura.readLine());
			}
		}catch(IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
