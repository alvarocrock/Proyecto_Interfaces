package DAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Statement;

import Models.Concesionario;
import Models.Usuarios;

public class VehiculosDAO extends AbstractDAO{
	
	public VehiculosDAO() {
		super();
	}
	
	
	//comportamiento para crear una array list de los concesionarios
	/**
	 * comportamiento que devuelve una array con los concesionarrios de la bbdd
	 * @return
	 */
	public ArrayList<Concesionario> arrayconce(){
		ArrayList<Concesionario> miarray= new ArrayList();
		Concesionario miconce=null; 
		ResultSet rs=null;
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rs = stm.executeQuery("SELECT * FROM concesionario;");
            while (rs.next()) {
            	miconce=new Concesionario(rs.getString(2),rs.getInt(1));
            	miarray.add(miconce);
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
   
		return miarray;
		
	}
		
	/**
	 * comporatmientop para obtener el iif de cliente en base a un dni dado
	 */
	
	public int getidcli(String DNI) {
		int miint=0;
		ResultSet rs=null;
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rs = stm.executeQuery("SELECT id_cli FROM proyecto.clientes where DNI ='"+DNI+"';");
            while (rs.next()) {
            miint=rs.getInt(1);	
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
		return miint;
	}
		
	/**
	 * comportamiento para crear un registro	
	 */
	public void crearregistro(String matricula,String bastidor,String marca,String modelo,float precio,int id_cli,int id_user,int id_conce, String tipo) {
		Date fecha;
		String mifecha;
		 try {

		      super.conectar();
		      stm = (Statement) cn.createStatement();
		      fecha= new Date();
		      mifecha=fecha.getYear()+1900+"-"+fecha.getMonth()+"-"+fecha.getDay();
		      
		      String consulta = "INSERT INTO `vehiculos` (`matricula`, `bastidor`, `marca`, `modelo`, `precio`, `fecha_alta`, `id_cliente`, `id_usuario`, `id_conce`, `tipo`)"
		      + " VALUES ('"+matricula+"', '"+bastidor+"', '"+marca+"', '"+modelo+"', '"+precio+"', '"+mifecha+"', '"+id_cli+"', '"+id_user+"', '"+id_conce+"', '"+tipo+"');";
		      
		      stm.executeUpdate(consulta);

		    } catch (SQLException e) {
		      e.printStackTrace();
		    } finally {
		      try {
		        // Close connection
		        if (stm != null) {
		          stm.close();
		        }
		        if (cn != null) {
		          cn.close();
		        }
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		    }
		
		
	}
	
	
	

}
