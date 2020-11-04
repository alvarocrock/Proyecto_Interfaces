package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import Models.Concesionario;

public class VentasDAO extends AbstractDAO{
	
	
	public 	VentasDAO() {
		super();
	}
	
	public ArrayList<Integer> getventas(){
		ArrayList<Integer> lista= new ArrayList();
		Concesionario miconce=null; 
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

}
