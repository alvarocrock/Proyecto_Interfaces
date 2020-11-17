package DAO;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

import Models.Clientes;
import Models.Concesionario;
import Models.Usuarios;
import Models.Vehiculos;

public class VehiculosDAO extends AbstractDAO{
	
	public VehiculosDAO() {
		super();
	}
	
	
	//comportamiento para crear una array list de los concesionarios
	/**
	 * comportamiento que devuelve una array con los concesionarrios de la bbdd
	 * @return
	 */
	/*public ArrayList<Concesionario> arrayconce(){
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
		
	}*/
	
	public int getidbymat(String mat) {
		int matr=0;
		ResultSet rs=null;
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            String consulta="SELECT id_vehiculo FROM proyecto.vehiculos where matricula='"+mat+"';";
            rs = stm.executeQuery(consulta);
            while (rs.next()) {
            			
            	matr= rs.getInt(1);
            	
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
		return matr;
	}
		
	/**
	 * comporatmientop para obtener el id de cliente en base a un dni dado
	 */
	
	public String getmatriculabyid(int id) {
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
	 * comportamiento que retorna el id del concesiona rio en base a su nombre
	 * @param nombre
	 * @return
	 */
	public int getidconce(String nombre) {
		int miint=0;
		ResultSet rs=null;
		try {
            super.conectar();
            stm = (Statement) cn.createStatement();
            rs = stm.executeQuery("SELECT id_conce FROM proyecto.concesionario where nombre='"+nombre+"';");
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
		Date mifecha=Date.valueOf(LocalDate.now());
		 try {

		      super.conectar();
		      stm = (Statement) cn.createStatement();

		      
		      String consulta = "INSERT INTO `vehiculos` "
						+ "(`matricula`, `bastidor`, `marca`, `modelo`, `precio`, `fecha_alta`, `id_cliente`,"
						+"`id_usuario`, `id_conce`, `tipo`)"
						+ " VALUES ('"+matricula+"', '"+bastidor+"', '"+marca+"', '"+modelo+"', '"+precio+"', '"
						+mifecha+"', '"+id_cli+"', '"+id_user+"', '"+id_conce+"', '"+tipo+"');";
  		      stm.executeUpdate(consulta);
		    } catch (SQLException e) {
		    	JOptionPane.showMessageDialog(null, "Something went wrong", "Message", JOptionPane.INFORMATION_MESSAGE);
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

	/**
	 * Carga un array con los datos de la tabla vehículos
	 * @return
	 */
	public ArrayList<Vehiculos> cargaListaDAO() {
		ArrayList<Vehiculos> miArray = new ArrayList<Vehiculos>();
		String 	strSql="select id_vehiculo,matricula, bastidor,"
				+"marca, modelo,precio,fecha_alta,id_cliente,id_usuario,id_conce, tipo from vehiculos order by id_vehiculo";
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		// recorre el rst y guarda en el array
		try {
			while (rst.next()) {
				Vehiculos miVeh = new Vehiculos(rst.getInt(1), rst.getString(2), rst.getString(3), 
						rst.getString(4), rst.getString(5), rst.getFloat(6), rst.getDate(7), rst.getInt(8), 
						rst.getInt(9), rst.getInt(10), rst.getString(11));
					miArray.add(miVeh);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return miArray;
	}
	
	/**
	 * devuelve un vehiculo dado un id de vehiculo
	 * @param idVeh
	 * @return
	 */
	public Vehiculos goToIdVeh(int idVeh) {
		String 	strSql="select * from vehiculos where id_vehiculo = " + idVeh + ";";
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		// crea el cliente
		Vehiculos veh=null;

		try {
			rst.first();
			veh = new Vehiculos(rst.getInt(1),rst.getString(2), rst.getString(3), rst.getString(4),rst.getString(5),rst.getFloat(6),
					rst.getDate(7),rst.getInt(8),rst.getInt(9),rst.getInt(10), rst.getString(11));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return veh;
	}
	
	/**
	 * retorna true si la matricula que se le pasa existe en la bbdd
	 * @param matricula
	 * @return
	 */
	public boolean Comprobarvehiculo(String matricula) {
		boolean resultado=false;
		String 	strSql="select matricula from vehiculos where matricula='"+matricula+"'";
		
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
	 * modifica un vehiculo que ya existe
	 * @param cliente objeto vehiculo a modificar
	 */
	public void updateVehiculo(Vehiculos mivehiculo) {
		
		//UPDATE `proyecto`.`vehiculos` SET 
		//`matricula` = '2', `bastidor` = '4', `marca` = '6', `modelo` = '7', `precio` = '0', 
		//`fecha_alta` = '2020-10-03', `id_usuario` = '2', `id_conce` = '5', 
		//`tipo` = 'nulo' WHERE (`id_vehiculo` = '24');
		String 	strSql="UPDATE vehiculos "
				+"SET matricula = '"+mivehiculo.getMatricula() + "', "
				+ " bastidor = '" + mivehiculo.getBastidor() + "', "
				+" marca = '"+mivehiculo.getMarca() + "', "
				+" modelo = '"+mivehiculo.getModelo()+"', "
				+ " precio = '"+mivehiculo.getPrecio()+"',"
				+ " fecha_alta = '"+mivehiculo.getFecha_alta() + "', "
				+" id_usuario = '"+mivehiculo.getId_user()+"',"
				+ " id_conce = '"+mivehiculo.getId_conce()+"', "
				+" tipo = '"+mivehiculo.getTipo()+"' "
				+" WHERE matricula = '"+mivehiculo.getMatricula()+"';";
		super.ejecutaSQL(strSql);

	}
	
	/**
	 * se posiciona en el primer vehiculo
	 * @return objeto que representa al primer vehiculo del rst
	 */
	public Vehiculos primero() {
		String 	strSql="select * from vehiculos order by id_vehiculo";
		Vehiculos miVehiculo=null;
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		try {
			// se posiciona en el primer registro
			rst.first();
			// crea el vehiculo
			miVehiculo = new Vehiculos(rst.getInt(1),rst.getString(2), rst.getString(3), rst.getString(4),rst.getString(5),rst.getFloat(6),
					rst.getDate(7),rst.getInt(8),rst.getInt(9),rst.getInt(10), rst.getString(11));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// devuelve el cliente 
		return miVehiculo;
	}
	
	/**
	 * busca el registro anterior
	 * @return Objeto vehiculo que representa el registro anterior
	 */
	public Vehiculos anterior(String matricula) {
		String 	strSql="select * from vehiculos order by id_vehiculo";
		Vehiculos miVehiculo=null;
		int miFila;
		
		// extrae al fila actual
		miFila=buscaMatricula(matricula);
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		try {
			// se posiciona en el registro actual
			rst.absolute(miFila);
			// se posiciona en el registro anterior
			if (rst.previous()) {
				// crea el vehiculo
				miVehiculo = new Vehiculos(rst.getInt(1),rst.getString(2), rst.getString(3), rst.getString(4),
						rst.getString(5),rst.getFloat(6),rst.getDate(7),rst.getInt(8),rst.getInt(9),rst.getInt(10), rst.getString(11));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// devuelve el cliente 
		return miVehiculo;
	}
	
	/**
	 * Busca una matricula y devuelve la fila en la que está
	 * @param matricula
	 * @return
	 */
	public int buscaMatricula(String matricula) {
		int resultado=1;
		boolean encontrado=false;
		String 	strSql="select matricula from vehiculos order by id_vehiculo";
		
		// ejecuta la consultaa
		ResultSet rst=super.consultaSQL(strSql);
		try {
			rst.last();
			rst.first();
			while (!encontrado) {
				if (matricula.equals(rst.getString(1))) {
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
	 * cuenta los registros en la tabla de vehiculos
	 * @return número de registros en la tabla de vehiculos
	 */
	public int count() {
		String 	strSql="select matricula from vehiculos order by id_vehiculo";
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
	 * Borra un vehiculo con una matrícula determinada
	 * @param matricula
	 */
	public void borraVehiculo(String matricula) {
		String 	strSql="delete from vehiculos where matricula='"+matricula+"'";
		super.ejecutaSQL(strSql);
	}
	
	/**
	 * Añade un vehiculo
	 * @param vehiculo objeto cliente a añadir
	 */
	public void addVehiculo(Vehiculos miVeh) {
		String 	strSql="insert into vehiculos "
				+ " (matricula, bastidor, marca, modelo, precio, fecha_alta,"
				+"id_cliente,id_usuario,id_conce) "
				+ "values ('" + miVeh.getMatricula() +"', '"
				+ miVeh.getBastidor() +"', '"
				+ miVeh.getMarca() +"', '"
				+ miVeh.getModelo() +"', '"
				+ String.valueOf(miVeh.getPrecio()) +"', '"
				+ miVeh.getFecha_alta() + "', '"
				+ miVeh.getId_cli()+"', '"
				+ miVeh.getId_user() + "', '"
				+ miVeh.getId_conce() + "', '"
				+ miVeh.getTipo() + "');";
		// Se ejecuta  el SQL
		super.ejecutaSQL(strSql);		
	}
	
	/**
	 * busca el registro siguiente
	 * @return Objeto vehiculo que representa el registro siguiente
	 */
	public Vehiculos siguiente(String matricula) {
		String 	strSql="select * from vehiculos order by id_vehiculo";
		Vehiculos miVeh=null;
				
		//Obtengo la fila
		int miFila=buscaMatricula(matricula);
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		try {
			// se posiciona en la fila dado la matricula
			rst.absolute(miFila);
			// se posiciona en el primer registro
			if (rst.next()) {
				// crea el vehiculo
				miVeh = new Vehiculos(rst.getInt(1),rst.getString(2), rst.getString(3), rst.getString(4),
						rst.getString(5),rst.getFloat(6), rst.getDate(7),rst.getInt(8),rst.getInt(9),rst.getInt(10), rst.getString(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// devuelve el cliente 
		return miVeh;
	}
	
	/**
	 * devuelve un objeto vehiculo representando el último registro de la tabla
	 * @return
	 */
	public Vehiculos ultimo() {
		String 	strSql="select * from vehiculos order by id_vehiculo";
		Vehiculos miVeh=null;
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		try {
			// se posiciona en el último registro
			rst.last();
			// crea el vehiculo
			miVeh = new Vehiculos(rst.getInt(1),rst.getString(2), rst.getString(3), rst.getString(4),rst.getString(5),rst.getFloat(6),
					rst.getDate(7),rst.getInt(8),rst.getInt(9),rst.getInt(10), rst.getString(11));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// devuelve el cliente 
		return miVeh;
	}
// **************************** fin
}
