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
	
	
	public void borrappto(int id) {
		String 	strSql="delete from ppto where id_presupuesto="+id+";";
		super.ejecutaSQL(strSql);
	}
	
	
	public void updateppto(Presupuesto presu) {
		
		String 	strSql="UPDATE ppto SET `id_cli` = '"+presu.getFecha_ppto()+"', `id_emple` = '"+presu.getId_emple()+"', "
				+ "`fecha_ppto` = '"+presu.getFecha_ppto()+"', `fecha_validez` = '"+presu.getFecha_validez()+"',"
				+ " `id_vehiculo` = '"+presu.getId_veh()+"', `precio` = '"+presu.getPrecio()+"' "
				+ "WHERE (`id_presupuesto` = '"+presu.getId()+"');";
		super.ejecutaSQL(strSql);
	}
	
	public String getDNIcli(int id) {
		return cliente.getDNIbyid(id);
	}
	
	
	public int getidclibydni(String dni) {
		return cliente.getidbydni(dni);
	}
	
	public int getidvehbymat(String mat) {
		return vehiculo.getidbymat(mat);
	}
	
	
	public void addpresu(Presupuesto presu) {
		String 	strSql="INSERT INTO `proyecto`.`ppto` (`id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) "
				+ "VALUES ('3', '2', '2000-04-21', '2012-04-21', '5', '3000');";
		// Se ejecuta correctamente el SQL
		super.ejecutaSQL(strSql);
	}
	
	
	
	public boolean comprobarppto(int id) {
		boolean resultado=false;
		String 	strSql="select id_presupuesto from ppto where id_conce="+id+";";
		
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
	
	public int getidemplebynick(String nick) {
		return usuarios.getidbynick(nick);
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
	
	public Presupuesto ultimo() {
		String 	strSql="select *"
						+" from ppto order by id_presupuesto";
		Presupuesto mipresu=null;
		
		// ejecuta la consulta
		ResultSet rs=super.consultaSQL(strSql);
		try {
			// se posiciona en el primer registro
			rs.last();
			// crea el concesionario
			mipresu = new Presupuesto(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getFloat(7));	
			} catch (SQLException e) {
			e.printStackTrace();
		}
		// devuelve el cliente 
		return mipresu;
	}
	
	
	public Presupuesto siguiente(String id) {
		String 	strSql="select *"
						+" from ppto order by id_presupuesto";
		Presupuesto mipresu=null;
		
		// ejecuta la consulta
		ResultSet rs=super.consultaSQL(strSql);
		try {
			rs.absolute(Integer.parseInt(id));
			rs.next();
			// crea el concesionario
			mipresu = new Presupuesto(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getFloat(7));	
			} catch (SQLException e) {
			e.printStackTrace();
		}
		// devuelve el cliente 
		return mipresu;
	}
	
	public Presupuesto anterior(String id) {
		String 	strSql="select *"
						+" from ppto order by id_presupuesto";
		Presupuesto mipresu=null;
		
		// ejecuta la consulta
		ResultSet rs=super.consultaSQL(strSql);
		try {
			rs.absolute(Integer.parseInt(id));
			rs.previous();
			// crea el concesionario
			mipresu = new Presupuesto(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getFloat(7));	
			} catch (SQLException e) {
			e.printStackTrace();
		}
		// devuelve el cliente 
		return mipresu;
	}
	
	
	public Presupuesto goToPPTO_byid(int id) {
		String 	strSql="select * from ppto where id_presupuesto= " + id + ";";
		
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
