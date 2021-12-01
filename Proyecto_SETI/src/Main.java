import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int contador_id = 0;

		static final String nombre_fichero_entrada;
		static final String nombre_fichero_resultados;
		
		Map<Integer, Tarea> tareasEnProceso =new HashMap<Integer, Tarea>();

		nombre_fichero_entrada = args[1];
		nombre_fichero_resultados = args[2];

		if(nombre_fichero_entrada.length()==0 || nombre_fichero_resultados.length()==0){
			System.exit(-1);
			System.out.println("No se han introducido parmetros de entrada");
		}

		FileReader archivoEntrada  = new FileReader(nombre_fichero_entrada);
		BufferedReader archivoLectura = new BufferedReader(nombre_fichero_entrada);
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(nombre_fichero_resultados)));

		BlockingQueue<Tarea> colaTareasEntrantes;
		colaTareasEntrantes = new ArrayBlockingQueue<Tarea>(10);

		BlockingQueue<Tarea> colaResultados;
		colaResultados = new ArrayBlockingQueue<Tarea>(10);


		Generador generador = new Generador(colaTareasEntrantes, contador_id);
		Thread hilo_Generador = new Thread(generador);
		hilo_Generador.start();
		contador_id ++;

		Distribuidor distribuidor = new Distribuidor(colaTareasEntrantes,colaResultados,contador_id);
		Thread hilo_Distribuidor = new Thread(distribuidor);
		hilo_Distribuidor.start();
		contador_id++;

					
		
		
	}

}
