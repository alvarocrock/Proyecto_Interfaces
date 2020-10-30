package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class UsuarioDAO extends  AbstractDAO{

	public UsuarioDAO() {
		super();
	}
	
	//devuelve true si el login es correcto, false si es incorrecto
	public boolean compobarlogin(String user, String contra) {
		ResultSet rs= null;
		boolean mibool=false;
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rs = stm.executeQuery("SELECT nick,passwd FROM usuarios;");
            //miprofe=siguiente();
            while (rs.next()) {
		        if (rs.getString(1).equals(user) && rs.getString(2).equals(contra) && mibool==false) {
		        	mibool=true;
		        	//aqui luego se crearia un usuario
		        }
            }
      
        } catch (SQLException e) {
            e.printStackTrace();    
        }
		return mibool;
	}
	
	
}
