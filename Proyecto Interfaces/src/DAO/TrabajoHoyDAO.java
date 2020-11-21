package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import Models.Clientes;
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
		Reparacion mirepa;
		ResultSet rst=null;
		try {
            //super.conectar();
            rst = stm.executeQuery("SELECT * FROM repara;");
            while (rst.next()) {
     			// crea la venta
            	Integer tiempo;
            	if (rst.getString(7)!="null") {
            		tiempo=null;
            	} else {
            		tiempo=rst.getInt(7);
            	}
				mirepa = new Reparacion(rst.getInt(1),rst.getInt(2),rst.getInt(3),rst.getInt(4),rst.getString(5),rst.getString(6),tiempo,rst.getInt(8),rst.getFloat(9),rst.getString(10),rst.getString(11));
            	lista.add(mirepa);
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
        		if (rst!=null) {
        			rst.close();
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
