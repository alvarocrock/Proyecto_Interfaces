package Models;

import java.sql.Date;

public class Usuarios {

	//estado
	private String dni;
	private String nick;
	private String Passwd;
	private String rango;
	private String fecha;
	private String foto;
	private int id;
	
	/**
	 * contructor usuarios
	 * @param midni
	 * @param minick
	 * @param mipasswd
	 * @param mirango
	 * @param mifecha
	 * @param miid
	 */
	public Usuarios(String midni,String minick,String mipasswd,String mirango,String mifecha,int miid, String foto) {
		dni=midni;
		nick=minick;
		Passwd=mipasswd;
		rango=mirango;
		fecha=mifecha;
		id=miid;
		this.foto=foto;
	}

	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPasswd() {
		return Passwd;
	}

	public void setPasswd(String passwd) {
		Passwd = passwd;
	}

	public String getRango() {
		return rango;
	}

	public void setRango(String rango) {
		this.rango = rango;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFoto() {
		return foto;
	}
	
	
}
