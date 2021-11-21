package entrenamiento4;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

public class Consumidor implements Runnable{
	
	private BlockingQueue<String> cola;
	private int identificador;
	private PrintWriter printWriter;	
	
	public Consumidor(BlockingQueue<String> cola, int identificador, PrintWriter printWriter) {
		this.cola = cola;
		this.identificador = identificador;
		this.printWriter = printWriter;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				String mensaje = this.cola.take();	//obtiene elemento de la cola
				this.printWriter.println(mensaje);
				printWriter.flush();
				
			}catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
	}

	private void cola(String string) {
		
	}


}