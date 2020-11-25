package Models;

public class Presupuesto {
	
	int id;
	int id_cli;
	int id_emple;
	String fecha_ppto;
	String fecha_validez;
	int id_veh;
	float precio;
	String estado;
	
	
	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Presupuesto(int id,int id_cliente,int id_empleado,String fecha_inicio,String fecha_lim,int id_vehiculo,float miprecio,String miestado) {
		this.id=id;
		id_cli=id_cliente;
		id_emple=id_empleado;
		fecha_ppto=fecha_inicio;
		fecha_validez=fecha_lim;
		id_veh=id_vehiculo;
		precio=miprecio;
		estado=miestado;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getId_cli() {
		return id_cli;
	}


	public void setId_cli(int id_cli) {
		this.id_cli = id_cli;
	}


	public int getId_emple() {
		return id_emple;
	}


	public void setId_emple(int id_emple) {
		this.id_emple = id_emple;
	}


	public String getFecha_ppto() {
		return fecha_ppto;
	}


	public void setFecha_ppto(String fecha_ppto) {
		this.fecha_ppto = fecha_ppto;
	}


	public String getFecha_validez() {
		return fecha_validez;
	}


	public void setFecha_validez(String fecha_validez) {
		this.fecha_validez = fecha_validez;
	}


	public int getId_veh() {
		return id_veh;
	}


	public void setId_veh(int id_veh) {
		this.id_veh = id_veh;
	}


	public float getPrecio() {
		return precio;
	}


	public void setPrecio(float precio) {
		this.precio = precio;
	}

}
