package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import Models.Concesionario;
import Models.Ventas;

public class VentasDAO extends AbstractDAO{
	
	
	public 	VentasDAO() {
		super();
	}
	
	/**
	 * comportamiento que devuelve una array list de id de ventas
	 * @return
	 */
	public ArrayList<Integer> getventas(){
		ArrayList<Integer> lista= new ArrayList();
		ResultSet rs=null;
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rs = stm.executeQuery("SELECT id_ventas FROM ventas;");
            while (rs.next()) {
      
            	lista.add(rs.getInt(1));
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
	 * comportamineto que devuelve un objeto de tipo venta dado un id
	 * @return
	 */
	public Ventas getuserbyid(int id) {
		Ventas miventa=null;
		ResultSet rs=null;
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rs = stm.executeQuery("SELECT * FROM ventas where id_ventas="+id+";");
            while (rs.next()) {
            						//id venta, id cliente, id empleado, fecha ppto, fecha validez, id vehiculo
            	miventa=new Ventas(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getFloat(7));
            	
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
   
		
		return miventa;
	}
	
	/**
	 * comportamiento que deculeve el nombre del lciente en base a un id de cliente
	 * @param id
	 * @return
	 */
	public String getnombrecli(int id) {
		String nombre="";
		//SELECT nombre,apellidos FROM proyecto.clientes where id_cli=1;
		ResultSet rs=null;
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rs = stm.executeQuery("SELECT nombre FROM clientes where id_cli="+id+";");
            while (rs.next()) {
            			
            	nombre= rs.getString(1);
            	
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
		return nombre;
	}
	/**
	 * retorna apellidos de un empleado en base al su id de cliente
	 * @param id
	 * @return
	 */
	public String getapellidocli(int id) {
		String nombre="";
		//SELECT nombre,apellidos FROM proyecto.clientes where id_cli=1;
		ResultSet rs=null;
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rs = stm.executeQuery("SELECT apellidos FROM clientes where id_cli="+id+";");
            while (rs.next()) {
            			
            	nombre= rs.getString(1);
            	
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
		return nombre;
	}
	
	/**
	 * comportamiento que retorna le nick del empleado en base a su id de empleado
	 * @param id
	 * @return
	 */
	public String getnick(int id) {
		String nick="";
		ResultSet rs=null;
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rs = stm.executeQuery("SELECT apellidos FROM clientes where id_cli="+id+";");
            while (rs.next()) {
            			
            	nick= rs.getString(1);
            	
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
		return nick;
	}

	/**
	 * comportamiento que retorna una matricula en base a un id de vehiculo
	 * @param id
	 * @return
	 */
	public String getmatricula(int id) {
		String mat="";
		ResultSet rs=null;
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rs = stm.executeQuery("SELECT matricula FROM proyecto.vehiculos where id_vehiculo="+id+";");
            while (rs.next()) {
            			
            	mat= rs.getString(1);
            	
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
		return mat;
	}
}
