package Models;

import java.sql.Date;

public class Ventas {

	int id_ventas;
	int id_cli;
	int id_emple;
	Date fechappto;
	Date fechavalidez;
	int id_vehiculo;
	float precio;
	
	public Ventas(int id_v, int id_clie,int miid_emple, Date mifechappto,Date FV,int id_vehi,float miprecio) {
		id_ventas=id_v;
		id_cli=id_clie;
		id_emple=miid_emple;
		fechappto=mifechappto;
		fechavalidez=FV;
		id_vehiculo=id_vehi;
		precio=miprecio;
	}

	public int getId_vehiculo() {
		return id_vehiculo;
	}

	public void setId_vehiculo(int id_vehiculo) {
		this.id_vehiculo = id_vehiculo;
	}

	public int getId_ventas() {
		return id_ventas;
	}

	public void setId_ventas(int id_ventas) {
		this.id_ventas = id_ventas;
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

	public Date getFechappto() {
		return fechappto;
	}

	public void setFechappto(Date fechappto) {
		this.fechappto = fechappto;
	}

	public Date getFechavalidez() {
		return fechavalidez;
	}

	public void setFechavalidez(Date fechavalidez) {
		this.fechavalidez = fechavalidez;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
}
