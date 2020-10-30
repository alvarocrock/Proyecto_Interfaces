package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

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
      
        } catch (SQLException e) {
            e.printStackTrace();    
        }
		return mibool;
	}
	
	public Usuarios getuser() {
		return miusuario;
	} 
	
}
