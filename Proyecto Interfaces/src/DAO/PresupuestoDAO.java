package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import Models.Concesionario;
import Models.Presupuesto;
import Models.Ventas;

public class PresupuestoDAO extends AbstractDAO{
	
	
	
	public PresupuestoDAO() {
		super();
	}

	public ArrayList<Presupuesto> getpresupuestos() {
		ArrayList<Presupuesto> lista= new ArrayList();
		Presupuesto mipresu;
		ResultSet rs=null;
		String contenido="";
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rs = stm.executeQuery("SELECT * FROM ppto;");
            while (rs.next()) {
            	int idveh=rs.getInt(6);
            	mipresu=new Presupuesto(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getFloat(7));
            	lista.add(mipresu);
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
	
	
	public String getnick(int id) {
		String nick="";
		ResultSet rs=null;
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rs = stm.executeQuery("SELECT Nick FROM usuarios where id_user="+id+";");
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
	
	public String getmatricula(int id) {
		String mat="";
		ResultSet rs=null;
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rs = stm.executeQuery("SELECT matricula FROM vehiculos where id_vehiculo="+id+";");
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
	
	public Presupuesto goToPPTO(int miId){
		Presupuesto mipresu = null;

		ResultSet rs=null;
            try {
	            rs = stm.executeQuery("SELECT * FROM ppto where id_presupuesto="+miId+";");
	            rs.first();
	          	mipresu=new Presupuesto(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getFloat(7));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return mipresu;
	}
	
	public int count() {
		String 	strSql="select * from ppto order by id_presupuesto";
		int count=0;
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
			try {
				// si hay registros
				if (rst.next()) {
					// se posiciona en el primer registro
					rst.first();
					count=1;
					// mientras haya registros suma 1 al contador
					while (rst.next()) {
						count++;
					}
				} else count=0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		// devuelve el número de registros 
		return count;
	}

}
