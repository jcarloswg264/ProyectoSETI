package entrenamiento3;
import java.util.concurrent.BlockingQueue;

public class Consumidor implements Runnable{
	
	private BlockingQueue<String> cola;
	private int identificador;
	
	
	
	
	public Consumidor(BlockingQueue<String> cola, int identificador) {
		this.cola = cola;
		this.identificador = identificador;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				String mensaje = this.cola.take();	//obtiene elemento de la cola
				this.cola("soy el CONSUMIDOR: " + this.identificador + "| El mensaje es:: " + mensaje);
				
			}catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
	}

	private void cola(String string) {
		
	}


}