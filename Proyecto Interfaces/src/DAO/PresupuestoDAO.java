package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import Models.Concesionario;
import Models.Presupuesto;
import Models.Ventas;

public class PresupuestoDAO extends AbstractDAO{
	
	protected ClientesDAO cliente;
	protected VehiculosDAO vehiculo;
	protected UsuarioDAO usuarios;
	
	
	public PresupuestoDAO() {
		super();
		cliente= new ClientesDAO();
		vehiculo= new VehiculosDAO();
		usuarios= new UsuarioDAO();
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
	
	
	public String getnombrecli(int id) {
		return cliente.getnombrebyid(id);
	}
	
	
	public String getnick(int id) {
		return usuarios.getnickbyid(id);
	}
	

	
	public String getapellidocli(int id) {
		return cliente.getapellidoByID(id);
	}
	
	public String getmatricula(int id) {
		return vehiculo.getmatriculabyid(id);
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
	
	public Presupuesto primero() {
		String 	strSql="select *"
						+" from ppto order by id_presupuesto";
		Presupuesto mipresu=null;
		
		// ejecuta la consulta
		ResultSet rs=super.consultaSQL(strSql);
		try {
			// se posiciona en el primer registro
			rs.first();
			// crea el concesionario
			mipresu = new Presupuesto(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getFloat(7));	
			} catch (SQLException e) {
			e.printStackTrace();
		}
		// devuelve el cliente 
		return mipresu;
	}
	
	
	public Presupuesto goToPPTO_byid(int id) {
		String 	strSql="select * from concesionario where id_presupuesto = " + id + ";";
		
		// ejecuta la consulta
		ResultSet rs=super.consultaSQL(strSql);
		// crea el concesionario
		Presupuesto mipresu=null;

		try {
			rs.first();
			mipresu = new Presupuesto(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getFloat(7));	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mipresu;
	}
	
	

}
