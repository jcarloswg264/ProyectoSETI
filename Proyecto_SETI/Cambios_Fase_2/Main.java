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
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AtomicInteger contador_id = new AtomicInteger(0);
		

		static final String nombre_fichero_entrada;
		static final String nombre_fichero_resultados;
		
		Map<AtomicInteger, Integer> tareasEnProceso =new HashMap<AtomicInteger, Integer>();

		nombre_fichero_entrada = args[0];
		nombre_fichero_resultados = args[1];
		int numVoluntarios = Integer.parseInt(args[2]);

		if(nombre_fichero_entrada.length() == 0 || nombre_fichero_resultados.length() == 0){
			System.exit(-1);
			System.out.println("No se han introducido parmetros de entrada");
		}

		FileReader archivoEntrada  = new FileReader(nombre_fichero_entrada);
		BufferedReader archivoLectura = new BufferedReader(nombre_fichero_entrada);
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(nombre_fichero_resultados)));

		BlockingQueue<Tarea> colaTareasEntrantes;
		colaTareasEntrantes = new ArrayBlockingQueue<Tarea>(5);

		BlockingQueue<Resultado> colaResultados;
		colaResultados = new ArrayBlockingQueue<Resultado>(5);


		Generador generador = new Generador(colaTareasEntrantes, contador_id, archivoLectura);
		Thread hilo_Generador = new Thread(generador);
		hilo_Generador.start();
		contador_id ++;

		Distribuidor distribuidor = new Distribuidor(colaTareasEntrantes,colaResultados,contador_id,tareasEnProceso,numVoluntarios);
		Thread hilo_Distribuidor = new Thread(distribuidor);
		hilo_Distribuidor.start();
		contador_id++;

		Receptor receptor = new Receptor(colaResultados,printWriter,tareasEnProceso);
		Thread hilo_receptor = new Thread(receptor);
		hilo_receptor.start();

					
		
		
	}

}
