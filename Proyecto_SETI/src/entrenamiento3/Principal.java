package entrenamiento3;
//Entrenamiento1

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Principal {
	
	public static void main (String args[]) {
	
	BlockingQueue<String> cadena_compartida;
	cadena_compartida = new ArrayBlockingQueue<String>(3);	
				
			//creamos productores
			for(int i = 1; i <2; i++) {
				Productor objeto_productor = new Productor(cadena_compartida, i);
				Thread hilo_productor = new Thread(objeto_productor);
				hilo_productor.start();
			}
			
			for(int i = 1; i < 2; i++) {
				Consumidor objeto_consumidor = new Consumidor(cadena_compartida, i);
				Thread hilo_consumidor = new Thread(objeto_consumidor);
				hilo_consumidor.start();			
			}		
	}

}
