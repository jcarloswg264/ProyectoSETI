package entrenamiento3;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@SuppressWarnings("unused")
public class Productor implements Runnable{
	
	private BlockingQueue<String> cola;
	private int identificador;
	private BufferedReader bufferLec;
	
	
	
	
	public Productor(BlockingQueue<String> cola_compartida, int identificador, BufferedReader bufferLec) {
		this.cola = cola_compartida;
		this.identificador = identificador;
		this.bufferLec = bufferLec;
	}
	
	@Override
	public void run() {
		
		try {
			while(this.bufferLec.ready()) {
				try {
					this.cola.put("Prodcutor inserta en la cola el siguiente mensaje: " +bufferLec.readLine());
				} catch (InterruptedException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	private void cola(String string) {
		// TODO Auto-generated method stub
		
	}

}
	
	


