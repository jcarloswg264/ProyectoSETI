import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

public class Voluntario implements Runnable {
	
	private String mensaje;
	private int id;
	private Tarea tarea;
	
	public Voluntario (Tarea tarea){
		this.tarea = tarea;
		
	}

	@Override
	public void run() {
		this.mensaje = this.tarea.getMensaje();
		this.id = this.tarea.getID();
		
		Analizador analizador = new Analizador(5, 20);
		int resultado = analizador.analizar(this.mensaje);		
	}
	

}
