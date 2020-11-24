package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import GUI.MenuJTallerView;
import GUI.MenuMecanicoView;
import GUI.MenuVentasView;
import Models.Usuarios;
import Models.Vehiculos;

public class UsuarioDAO extends  AbstractDAO{

	private Usuarios miusuario;
	
	public UsuarioDAO() {
		super();
		miusuario=null;
	}
	
	/**
	 * devuelve id usuario dado un Nick
	 * @param nick
	 * @return
	 */
	public int getidbynick(String nick) {
		int id=0;
		ResultSet rs=null;
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            String consulta="SELECT id_user FROM proyecto.usuarios where nick='"+nick+"';";
            rs = stm.executeQuery(consulta);
            while (rs.next()) {
            	id= rs.getInt(1);	
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
		return id;
	}
	
	/**
	 * devuelve un nick dado un id
	 * @param id
	 * @return
	 */
	public String getnickbyid(int id) {
		String valor="";
		//SELECT nombre,apellidos FROM proyecto.clientes where id_cli=1;
		ResultSet rs=null;
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rs = stm.executeQuery("SELECT Nick FROM usuarios where id_user="+id+";");
            while (rs.next()) {
            			
            	valor= rs.getString(1);
            	
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
		return valor;
	}
	

	/**
	 * devuelve true si el login es correcto, false si es incorrecto
	 * @param user
	 * @param contra
	 * @return
	 */
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
		        	miusuario= new Usuarios(rs.getString(1),rs.getString(2),
		        			rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(7), rs.getString(6));
		        }
		        
		      
            }
         
        } catch (SQLException e) {
            e.printStackTrace();    
        }
		return mibool;
	}

	public Usuarios goToUserById(int idUser) {
		String 	strSql="select * from usuarios where id_user = " + idUser + ";";
		
		// crea el usuario
		Usuarios miUser=null;
		ResultSet rs=null;
		try {
            rs = stm.executeQuery(strSql);
            if (rs.next()) {
			miUser =  new Usuarios(rs.getString(1),rs.getString(2),
        			rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(7), rs.getString(6));
            }
              
        } catch (SQLException e) {
            e.printStackTrace();    
        } finally {
        	try {
        		if (rs!=null) {
        			rs.close();
        		}
        		}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 }
        	}
		
        return miUser;
	}
	
	/**
	 * devuelve objeto usuario actual
	 * @return
	 */
	public Usuarios getuser() {
		return miusuario;
	}
	
	
}
