package DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Common.Constantes;

public abstract class AbstractDAO {
	
	// estados

	
    // conexión a la BD
    public static Connection cn = null;
    // statement
	public static Statement stm = null;
    
    //comportamientos
    /**
     * inicia el DAO
     * crea la conexión a BD y el statement
     * @return 
     */
    public static void iniciar () {
		// crea la conexión
        conectar();
        // crea el statement
        try {
			stm = cn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        // crea la BD si es necesario
        if (ejecutaSQL("USE "+Constantes.BD)) {
        	ejecutaSentencias(Constantes.SQLCREATE);
        } else {
        	
        }
    }
    
	/**
	 * Define el objeto connection para conectar con una BD MySQL
	 */
    private static void conectar() {
        try {
           Class.forName(Constantes.CONTROLADOR);
            cn = DriverManager.getConnection(Constantes.URL, Constantes.USUARIO, Constantes.CLAVE);
            System.out.println("Conexión OK");

        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
        }		
	}

	
	/**
	 * ejecuta sentencias desde un fichero SQL
	 * @param fileSQL fichero con las sentencias
	 */
	public static void ejecutaSentencias(String fileSQL) {
		//lee el fichero
		String lineas= leeFichero(fileSQL);
		String [] sentencias=lineas.split(";");
		// recorre el array y ejecuta las sentencias 
		for (int i=0;i<sentencias.length;i++) {
			ejecutaSQL(sentencias[i]);
		}
	}
	
	/**
	 * lee un fichero SQL y devuelve un String con los datos
	 * @return
	 */
	private static String leeFichero(String fileSQL) {
		String lineas=" ";
		BufferedReader lector = null;

		try {
			lector = new BufferedReader(new FileReader(fileSQL));
			String linea=lector.readLine();
			while(linea!=null) {
				if (linea!=null) lineas=lineas+linea.trim();
				linea=lector.readLine();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			try {
				lector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lineas;
	}
	
	/**
	 * Ejecuta un statement 
	 * @param strSQL el statement SQL a ejecutar
	 * @return 
	 */
	private static boolean ejecutaSQL(String strSQL) {
		boolean resultado=true;
		
		try {
			// ejecuta Statement
            stm.execute(strSQL);
            JOptionPane.showMessageDialog(null, strSQL, "SQL Ejecutado", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException e) {
			e.printStackTrace();
            JOptionPane.showMessageDialog(null, strSQL, "Error: "+e.getMessage(), JOptionPane.INFORMATION_MESSAGE);
			resultado=false;
		} finally {
			
        }
		return resultado;
	}
	
	//*************** fin
}
