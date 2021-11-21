package clasesFran;
//Entrenamiento1

import java.util.ArrayList;

public class Principal {
	
	public static void main (String args[]) {
	
		
		ArrayList<String> cadenaCompartida;
		cadenaCompartida = new ArrayList<String>();
		
		
		
		for(int i = 0; i < 9; i++) {		
			//Crear N hilos
			//1º Instanciar un objeto de la clase que quiero que actue como hilo
				Hilo objeto_hilo = new Hilo(i, cadenaCompartida);
			//Instanciamos un objeto Thread de la clase que quiero que actue como HILO
				Thread hilo_real = new Thread(objeto_hilo);
			//Lanzamos el Hilo
				hilo_real.start(); //Esta forma ejecuta el hilo de manera concurrente
				//hilo_real.run(); //Esta forma crea  hilos de forma secuuencial sin concurrencia
		}		
	}
	

}
