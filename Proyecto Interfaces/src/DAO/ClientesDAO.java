package DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Models.Clientes;


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
	 * Añade un cliente
	 * @param cliente objeto cliente a añadir
	 */
	public void addCliente(Clientes cliente) {
		String 	strSql="insert into clientes "
				+ " (nombre, apellidos, direccion, provincia, poblacion, fecha_alta, DNI) "
				+ "values ('" + cliente.getNombre() +"', '"
				+ cliente.getApellido() +"', '"
				+ cliente.getDireccion() +"', '"
				+ cliente.getProvincia() +"', '"
				+ cliente.getPoblacion() +"', '"
				+ Date.valueOf(LocalDate.now()) + "', '"
				+ cliente.getDNI()+"');";
		// Se ejecuta correctamente el SQL
		super.ejecutaSQL(strSql);
		
	}

	/**
	 * modifica un ciente que ya existe
	 * @param cliente objeto cliente a modificar
	 */
	public void updateCliente(Clientes cliente) {
		
		String 	strSql="update clientes "
				+ " set nombre = '" + cliente.getNombre() +"',"
				+" apellidos = '" + cliente.getApellido() +"',"
				+" direccion = '" + cliente.getDireccion() +"',"
				+" provincia = '" + cliente.getProvincia() +"',"
				+" poblacion = '" + cliente.getPoblacion() +"',"
				+ " fecha_alta = '" + Date.valueOf(LocalDate.now()) + "' "
				+ "where DNI='"+cliente.getDNI()+"';";
		super.ejecutaSQL(strSql);
	}
	
	/**
	 * se posiciona en el primer cliente
	 * @return objeto que representa al primer cliente del rst
	 */
	public Clientes primero() {
		String 	strSql="select id_cli,DNI,nombre,apellidos,direccion,provincia,poblacion,fecha_alta"
						+" from clientes order by id_cli";
		Clientes cliente=null;
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		try {
			// se posiciona en el primer registro
			rst.first();
			// crea el cliente
			cliente = new Clientes(rst.getInt(1),rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), 
					rst.getString(6),rst.getString(7), rst.getDate(8));	
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// devuelve el cliente 
		return cliente;
	}
	
	/**
	 * busca el registro anterior
	 * @return Objeto cliente que representa el registro anterior
	 */
	public Clientes anterior(String DNI) {
		String 	strSql="select id_cli,DNI,nombre,apellidos,direccion,provincia,poblacion,fecha_alta"
				+" from clientes order by id_cli";
		Clientes cliente=null;
		int miFila;
		
		miFila=buscaDNI(DNI);
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		try {
			// se posiciona en la fila dado el DNI
			rst.absolute(miFila);
			// se posiciona en el anterior registro
			if (rst.previous()) {
				// crea el cliente
				cliente = new Clientes(rst.getInt(1),rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), 
						rst.getString(6),rst.getString(7), rst.getDate(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// devuelve el cliente 
		return cliente;
	}

	/**
	 * Busca un DNI y devuelve la fila en la que está
	 * @param dNI
	 * @return
	 */
	public int buscaDNI(String dNI) {
		int resultado=1;
		boolean encontrado=false;
		String 	strSql="select DNI from clientes order by id_cli";
		
		// ejecuta la consultaa
		ResultSet rst=super.consultaSQL(strSql);
		try {
			rst.last();
			rst.first();
			while (!encontrado) {
				if (dNI.equals(rst.getString(1))) {
					encontrado=true;
				} else {
					rst.next();
					resultado++;
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return resultado;
	}

	/**
	 * busca el registro siguiente
	 * @return Objeto cliente que representa el registro siguiente
	 */
	public Clientes siguiente(String DNI) {
		String 	strSql="select id_cli,DNI,nombre,apellidos,direccion,provincia,poblacion,fecha_alta"
				+" from clientes order by id_cli";
		Clientes cliente=null;
				
		//Obtengo la fila
		int miFila=buscaDNI(DNI);
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		try {
			// se posiciona en la fila dado el DNI
			rst.absolute(miFila);
			// se posiciona en el primer registro
			if (rst.next()) {
				// crea el cliente
				cliente = new Clientes(rst.getInt(1),rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), 
						rst.getString(6),rst.getString(7), rst.getDate(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// devuelve el cliente 
		return cliente;
	}

	/**
	 * devuelve un objeto cliente representando el último registro de la tabla
	 * @return
	 */
	public Clientes ultimo() {
		String 	strSql="select id_cli,DNI,nombre,apellidos,direccion,provincia,poblacion,fecha_alta"+
						" from clientes order by id_cli";
		Clientes cliente=null;
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		try {
			// se posiciona en el último registro
			rst.last();
			// crea el cliente
			cliente = new Clientes(rst.getInt(1),rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), 
					rst.getString(6),rst.getString(7), rst.getDate(8));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// devuelve el cliente 
		return cliente;
	}

	/**
	 * cuenta los registros en la tabla de clientes
	 * @return número de registros en la tabla de clientes
	 */
	public int count() {
		String 	strSql="select DNI from clientes order by id_cli";
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		// devuelve el número de registros 
		return count;
	}

	public ArrayList<Clientes> cargaListaDAO() {
		ArrayList<Clientes> miArray = new ArrayList<Clientes>();
		String 	strSql="select id_cli,DNI,nombre,apellidos,direccion,provincia,poblacion,"
				+"fecha_alta from clientes order by id_cli";
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		// recorre el rst y guarda en el array
		try {
			Clientes cliente;
			while (rst.next()) {
				cliente = new Clientes(rst.getInt(1),rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), 
						rst.getString(6),rst.getString(7), rst.getDate(8));
				miArray.add(cliente);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return miArray;
	}
	
	/**
	 * devuelve un cliente dado un id de cliente
	 * @param idCli
	 * @return
	 */
	public Clientes goToIdCli(int idCli) {
		String 	strSql="select id_cli,DNI,nombre,apellidos,direccion,provincia,poblacion,fecha_alta "
				+"from clientes where id_cli = " + idCli + ";";
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		// crea el cliente
		Clientes cliente=null;

		try {
			rst.first();
			cliente = new Clientes(rst.getInt(1),rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), 
					rst.getString(6),rst.getString(7), rst.getDate(8));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cliente;
	}
}
