
public class Tarea {
	
	public Integer id;
	public String mensaje;
	public int resultados;
	
	public Tarea (Integer id, String mensaje) {
		this.id = id;
		this.mensaje = mensaje;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getMensaje() {
		return this.mensaje;
	}

}
