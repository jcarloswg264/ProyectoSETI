package clasesFran;

import java.util.ArrayList;

/////Para que una clase pueda actuar como hilo, tiene que implementar la interfaz runnable.
public class Hilo implements Runnable {
	
	private int identificador;
	private ArrayList<String> cadena;
	
	
	public Hilo(int identificador, ArrayList<String> cadena) {
		this.identificador = identificador;
		this.cadena = cadena;
		
	}
	
	//Cunado una clase actua como un hilo, SOLO EJECUTA SU METODO RUN
	public void run() { //Funciona como EXEC()
		
		for(int j = 0; j < 10000; j++) {
			this.cadena.add("Numero" + j);
					
		}
		System.out.println("HILO:" + this.identificador + " El numero de elementos de la cadena es:" + this.cadena.size());		
	}
	
	

}
