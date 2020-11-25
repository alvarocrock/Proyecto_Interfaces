package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import Common.Constantes.DigitoDni;
import Common.Constantes.Meses;
import Models.Usuarios;
import Models.Ventas;
import Models.vXeData;
import Models.vXmData;

public class VentasDAO extends AbstractDAO{
	
	protected UsuarioDAO user;
	protected VehiculosDAO vehi;
	protected ClientesDAO clie;
	
	public 	VentasDAO() {
		super();
		user=new UsuarioDAO();
		vehi=new VehiculosDAO();
		clie= new ClientesDAO();
	}
	
	/**
	 * comportamiento que devuelve una array list de id de ventas
	 * @return
	 */
	public ArrayList<Ventas> getventas(){
		ArrayList<Ventas> lista= new ArrayList<Ventas>();
		Ventas miventa;
		ResultSet rst=null;
		try {
            //super.conectar();
            rst = stm.executeQuery("SELECT * FROM ventas;");
            while (rst.next()) {
     			// crea la venta
				miventa = new Ventas(rst.getInt(1),rst.getInt(2), rst.getInt(3), rst.getDate(4), rst.getDate(5), 
						rst.getInt(6),rst.getFloat(7));;
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
	
	public String getnick(int id) {
		return user.getnickbyid(id);
	}
	
	public String getnombrecli(int id) {
		return clie.goToIdCli(id).getNombre();
	}
	
	public String getapellidocli(int id) {
		return clie.goToIdCli(id).getApellido();
	}
	
	public String getmatricula(int id) {
		return vehi.goToIdVeh(id).getMatricula();
	}
	
	/**
	 * comportamiento que devuelve un objeto de tipo venta dado un id
	 * @return 
	 */
	public Ventas getuserbyid(int id) {
		Ventas miventa=null;
		ResultSet rst=null;
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rst = stm.executeQuery("SELECT * FROM ventas where id_ventas="+id+";");
            while (rst.next()) {
				// crea la venta
				miventa = new Ventas(rst.getInt(1),rst.getInt(2), rst.getInt(3), rst.getDate(4), rst.getDate(5), 
						rst.getInt(6),rst.getFloat(7));
            	
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
   
		 
		return miventa;
	}
	//***********************************************
	/**
	 * Borra una venta con un id determinado
	 * @param id
	 */
	public void borraVenta(String id) {
		String 	strSql="delete from ventas where id_ventas='"+id+"'";
		super.ejecutaSQL(strSql);
	}
	
	/**
	 * Comprueba que existe la venta  con un id dado
	 * @param id
	 * @return verdadero si existe esa venta
	 */
	public boolean ComprobarVenta(String id) {
		boolean resultado=false;
		String 	strSql="select id_ventas from ventas where id_ventas='"+id+"'";
		
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
	 * Añade un cliente
	 * @param cliente objeto cliente a añadir
	 */
	public void addVenta(Ventas venta) {
		String 	strSql="insert into ventas "
				+ " (id_cli, id_emple, fecha_ppto, fecha_validez, id_vehiculo, precio) "
				+ "values ('" + venta.getId_cli() +"', '"
				+ venta.getId_emple() +"', '"
				+ venta.getFechappto() +"', '"
				+ venta.getFechavalidez() +"', '"
				+ venta.getId_vehiculo() +"', '"
				+ venta.getPrecio()+"');";
		//ejecuta SQL
		super.ejecutaSQL(strSql);
		
	}

	/**
	 * modifica una venta que ya existe
	 * @param venta objeto venta a modificar
	 */
	public void updateVenta(Ventas venta) {
		
		String 	strSql="update ventas "
				+ " set id_ventas = '" + venta.getId_ventas()+"',"
				+" id_cli = '" + venta.getId_cli() +"',"
				+" id_emple = '" + venta.getId_emple() +"',"
				+" fecha_ppto = '" + venta.getFechappto() +"',"
				+" fecha_validez = '" + venta.getFechavalidez() +"',"
				+ " id_vehiculo = '" + venta.getId_vehiculo() + "' "
				+ "where id_ventas ='"+venta.getId_ventas()+"';";
		super.ejecutaSQL(strSql);
	}
	
	/**
	 * se posiciona en la primera venta
	 * @return objeto que representa la primera venta del rst
	 */
	public Ventas primero() {
		String 	strSql="select * from ventas order by id_ventas";
		Ventas venta=null;
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		try {
			// se posiciona en el primer registro
			if (rst.first()) {
				// crea la venta
				venta = new Ventas(rst.getInt(1),rst.getInt(2), rst.getInt(3), rst.getDate(4), rst.getDate(5), 
						rst.getInt(6),rst.getFloat(7));	
				// devuelve la venta 
				return venta;
			};
			
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * busca el registro anterior
	 * @return Objeto venta que representa el registro anterior
	 */
	public Ventas anterior(String id) {
		String 	strSql="select * from ventas order by id_ventas";
		Ventas venta=null;
		int miFila;
		
		miFila=buscaId(id);
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		try {
			// se posiciona en la fila dado el DNI
			rst.absolute(miFila);
			// se posiciona en el anterior registro
			if (rst.previous()) {
				// crea la venta
				venta = new Ventas(rst.getInt(1),rst.getInt(2), rst.getInt(3), rst.getDate(4), rst.getDate(5), 
						rst.getInt(6),rst.getFloat(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// devuelve el cliente 
		return venta;
	}

	/**
	 * Busca un id y devuelve la fila en la que está
	 * @param id
	 * @return
	 */
	public int buscaId(String id) {
		int resultado=1;
		boolean encontrado=false;
		String 	strSql="select id_ventas from ventas order by id_ventas";
		
		// ejecuta la consultaa
		ResultSet rst=super.consultaSQL(strSql);
		try {
			rst.last();
			rst.first();
			while (!encontrado) {
				if (id.equals(String.valueOf(rst.getInt(1)))) {
					encontrado=true;
				} else {
					rst.next();
					resultado++;
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return resultado;
	}

	/**
	 * busca el registro siguiente
	 * @return Objeto venta que representa el registro siguiente
	 */
	public Ventas siguiente(String id) {
		String 	strSql="select * from ventas order by id_ventas";
		Ventas venta=null;
				
		//Obtengo la fila
		int miFila=buscaId(id);
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		try {
			// se posiciona en la fila dado el id
			rst.absolute(miFila);
			// se posiciona en el siguiente registro
			if (rst.next()) {
				// crea la venta
				// crea la venta
				venta = new Ventas(rst.getInt(1),rst.getInt(2), rst.getInt(3), rst.getDate(4), rst.getDate(5), 
						rst.getInt(6),rst.getFloat(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// devuelve el cliente 
		return venta;
	}

	/**
	 * devuelve un objeto venta representando el último registro de la tabla
	 * @return
	 */
	public Ventas ultimo() {
		String 	strSql="select * from ventas order by id_ventas";
		Ventas venta=null;
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		try {
			// se posiciona en el último registro
			rst.last();
			// crea la venta
			venta = new Ventas(rst.getInt(1),rst.getInt(2), rst.getInt(3), rst.getDate(4), rst.getDate(5), 
					rst.getInt(6),rst.getFloat(7));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// devuelve el cliente 
		return venta;
	}
	

	/**
	 * cuenta los registros en la tabla de ventas
	 * @return número de registros en la tabla de ventas
	 */
	public int count() {
		String 	strSql="select id_ventas from ventas";
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		// devuelve el número de registros 
		return count;
	}
	
	/**
	 * Carga en un arrayList los registros de ventas
	 * @return
	 */
	public ArrayList<Ventas> cargaListaDAO() {
		ArrayList<Ventas> miArray = new ArrayList<Ventas>();
		String 	strSql="select * from ventas order id_ventas";
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		// recorre el rst y guarda en el array
		try {
			Ventas venta;
			while (rst.next()) {
				// crea la venta
				venta = new Ventas(rst.getInt(1),rst.getInt(2), rst.getInt(3), rst.getDate(4), rst.getDate(5), 
						rst.getInt(6),rst.getFloat(7));
				miArray.add(venta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return miArray;
	}
	
	/**
	 * devuelve una venta dado un id de venta
	 * @param id
	 * @return
	 */
	public Ventas goToIdVenta(int id) {
		String 	strSql="select * from ventas where id_ventas = " + id + ";";
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		// crea el cliente
		Ventas venta=null;

		try {
			rst.first();
			// crea la venta
			venta = new Ventas(rst.getInt(1),rst.getInt(2), rst.getInt(3), rst.getDate(4), rst.getDate(5), 
					rst.getInt(6),rst.getFloat(7));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return venta;
		}

	/**
	 * retorna array con los objetos de ventas por meses
	 * para el dataset del gráfico circular
	 * @return 
	 */
	public ArrayList<vXmData> VentaXMes() {
		String 	strSql="";
		vXmData vxm=null;
		ResultSet rst=null;
		ArrayList <vXmData> miArray = new ArrayList<vXmData> ();
		
		int contador=0;
		// recorre el enum de meses
		for (Meses mes: Meses.values()) {
			contador++;
			strSql="select sum(precio) from ventas where month(fecha_ppto) = " + contador + ";";
			// ejecuta la consulta
			rst=super.consultaSQL(strSql);
			// crea el dataset
			try {
				rst.first();
				vxm = new vXmData(mes.toString(),rst.getInt(1));
				miArray.add(vxm);
				rst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return miArray;		
		
	}

	/**
	 * retorna array con los datos de ventas por empleado
	 * para el dataset del gráfico
	 * @return
	 */
	public ArrayList<vXeData> VentaXEmp(ArrayList<Usuarios> ArrayEmp) {
		String 	strSql="";
		vXeData vxe=null;
		ResultSet rst=null;
		ArrayList <vXeData> miArray = new ArrayList<vXeData> ();

		// recorre el el array de empleados
		for (int i=0;i<ArrayEmp.size();i++) {
			strSql="select sum(precio) from ventas where id_emple = " + ArrayEmp.get(i).getId() + ";";
			// ejecuta la consulta
			rst=super.consultaSQL(strSql);
			// crea el dataset
			try {
				rst.first();
				vxe = new vXeData(rst.getFloat(1),"Ventas",ArrayEmp.get(i).getNick());
				miArray.add(vxe);
				rst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return miArray;		
		
	}
//********************** fin

	public ArrayList<Integer> getUsuVentas() {
		String 	strSql="select distinct id_emple from ventas order by id_emple";
		ResultSet rst=null;
		ArrayList <Integer> miArray=new ArrayList<Integer>();
		// ejecuta la consulta
		rst=super.consultaSQL(strSql);
		try {
			while (rst.next()) {
				miArray.add(rst.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rst!=null)
				try {
					rst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return miArray;	
	}
}
