package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Common.Constantes;

public abstract class AbstractDAO {

    // conexión a la BD
    public static Connection conexion = null;
	/**
	 * Define el objeto para conectar con una BD MySQL
	 * @return retorna un objeto connection
	 */
	public void conectar() {

        try {
           Class.forName(Constantes.CONTROLADOR);
            conexion = DriverManager.getConnection(Constantes.URL, Constantes.USUARIO, Constantes.CLAVE);
            System.out.println("Conexión OK");

        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
        }

    }
}
