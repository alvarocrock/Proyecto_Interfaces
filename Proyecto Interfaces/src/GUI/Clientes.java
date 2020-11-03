package GUI;

import java.sql.Date;

public class Clientes {
	// estados
	private String DNI;
	private String nombre;
	private String apellido;
	private String direccion;
	private String provincia;
	private String poblacion;
	private Date fecha_alta;
	private String idCli;
	
	
	//Comportamientos
	/**
	 * Constructor
	 * @param nombre
	 * @param tlf
	 * @param apellido
	 * @param direccion
	 * @param provincia
	 * @param poblacion
	 * @param fecha_alta
	 * @param idCli
	 * @param dNI
	 */
	public Clientes(String dNI, String nombre, String apellido, String direccion, String provincia, String poblacion,
			Date fecha_alta, String idCli) {
		
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.provincia = provincia;
		this.poblacion = poblacion;
		this.fecha_alta = fecha_alta;
		this.idCli = idCli;
		DNI = dNI;
	}
	
	//Getters
	public String getDNI() {
		return DNI;
	}
	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public String getProvincia() {
		return provincia;
	}
	public String getPoblacion() {
		return poblacion;
	}
	public Date getFecha_alta() {
		return fecha_alta;
	}
	public String getIdCli() {
		return idCli;
	}

	
}
