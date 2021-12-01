
/**
 * @brief Contiene, al menos, el resultado calculado por un voluntario y el
          identificador de la tarea a la que corresponde.* 
*/
public class Resultado {
	
	private int id_Tarea;
	private int resultado;
	

    /**
     *  Contiene, al menos, el resultado calculado por un voluntario y el
         identificador de la tarea a la que corresponde.
     * @param id_Tarea
     * @param resultado
     */
	public Resultado (int id_Tarea, int resultado) {
		this.id_Tarea = id_Tarea;
		this.resultado = resultado;
	}
	
    /**
     * Devuelve el ID de una tarea
     * @return int
     */
	public int getID() {
		return this.id_Tarea;
	}
	
     /**
     * Devuelve el RESULTADO de una tarea
     * @return int
     */
	public int getResultado() {
		return this.resultado;
	}

}
