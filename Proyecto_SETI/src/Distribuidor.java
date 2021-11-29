import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;


public class Distribuidor implements Runnable{	
		
		private BlockingQueue<Tarea> cola;
		private int identificador;
		private PrintWriter printWriter;	
		
		public void Consumidor(BlockingQueue<Tarea> cola, int identificador, PrintWriter printWriter) {
			this.cola = cola;
			this.identificador = identificador;
			this.printWriter = printWriter;
		}
		
		@Override
		public void run() {
			while(true) {
				try {
					Tarea tarea = this.cola.take();//obtiene elemento de la cola
					
					Voluntario voluntario = new Voluntario(tarea);
					Thread hilo_voluntario = new Thread(voluntario);
					hilo_voluntario.start();
				
					
					this.printWriter.println(mensaje);
					printWriter.flush();
					
				}catch (Exception e) {
					e.printStackTrace();
				}			
			}
			
		}
}
	
	

