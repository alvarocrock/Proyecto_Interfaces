package Models;

import java.sql.Date;

public class Vehiculos {
	
	//, matricula, bastidor, marca, modelo,precio, fecha alta, id cli, id_user, idconce

	int idVeh;
	String matricula;
	String bastidor;
	String marca;
	String modelo;
	float precio;
	Date fecha_alta;
	int id_cli;
	int id_user;
	int id_conce;
	String tipo;
	int year;
	int km;
	String combustible;

	
	public Vehiculos(int idVeh,String mimatricula, String mibastidor,String mimarca, String 
			mimodelo, float miprecio,Date mifecha_alta , int miid_cli,int mi_id_user,
			int miid_conce, String tipo,int miyear,int mikm,String micombu) {

		this.idVeh=idVeh;
		matricula=mimatricula;
		bastidor=mibastidor;
		marca=mimarca;
		modelo=mimodelo;
		precio=miprecio;
		fecha_alta=mifecha_alta;
		id_cli=miid_cli;
		id_user=mi_id_user;
		id_conce=miid_conce;
		this.tipo=tipo;
		year=miyear;
		km=mikm;
		combustible=micombu;

		
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public int getKm() {
		return km;
	}


	public void setKm(int km) {
		this.km = km;
	}


	public String getCombustible() {
		return combustible;
	}


	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}


	public void setIdVeh(int idVeh) {
		this.idVeh = idVeh;
	}


	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public int getidVeh() {
		return idVeh;
	}
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getBastidor() {
		return bastidor;
	}

	public void setBastidor(String bastidor) {
		this.bastidor = bastidor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Date getFecha_alta() {
		return fecha_alta;
	}

	public int getId_cli() {
		return id_cli;
	}

	public void setId_cli(int id_cli) {
		this.id_cli = id_cli;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_conce() {
		return id_conce;
	}

	public void setId_conce(int id_conce) {
		this.id_conce = id_conce;
	}


	public int getIdVeh() {
		return idVeh;
	}


	public String getTipo() {
		return tipo;
	}



}
