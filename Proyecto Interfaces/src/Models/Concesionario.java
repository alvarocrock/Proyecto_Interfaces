package Models;

/**
 * clase que almacena un concesionario
 * @author alvar
 *
 */
public class Concesionario {

	String nombre;
	int id;
	
	/**
	 * constructor  de consesiopnario
	 * @param minombre
	 * @param miid
	 */
	public Concesionario(String minombre,int miid) {
		nombre=minombre;
		id=miid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
