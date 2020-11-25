package DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import Models.Usuarios;

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

	/**
	 * devuelve un objeto usuario dado un id
	 * @param idUser
	 * @return
	 */
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
	/**
	 * Comprueba que existe el usuario con un DNI dado
	 * @param DNI
	 * @return verdadero si existe ese usuario
	 */
	public boolean ComprobarUsuario(String DNI) {
		boolean resultado=false;
		String 	strSql="select DNI from usuarios where DNI='"+DNI+"'";
		
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
	 * Borra un usuario con un DNI determinado
	 * @param DNI
	 */
	public void borraUsuario(String DNI) {
		String 	strSql="delete from usuarios where DNI='"+DNI+"'";
		super.ejecutaSQL(strSql);
	}
	/**
	 * Añade un usuario
	 * @param usuario objeto cliente a añadir
	 */
	public void addUsuario(Usuarios usuario) {
		String 	strSql="insert into usuario "
				+ " (DNI,Nick, passwd,rango,fecha_alta,foto,id_user) "
				+ "values ('" + usuario.getDni() +"', '"
				+ usuario.getPasswd() +"', '"
				+ usuario.getPasswd() +"', '"
				+ usuario.getRango() +"', '"
				+ Date.valueOf(LocalDate.now()) + "', '"
				+ usuario.getFoto() +"');";
		super.ejecutaSQL(strSql);
	}

	/**
	 * modifica un usuario que ya existe
	 * @param cliente objeto cliente a modificar
	 */
	public void updateUsuario(Usuarios usuario) {
		
		String 	strSql="update usuarios "
				+ " set DNI = '" + usuario.getDni() +"',"
				+" Nick = '" + usuario.getNick() +"',"
				+" passwd = '" + usuario.getPasswd() +"',"
				+" rango = '" + usuario.getRango() +"',"
				+" fecha_alta = '" + Date.valueOf(LocalDate.now()) +"',"
				+" foto = '" + usuario.getFoto() +"',"
				+ " id_user = '" + usuario.getId()+ "' "
				+ "where DNI='"+usuario.getDni()+"';";
		super.ejecutaSQL(strSql);
	}
	
	/**
	 * se posiciona en el primer usuario
	 * @return objeto que representa al primer usuario del rst
	 */
	public Usuarios primero() {
		String 	strSql="select * from usuarios order by id_user";
		Usuarios usuario=null;
		

		try {
			// ejecuta la consulta
			super.conectar();
			Statement stmt= (Statement) cn.createStatement();
			ResultSet rst = stmt.executeQuery(strSql);
			// se posiciona en el primer registro
			rst.first();
			// crea el usuario
			usuario =  new Usuarios(rst.getString(1),rst.getString(2),
	        			rst.getString(3),rst.getString(4),rst.getString(5),rst.getInt(7), rst.getString(6));
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// devuelve el usuario 
		return usuario;
	}
	
	/**
	 * busca el registro anterior
	 * @return Objeto usuario que representa el registro anterior
	 */
	public Usuarios anterior(String DNI) {
		String 	strSql="select * from usuarios order by id_user";
		Usuarios usuario=null;
		int miFila;
		miFila=buscaDNI(DNI);

		try {
			// ejecuta la consulta
			super.conectar();
			Statement stmt= (Statement) cn.createStatement();
			ResultSet rst = stmt.executeQuery(strSql);
			// se posiciona en la fila dado el DNI
			rst.absolute(miFila);
			// se posiciona en el anterior registro
			if (rst.previous()) {
				usuario =  new Usuarios(rst.getString(1),rst.getString(2),
	        			rst.getString(3),rst.getString(4),rst.getString(5),rst.getInt(7), rst.getString(6));
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// devuelve el cliente 
		return usuario;
	}

	/**
	 * Busca un DNI y devuelve la fila en la que está
	 * @param dNI
	 * @return
	 */
	public int buscaDNI(String dNI) {
		int resultado=1;
		boolean encontrado=false;
		String 	strSql="select DNI from usuarios order by id_user";
		
		// ejecuta la consultaa
		ResultSet rs=null;
		
		try {
			super.conectar();
			Statement stmt= (Statement) cn.createStatement();
			rs = stmt.executeQuery(strSql);
			rs.last();
			rs.first();
			while (!encontrado) {
				if (dNI.equals(rs.getString(1))) {
					encontrado=true;
				} else {
					rs.next();
					resultado++;
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return resultado;
	}

	/**
	 * busca el registro siguiente
	 * @return Objeto usuario que representa el registro siguiente
	 */
	public Usuarios siguiente(String DNI) {
		String 	strSql="select * from usuarios order by id_user";
		Usuarios usuario=null;
				
		//Obtengo la fila
		int miFila=buscaDNI(DNI);
	 
		try {
			// ejecuta la consulta
			super.conectar();
			Statement stmt= (Statement) cn.createStatement();
			ResultSet rst = stmt.executeQuery(strSql);
			// se posiciona en la fila dado el DNI
			rst.absolute(miFila);
			// se posiciona en el siguiente registro
			if (rst.next()) {
				usuario =  new Usuarios(rst.getString(1),rst.getString(2),
	        			rst.getString(3),rst.getString(4),rst.getString(5),rst.getInt(7), rst.getString(6));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		// devuelve el usuario 
		return usuario;
	}

	/**
	 * devuelve un objeto usuario representando el último registro de la tabla
	 * @return
	 */
	public Usuarios ultimo() {
		String 	strSql="select * from usuarios order by id_user";
		Usuarios usuario=null;
		
		try {
			// ejecuta la consulta
			super.conectar();
			Statement stmt= (Statement) cn.createStatement();
			ResultSet rst = stmt.executeQuery(strSql);
			// se posiciona en el último registro
			rst.last();
			usuario =  new Usuarios(rst.getString(1),rst.getString(2),
	        			rst.getString(3),rst.getString(4),rst.getString(5),rst.getInt(7), rst.getString(6));
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// devuelve el usuario 
		return usuario;
	}

	/**
	 * cuenta los registros en la tabla de usuarios
	 * @return número de registros en la tabla de usuarios
	 */
	public int count() {
		String 	strSql="select DNI from usuarios order by id_user";
		int count=0;
		
		// ejecuta la consulta
		ResultSet rs=null;
			try {
				super.conectar();
				Statement stmt= (Statement) cn.createStatement();
				rs = stmt.executeQuery(strSql);
				// si hay registros
				if (rs.next()) {
					// se posiciona en el primer registro
					rs.first();
					count=1;
					// mientras haya registros suma 1 al contador
					while (rs.next()) {
						count++;
					}
				} else count=0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (rs!=null)
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		// devuelve el número de registros 
		return count;
	}
	
	/**
	 * Carga en un arrayList los registros de usuarios
	 * @return
	 */
	public ArrayList<Usuarios> cargaListaDAO() {
		ArrayList<Usuarios> miArray = new ArrayList<Usuarios>();
		String 	strSql="select * from usuarios order by id_user";
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		// recorre el rst y guarda en el array
		try {
			Usuarios usuario;
			while (rst.next()) {
				usuario =  new Usuarios(rst.getString(1),rst.getString(2),
	        			rst.getString(3),rst.getString(4),rst.getString(5),rst.getInt(7), rst.getString(6));
				miArray.add(usuario);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return miArray;
	}
	
	/**
	 * devuelve un usuario dado un id de usuario
	 * @param idCli
	 * @return
	 */
	public Usuarios goToIdUsu(int idUsu) {
		
		ResultSet rst=null;
		Usuarios usuario=null;
		String 	strSql="select * from usuarios where id_user = " + idUsu + ";";
		
		// ejecuta la consulta
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rst = stm.executeQuery(strSql);
			rst.first();
			usuario =  new Usuarios(rst.getString(1),rst.getString(2),
        			rst.getString(3),rst.getString(4),rst.getString(5),rst.getInt(7), rst.getString(6));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
        		if (stm!=null) {
        			stm.close();
        		}
        		if (rst!=null) {
        			rst.close();
        		}
        		}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 }
		}
		return usuario;
		}

	public ArrayList<Usuarios> getUsuVentas(ArrayList<Integer> arrayUsuInt) {
		ArrayList<Usuarios> miArray = new ArrayList<Usuarios>();
		String 	strSql="select * from usuarios order by id_user";
		Usuarios usuario=null;
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		// recorre el rst y guarda en el array
		try {
			rst.first();
			for (int i=0;i<arrayUsuInt.size();i++) {
				usuario =  new Usuarios(rst.getString(1),rst.getString(2),
	        			rst.getString(3),rst.getString(4),rst.getString(5),rst.getInt(7), rst.getString(6));
				miArray.add(usuario);
				rst.next();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return miArray;
	}
}