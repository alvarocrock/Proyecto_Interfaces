package Models;

import java.util.Date;

public class Reparacion {
	Integer id;
	Integer id_cli;
	Integer id_jefe;
	Integer id_mec;
	String desc;
	String Fecha_repara;
	Integer tiempo; //este campo se calculara a partir de las feca ini y fn
	Integer id_veh;
	float precio;
	String fecha_ini; //este campo se pasara cuando el trabajador pulse inciar
	String fecha_fn; //este campo se pasara cuando el trabajador pulse finalizar
	String hora_ini;
	String HoraFN;
	
	
	/**
	 * contructor de reparación parcial
	 * @param miid
	 * @param miid_cli
	 * @param miid_jefe
	 * @param miid_mec
	 * @param midesc
	 * @param fecha_repara
	 * @param miid_veh
	 * @param miprecio
	 */
	public Reparacion(int miid, int miid_cli,int miid_jefe,int miid_mec,String midesc, String fecha_repara,int miid_veh,float miprecio) {
		id=miid;
		id_cli=miid_cli;
		id_jefe=miid_jefe;
		id_mec=miid_mec;
		desc=midesc;
		Fecha_repara=fecha_repara;
		id_veh=miid_veh;
		precio=miprecio;
		tiempo=null;
		fecha_ini=null;
		fecha_fn=null;
		hora_ini=null;
		HoraFN=null;
		
	}
	/**
	 * contructor de reparacion completo
	 * @param miid
	 * @param miid_cli
	 * @param miid_jefe
	 * @param miid_mec
	 * @param midesc
	 * @param mitiempo
	 * @param fecha_repara
	 * @param miid_veh
	 * @param miprecio
	 * @param mifecha_ini
	 * @param mifecha_fin
	 */
	
	public Reparacion(int miid, int miid_cli,int miid_jefe,int miid_mec,String midesc,String fecha_repara,Integer mitiempo,int miid_veh,float miprecio,String mifecha_ini,String mifecha_fin,String horaini,String horafn) {
		id=miid;
		id_cli=miid_cli;
		id_jefe=miid_jefe;
		id_mec=miid_mec;
		desc=midesc;
		Fecha_repara=fecha_repara;
		id_veh=miid_veh;
		precio=miprecio;
		tiempo=mitiempo;
		fecha_ini=mifecha_ini;
		fecha_fn=mifecha_fin;
		HoraFN=horafn;
		hora_ini=horaini;
		
	}


	public String getHora_ini() {
		return hora_ini;
	}
	public void setHora_ini(String hora_ini) {
		this.hora_ini = hora_ini;
	}
	public String getHoraFN() {
		return HoraFN;
	}
	public void setHoraFN(String horaFN) {
		HoraFN = horaFN;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setId_cli(Integer id_cli) {
		this.id_cli = id_cli;
	}
	public void setId_jefe(Integer id_jefe) {
		this.id_jefe = id_jefe;
	}
	public void setId_mec(Integer id_mec) {
		this.id_mec = id_mec;
	}
	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}
	public void setId_veh(Integer id_veh) {
		this.id_veh = id_veh;
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


	public int getId_jefe() {
		return id_jefe;
	}


	public void setId_jefe(int id_jefe) {
		this.id_jefe = id_jefe;
	}


	public int getId_mec() {
		return id_mec;
	}


	public void setId_mec(int id_mec) {
		this.id_mec = id_mec;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public String getFecha_repara() {
		return Fecha_repara;
	}


	public void setFecha_repara(String fecha_repara) {
		Fecha_repara = fecha_repara;
	}


	public Integer getTiempo() {
		return tiempo;
	}


	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
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


	public String getFecha_ini() {
		return fecha_ini;
	}


	public void setFecha_ini(String fecha_ini) {
		this.fecha_ini = fecha_ini;
	}


	public String getFecha_fn() {
		return fecha_fn;
	}


	public void setFecha_fn(String fecha_fn) {
		this.fecha_fn = fecha_fn;
	}
	
	
	
	

}
