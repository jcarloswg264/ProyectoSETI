
/**
 * @NAME ENTRENAMIENTO 4
 * 
 * 
 * sobre el programa anterior modifique el hilo productor y consumidor para que el hilo que añada a la cola, las obtenga de un fichero.
 * 
 * -Para la lectura de un fichero se utilizará las clases FileReader y BufferReader
 * -Para la escritura de un archivo se urilizará PrintWriter 
 */

package entrenamiento4;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Principal {

	// nombre de los archivos. Poner los ficheros dentro la raiz del pryecto
	static final String fichero_entrada = "Fichero_entrada.txt";
	static final String fichero_salida = "fichero_salida.txt";

	public static void main(String args[]) {

		FileReader fich_entrada = new FileReader(fichero_entrada); // en la practica será argv[n]
		BufferedReader bufferLec = new BufferedReader(fichero_entrada);
		PrintWriter pritnwriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fichero_salida))); // Crea
																													// fichero
																													// y
																													// escirbe
																													// en
																													// él

		BlockingQueue<String> cadena_compartida;
		cadena_compartida = new ArrayBlockingQueue<String>(3);

		// creamos productores
		for (int i = 1; i < 2; i++) { // Ahora solo solo un productor
			Productor objeto_productor = new Productor(cadena_compartida, i, bufferLec);
			Thread hilo_productor = new Thread(objeto_productor);
			hilo_productor.start();
		}

		for (int i = 1; i < 2; i++) {
			Consumidor objeto_consumidor = new Consumidor(cadena_compartida, i, pritnwriter);
			Thread hilo_consumidor = new Thread(objeto_consumidor);
			hilo_consumidor.start();
		}
	}

}
