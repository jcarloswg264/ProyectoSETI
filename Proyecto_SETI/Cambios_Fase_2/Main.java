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
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Map<AtomicInteger, Integer> tareasEnProceso =new HashMap<AtomicInteger, Integer>();
		AtomicInteger contador_id = new AtomicInteger(0);
		int tareas_activas = 0;
		Lock cerrojo = new ReentrantLock();
		Condition lleno = cerrojo.newCondition();
			

		///REOGER ARGUMENTOS//
		static final String nombre_fichero_entrada = args[0];
		static final String nombre_fichero_resultados = args[1];
		int numVoluntarios = Integer.parseInt(args[2]);
		int max_tareas = Integer.parseInt(args[3]);	

		System.out.println(	"---ARGUMENTOS RECOGIDOS----");
		System.out.println("NOMBRE FICHERO ENTRADA: " + nombre_fichero_entrada);
		System.out.println("NOMBRE FICHERO RESULTADOS: " + nombre_fichero_resultados);
		System.out.println("MAXIMO HILOS VOLUNTARIOS: " + numVoluntarios);
		System.out.println("MAXIMO TAREAS A ENCOLAR: " + max_tareas);

		////FIN RECOGER ARGUMENTOS////////

		if(nombre_fichero_entrada.length() == 0 || nombre_fichero_resultados.length() == 0){
			System.exit(-1);
			System.out.println("No se han introducido parmetros de entrada");
		}

		FileReader archivoEntrada  = new FileReader(nombre_fichero_entrada);
		if(archivoEntrada.nullReader()){
			System.exit(-1);
		}else{
			System.out.println("El archivo de entrada es correcto");
		}

		BufferedReader archivoLectura = new BufferedReader(nombre_fichero_entrada);
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(nombre_fichero_resultados)));

		BlockingQueue<Tarea> colaTareasEntrantes;
		colaTareasEntrantes = new ArrayBlockingQueue<Tarea>(5);

		BlockingQueue<Resultado> colaResultados;
		colaResultados = new ArrayBlockingQueue<Resultado>(5);


		Generador generador = new Generador(colaTareasEntrantes, contador_id, archivoLectura, tareas_activas, max_tareas, cerrojo, lleno);
		Thread hilo_Generador = new Thread(generador);
		hilo_Generador.start();
		
		Distribuidor distribuidor = new Distribuidor(colaTareasEntrantes,colaResultados,contador_id,tareasEnProceso,numVoluntarios);
		Thread hilo_Distribuidor = new Thread(distribuidor);
		hilo_Distribuidor.start();
		
		Receptor receptor = new Receptor(colaResultados,printWriter,tareasEnProceso, tareas_activas, cerrojo,lleno);
		Thread hilo_receptor = new Thread(receptor);
		hilo_receptor.start();

					
		
		
	}

}
