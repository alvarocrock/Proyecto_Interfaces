package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import GUI.Clientes;

public class ClientesDAO extends AbstractDAO {

	public ClientesDAO() {
		super();
	}
	
	/**
	 * Borra un cliente con un DNI determinado
	 * @param DNI
	 */
	public void borraCliente(String DNI) {
		String 	strSql="delete from clientes where DNI='"+DNI+"'";
		super.ejecutaSQL(strSql);
	}
	
	/**
	 * Comprueba que existe el cliente con un DNI dado
	 * @param DNI
	 * @return verdadero si existe ese cliente
	 */
	public boolean ComprobarCliente(String DNI) {
		boolean resultado=false;
		String 	strSql="select DNI from clientes where DNI='"+DNI+"'";
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		// devuelve rst.next (falso si no existe, true si existe)
		try {
			resultado= rst.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	/**
	 * guarda un cliente que ya existe
	 * @param cliente
	 */
	public void updateCliente(Clientes cliente) {
		String 	strSql="update clientes "
				+ " set nombre = '" + cliente.getNombre() +"',"
				+" apellidos = '" + cliente.getApellido() +"',"
				+" direccion = '" + cliente.getDireccion() +"',"
				+" provincia = '" + cliente.getProvincia() +"',"
				+" poblacion = '" + cliente.getPoblacion() +"',"
				+" id_cli = '" + cliente.getIdCli() +"',"				
				+ "where DNI='"+cliente.getDNI()+"'";
		super.ejecutaSQL(strSql);
		
	}
	
	
}
