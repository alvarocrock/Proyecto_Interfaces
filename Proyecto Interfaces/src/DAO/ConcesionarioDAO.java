package DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import Models.Clientes;
import Models.Concesionario;
import Models.Vehiculos;
import Models.Ventas;

public class ConcesionarioDAO extends AbstractDAO{
	
	/**
	 * contructor concesionarioDAO
	 */
	public ConcesionarioDAO() {
		super();
	}
	
	/**
	 * devuelve una array con los concesionarios
	 * @return
	 */
	public ArrayList<Concesionario> getconce(){
		ArrayList<Concesionario> lista= new ArrayList();
		Concesionario miconce;
		ResultSet rs=null;
		String contenido="";
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rs = stm.executeQuery("SELECT * FROM concesionario;");
            while (rs.next()) {
            	miconce=new Concesionario(rs.getString(2),rs.getInt(1));
            	lista.add(miconce);
            }
            
      
        } catch (SQLException e) {
            e.printStackTrace();    
        } finally {
        	try {
        		if (cn!=null) {
        			cn.close();
        		}
        		if (stm!=null) {
        			stm.close();
        		}
        		if (rs!=null) {
        			rs.close();
        		}
        		}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 }
        	}
   
		return lista;
		
	}
	
	/**
	 * devuelve una objeto concesionario dado un id de concesionario
	 * @return
	 */
	public Concesionario goToConce(int miId){
		Concesionario miConce = null;

		ResultSet rs=null;
            try {
	            rs = stm.executeQuery("SELECT * FROM concesionario where id_conce=;"+miId);
	          	miConce=new Concesionario(rs.getString(2),rs.getInt(1));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return miConce;
	}

	/**
	 * se posiciona en el primer concesionario
	 * @return objeto que representa al primer cliente del rst
	 */
	public Concesionario primero() {
		String 	strSql="select id_conce, nombre"
						+" from concesionario order by id_conce";
		Concesionario miConce=null;
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		try {
			// se posiciona en el primer registro
			rst.first();
			// crea el concesionario
			miConce = new Concesionario(rst.getString(2),rst.getInt(1));	
			} catch (SQLException e) {
			e.printStackTrace();
		}
		// devuelve el cliente 
		return miConce;
	}
	
	/**
	 * busca el registro anterior
	 * @return Objeto concesionario que representa el registro anterior
	 */
	public Concesionario anterior(String idConce) {
		String 	strSql="select id_conce, nombre from concesionario order by id_conce";
		Concesionario miConce=null;
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		try {
			// se posiciona en la fila dado el DNI
			rst.absolute(Integer.parseInt(idConce));
			// se posiciona en el anterior registro
			if (rst.previous()) {
				// crea el Concesionario
				miConce = new Concesionario(rst.getString(2),rst.getInt(1));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// devuelve el cliente 
		return miConce;
	}

	/**
	 * busca el registro siguiente
	 * @return Objeto Concesionario que representa el registro siguiente
	 */
	public Concesionario siguiente(String idConce) {
		String 	strSql="select id_conce, nombre from concesionario order by id_conce";
		Concesionario miConce=null;
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		try {
			// se posiciona en la fila dado el DNI
			rst.absolute(Integer.parseInt(idConce));
			// se posiciona en el siguiente registro
			if (rst.next()) {
				// crea el Concesionario
				miConce = new Concesionario(rst.getString(2),rst.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// devuelve el cliente 
		return miConce;
	}

	/**
	 * devuelve un objeto concesionario representando el último registro de la tabla
	 * @return
	 */
	public Concesionario ultimo() {
		String 	strSql="select id_conce,nombre from clientes order by id_conce";
		Concesionario miConce=null;
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		try {
			// se posiciona en el último registro
			rst.last();
			// crea el cliente
			miConce = new Concesionario(rst.getString(2), rst.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// devuelve el concesionario 
		return miConce;
	}

	/**
	 * cuenta los registros en la tabla de concesionarios
	 * @return número de registros en la tabla de concesionarios
	 */
	public int count() {
		String 	strSql="select id_conce from clientes order by id_conce";
		int count=0;
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
			try {
				// si hay registros
				if (rst.next()) {
					// se posiciona en el primer registro
					rst.first();
					count=1;
					// mientras haya registros suma 1 al contador
					while (rst.next()) {
						count++;
					}
				} else count=0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		// devuelve el número de registros 
		return count;
	}

	/**
	 * devuelve un concesionario dado un id
	 * @param idConce
	 * @return
	 */
	public Concesionario goToIdConce(int idConce) {
		String 	strSql="select id_conce, nombre from clientes where id_conce = " + idConce + ";";
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		// crea el concesionario
		Concesionario miConce=null;

		try {
			rst.first();
			miConce = new Concesionario(rst.getString(2), rst.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return miConce;
	}
	
	/**
	 * Borra un concesionario con un ID determinado
	 * @param DNI
	 */
	public void borraConce(int idConce) {
		String 	strSql="delete from concesionario where id_conce="+idConce+";";
		super.ejecutaSQL(strSql);
	}
	
	/**
	 * Comprueba que existe el concesionario con un ID dado
	 * @param idConce
	 * @return verdadero si existe ese concesionario
	 */
	public boolean comprobarConce(int idConce) {
		boolean resultado=false;
		String 	strSql="select id_conce from concesionarios where id_conce="+idConce+";";
		
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
	 * modifica un concesionario que ya existe
	 * @param concesionario objeto cliente a modificar
	 */
	public void updateConce(Concesionario concesionario) {
		
		String 	strSql="update concesionario "
				+ " set nombre = '" + concesionario.getNombre() +"' "
				+ "where id_conce='"+concesionario.getNombre()+"';";
		super.ejecutaSQL(strSql);
	}
	
	/**
	 * Añade un concesionario
	 * @param concesionario objeto concesionario a añadir
	 */
	public void addConce(Concesionario conce) {
		String 	strSql="insert into concesionario "
				+ " (nombre) "
				+ "values ('" + conce.getNombre() 
				+"');";
		// Se ejecuta correctamente el SQL
		super.ejecutaSQL(strSql);
		
	}
}
