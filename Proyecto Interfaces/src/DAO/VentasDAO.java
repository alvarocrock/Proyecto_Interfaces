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
	public ArrayList<Ventas> getventas(){
		ArrayList<Ventas> lista= new ArrayList();
		Ventas miventa;
		ResultSet rs=null;
		String contenido="";
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rs = stm.executeQuery("SELECT * FROM ventas;");
            while (rs.next()) {
            	miventa=new Ventas(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getFloat(7));
            	lista.add(miventa);
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
	
}
