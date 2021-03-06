package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Statement;

import Models.Clientes;
import Models.Concesionario;
import Models.Reparacion;
import Models.Ventas;

public class TrabajoHoyDAO  extends AbstractDAO{
	
	
	ClientesDAO clie;
	UsuarioDAO usu;
	VehiculosDAO vehi;
	
	public TrabajoHoyDAO(){
		super();
		clie= new ClientesDAO();
		usu= new UsuarioDAO();
		vehi= new VehiculosDAO();
	}
	
	public void add(Reparacion r) {
		String strSql="INSERT INTO `proyecto`.`repara` (`id_cli`, `id_jefe_taller`, `id_mec`, `descripcion`, `fecha_repara`, `id_vehiculo`, `presu_esrti`) "
				+ "VALUES ('"+r.getId()+"', '"+r.getId_jefe()+"', '"+r.getId_mec()+"', '"+r.getDesc()+"', '"+r.getFecha_repara()+"', '"+r.getId_veh()+"', '"+r.getPrecio()+"');";
		Connection conn = null;
	    Statement stmt = null;

	    try {

	      super.conectar();
	      stmt = (Statement) cn.createStatement();

	      stmt.executeUpdate(strSql);

	      

	    } catch (SQLException e) {
	      e.printStackTrace();
	    	
	    } finally {
	      try {
	        // Close connection
	        if (stmt != null) {
	          stmt.close();
	        }
	        if (cn != null) {
	          cn.close();
	        }
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    }
	}
	
	public Reparacion gotoTrabajo(int id) {
		
		Reparacion rep=null;
		ResultSet rs=null;
		String contenido="";
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rs = stm.executeQuery("SELECT * FROM proyecto.repara where id_rep='"+id+"' order by id_rep;");
            	rs.first();
            	Integer tiempo;
            	if (rs.getString(7)!="null") {
            		tiempo=rs.getInt(7);
            	} else {
            		tiempo=null;
            	}
            	String fechaini;
            	String prueba=rs.getString(10);
            	if (rs.getString(10)==null) {
            		fechaini="0000-00-00";
            	} else {
            		fechaini=rs.getString(10);
            	}
            	
            	String fechafn;
            	if (rs.getString(11)==null) {
            		fechafn="0000-00-00";
            	} else {
            		fechafn=rs.getString(11);
            	}
            	
            	String horaini;
            	if (rs.getString(12)==null) {
            		horaini="0";
            	} else {
            		horaini=rs.getString(12);
            	}
            	
            	String horafn;
            	if (rs.getString(13)==null) {
            		horafn="0";
            	} else {
            		horafn=rs.getString(13);
            	}
            	rep=new Reparacion(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getString(6),tiempo,rs.getInt(8),rs.getFloat(9),fechaini,fechafn,horaini,horafn);
            
            
      
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
   
		return rep;
	}
	
	
	public void update(Reparacion rep) {
			//qqqqq
			String strSql="UPDATE `proyecto`.`repara` SET `id_cli` = '"+rep.getId()+"', `id_jefe_taller` = '"+rep.getId_jefe()+"',"
					+ " `id_mec` = '"+rep.getId_mec()+"', `descripcion` = '"+rep.getDesc()+"',"
					+ " `id_vehiculo` = '"+rep.getId_veh()+"', `presu_esrti` = '"+rep.getPrecio()+"' WHERE (`id_rep` = '"+rep.getId()+"');";
			try {
			super.conectar();
            stm = (Statement) cn.createStatement();
            stm.executeUpdate(strSql);
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
	        		}
					catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					 }
	        	}
	}

	
	public void delete(int id) {
		//qqqqq
		String strSql="DELETE FROM `proyecto`.`repara` WHERE (`id_rep` = '"+id+"');";
		try {
		super.conectar();
        stm = (Statement) cn.createStatement();
        stm.executeUpdate(strSql);
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
        		}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 }
        	}
}
	
	
	public boolean comprobartrabajo(int id) {
		boolean resultado=false;
		String 	strSql="SELECT * FROM proyecto.repara where id_rep="+id+"' order by id_rep;";
		
		// ejecuta la consulta
		ResultSet rs=null;
		
		// devuelve rst.next (falso si no existe, true si existe)
		try {
			super.conectar();
            stm = (Statement) cn.createStatement();
            rs = stm.executeQuery("SELECT * FROM proyecto.repara where id_rep='"+id+"' order by id_rep;");
			resultado= rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return resultado;
	}
	
	
	
	public Reparacion first() {
			
			Reparacion rep=null;
			ResultSet rs=null;
			String contenido="";
			try {
	            super.conectar();
	            stm = (Statement) cn.createStatement();
	            rs = stm.executeQuery("SELECT * FROM proyecto.repara order by id_rep;");
	            	rs.first();
	            	Integer tiempo;
	            	if (rs.getString(7)!="null") {
	            		tiempo=rs.getInt(7);
	            	} else {
	            		tiempo=null;
	            	}
	            	String fechaini;
	            	String prueba=rs.getString(10);
	            	if (rs.getString(10)==null) {
	            		fechaini="0000-00-00";
	            	} else {
	            		fechaini=rs.getString(10);
	            	}
	            	
	            	String fechafn;
	            	if (rs.getString(11)==null) {
	            		fechafn="0000-00-00";
	            	} else {
	            		fechafn=rs.getString(11);
	            	}
	            	
	            	String horaini;
	            	if (rs.getString(12)==null) {
	            		horaini="0";
	            	} else {
	            		horaini=rs.getString(12);
	            	}
	            	
	            	String horafn;
	            	if (rs.getString(13)==null) {
	            		horafn="0";
	            	} else {
	            		horafn=rs.getString(13);
	            	}
	            	rep=new Reparacion(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getString(6),tiempo,rs.getInt(8),rs.getFloat(9),fechaini,fechafn,horaini,horafn);
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
	   
			return rep;
		}
	
	
	/**
	 * retorna el ultimo id de reparacion
	 * @return
	 */
	public Integer getlast() {
		String 	strSql="select *"
				+" from proyecto.repara order by id_rep";
			Integer num=1;
			
			// ejecuta la consulta
			ResultSet rs=super.consultaSQL(strSql);
			try {
				// se posiciona en el primer registro
				if (rs.last()==true) {
					rs.last();
					num=rs.getInt(1);
				}
				
				 
				
				// crea el concesionario	
				} catch (SQLException e) {
				e.printStackTrace();
			}
			return num;
		
	}
	
	/**
	 * modifica la decha de inicio por la fecha actual
	 */
	public void setFini(int id) {
		Date d=new Date();
		String fecha=1900+d.getYear()+"-"+d.getMonth()+"-"+d.getDay();
		String strSQL="UPDATE `proyecto`.`repara` SET `fecha_ini` = '"+fecha+"', `hora_fn` = "+d.getHours()+"  WHERE (`id_rep` = '"+id+"');";
		try {
			super.conectar();
            stm = (Statement) cn.createStatement();
            stm.executeUpdate(strSQL);
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
	        		}
					catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					 }
	        	}
		
	}
	
	public void setTfinal(int tfinal,int id) {
		String strSQL="UPDATE `proyecto`.`repara` SET `tiempo` = "+tfinal+" WHERE (`id_rep` = '"+id+"');";
		try {
			super.conectar();
            stm = (Statement) cn.createStatement();
            stm.executeUpdate(strSQL);
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
	        		}
					catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					 }
	        	}
	}
	
	
	public void setFFN(int id) {
		Date d=new Date();
		String fecha=1900+d.getYear()+"-"+d.getMonth()+"-"+d.getDay();
		String strSQL="UPDATE `proyecto`.`repara` SET `fecha_fn` = '"+fecha+"',`hora_ini` = "+d.getHours()+" WHERE (`id_rep` = '"+id+"');";
		try {
			super.conectar();
            stm = (Statement) cn.createStatement();
            stm.executeUpdate(strSQL);
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
	        		}
					catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					 }
	        	}
		
	}
	
	public String getdir(int id) {
		return clie.goToIdCli(id).getDireccion();
	}
	
	public int getidclibydni(String dni) {
		return clie.getidbydni(dni);
	}
	
	public int getidmec(String nick) {
		return usu.getidbynick(nick);
	}
	
	public int getidveh(String mat) {
		return vehi.getidbymat(mat);
	}

	public String getnombre(int id) {
		return clie.goToIdCli(id).getNombre();
	}
	
	public String getapellido(int id) {
		return clie.goToIdCli(id).getApellido();
	}
	
	public String getnick(int id){
		return usu.getnickbyid(id);
	}
	
	public String getmatricula(int id) {
		return vehi.goToIdVeh(id).getMatricula();
	}
	
	public ArrayList<Reparacion> cargaListaDAO() {
		// TODO Auto-generated method stub
		ArrayList<Reparacion> lista= new ArrayList();
		Reparacion rep;
		ResultSet rs=null;
		try {
            //super.conectar();
            rs = stm.executeQuery("SELECT * FROM repara;");
            while (rs.next()) {
     			// crea la venta
            	Integer tiempo;
            	if (rs.getString(7)!="null") {
            		tiempo=rs.getInt(7);
            	} else {
            		tiempo=null;
            	}
            	String fechaini;
            	String prueba=rs.getString(10);
            	if (rs.getString(10)==null) {
            		fechaini="0000-00-00";
            	} else {
            		fechaini=rs.getString(10);
            	}
            	
            	String fechafn;
            	if (rs.getString(11)==null) {
            		fechafn="0000-00-00";
            	} else {
            		fechafn=rs.getString(11);
            	}
            	
            	String horaini;
            	if (rs.getString(12)==null) {
            		horaini="0";
            	} else {
            		horaini=rs.getString(12);
            	}
            	
            	String horafn;
            	if (rs.getString(13)==null) {
            		horafn="0";
            	} else {
            		horafn=rs.getString(13);
            	}
            	rep=new Reparacion(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getString(6),tiempo,rs.getInt(8),rs.getFloat(9),fechaini,fechafn,horaini,horafn);
            	lista.add(rep);
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
