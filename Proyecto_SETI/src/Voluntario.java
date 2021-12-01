import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

public class Voluntario implements Runnable {
	private BlockingQueue<Tarea> colaResultados;
	private String mensaje;
	private int id;
	//private Tarea tarea;
	
	public Voluntario (Tarea tarea, BlockingQueue<Tarea> colaResultados){
		//this.tarea = tarea;

		this.mensaje = tarea.getMensaje();
		this.id = tarea.getID();
		this.colaResultados = colaResultados;
	}

	@Override
	public void run() {
		Analizador analizador = new Analizador(5, 20);
		int aux = analizador.analizar(this.mensaje);
		Resultado resultado = new Resultado(this.id,aux);
	}
	

}
