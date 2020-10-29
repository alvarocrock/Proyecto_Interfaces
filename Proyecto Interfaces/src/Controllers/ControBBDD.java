package Controllers;

import java.sql.Connection;
import java.sql.ResultSet;

import DAO.AbstractDAO;

public class ControBBDD extends AbstractDAO{
	
	protected ResultSet rs;
	
	//connection es cn
	
	//constructor conrolador bbdd
	public ControBBDD() {
		super();
	}
	
	//comportamiento para conectar con la bbdd
	
	//coportamiento para comprobar el usuario
	public void comprobar(String nick,String passwd) {
		
	}
	
	public void iniciar() {
		super.iniciar();
	}

}
