package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import GUI.MenuJTallerView;
import GUI.MenuMecanicoView;
import GUI.MenuVentasView;
import Models.Usuarios;

public class UsuarioDAO extends  AbstractDAO{

	private Usuarios miusuario;
	
	public UsuarioDAO() {
		super();
		miusuario=null;
	}
	
	//devuelve true si el login es correcto, false si es incorrecto
	public boolean compobarlogin(String user, String contra) {
		ResultSet rs= null;
		boolean mibool=false;
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rs = stm.executeQuery("SELECT * FROM usuarios;");
            //miprofe=siguiente();
            while (rs.next()) {
		        if (rs.getString(2).equals(user) && rs.getString(3).equals(contra) && mibool==false) {
		        	mibool=true;
		        	//aqui luego se crearia un usuario
		        	miusuario= new Usuarios(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
		        }
		        
		      
            }
            if (miusuario!=null) {
		        navegacion();
		        }
      
        } catch (SQLException e) {
            e.printStackTrace();    
        }
		return mibool;
	}
	
	

	/**
	 * navegación al menú inicial
	 */
	private void navegacion() {
		switch (miusuario.getRango()) {
			case "vendedor":
				//llamar a la GUI de menú inicial ventas
				MenuVentasView miMenuV = new MenuVentasView(miusuario);
				miMenuV.getFrame().setVisible(true);
				break;
			case "mecanico":
				//llamar a la GUI de menú inicial taller
				MenuMecanicoView miMenuM = new MenuMecanicoView();
				miMenuM.getFrame().setVisible(true);
				break;
			case "jefe":
				//llamar a la GUI de menú inicial Jefe
				MenuJTallerView miMenuJT = new MenuJTallerView();
				miMenuJT.getFrame().setVisible(true);
				break;
			case "jefeTaller":
				//llamar a la GUI de menú inicial jefe de taller
				
				break;
			default:
				System.out.println("¿Comorrrrrrrr?");
		}			
	}
	
	
	public Usuarios getuser() {
		return miusuario;
	} 
	
}
