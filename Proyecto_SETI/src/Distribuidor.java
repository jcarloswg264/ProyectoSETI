import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.BlockingQueue;


public class Distribuidor implements Runnable{	
		
		private BlockingQueue<Tarea> colaTareas;
		private BlockingQueue<Resultado> colaResultados;
		private int identificador;
		private PrintWriter printWriter;
		private Map<Integer, Tarea> tareasEnProceso;	
		
		public void Consumidor(BlockingQueue<Tarea> colaTareasEntranTareas,BlockingQueue<Resultado> colaResultados, int identificador, Map<Integer, Tarea> tareasEnProceso) {
			this.cola = colaTareasEntranTareas;
			this.identificador = identificador;
			this.printWriter = printWriter;
			this.colaResultados = colaResultados;
			this.tareasEnProceso = tareasEnProceso;
		}
		
		@Override
		public void run() {
			while(true) {
				try {
					Tarea tarea = this.colaTareas.take();//obtiene elemento de la cola
					tareasEnProceso.put(this.identificador, tarea);

					
					Voluntario voluntario = new Voluntario(tarea, this.colaResultados);
					Thread hilo_voluntario = new Thread(voluntario);
					hilo_voluntario.start();					
					
				}catch (Exception e) {
					e.printStackTrace();
				}			
			}
			
		}
}
	
	

