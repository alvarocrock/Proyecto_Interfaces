package DAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

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
		      JOptionPane.showMessageDialog(null, "vehicle added successfully to database", "Message", JOptionPane.INFORMATION_MESSAGE);

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


	public ArrayList<String> cargaListaDAO() {
		ArrayList<String> miArray = new ArrayList<String>();
		String 	strSql="select id_vehiculo,matricula,marca,modelo,precio from vehiculo order by id_vehiculo";
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		// recorre el rst y guarda en el array
		try {
			while (rst.next()) {
				String str = " ";
				str=String.valueOf(rst.getInt(1))+" | ";
				str=str + rst.getString(2)+" | ";
				str=str + rst.getString(4)+" | ";
				str=str + rst.getString(5)+" | ";
				str=str + rst.getFloat(6)+" | ";
				miArray.add(str);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			veh = new Vehiculos(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4),rst.getString(5),rst.getFloat(6),
					rst.getString(7),rst.getInt(8),rst.getInt(9),rst.getInt(10),rst.getString(11));
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
	 * @param cliente objeto cliente a modificar
	 */
	public void updateVehiculo(Vehiculos mivehiculo) {
		
		//UPDATE `proyecto`.`vehiculos` SET `matricula` = '2', `bastidor` = '4', `marca` = '6', `modelo` = '7', `precio` = '0', `fecha_alta` = '2020-10-03', `id_usuario` = '2', `id_conce` = '5', `tipo` = 'nulo' WHERE (`id_vehiculo` = '24');
		String 	strSql="UPDATE `vehiculos` SET `matricula` = '"+mivehiculo.getMatricula()+"', "
				+ "`bastidor` = '"+mivehiculo.getBastidor()+"', `marca` = '"+mivehiculo.getMarca()+"', `modelo` = '"+mivehiculo.getModelo()+"', "
				+ "`precio` = '"+mivehiculo.getPrecio()+"',"
				+ " `fecha_alta` = '"+mivehiculo.getFecha_alta()+"', `id_usuario` = '"+mivehiculo.getId_user()+"',"
				+ " `id_conce` = '"+mivehiculo.getId_conce()+"', `tipo` = '"+mivehiculo.getTipo()+"' WHERE (`id_vehiculo` = '"+mivehiculo.getId()+"');";
		if (super.ejecutaSQL(strSql))
			JOptionPane.showMessageDialog(null, "Registro modificado correctamente", "Modificar cliente", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * se posiciona en el primer vehiculo
	 * @return objeto que representa al primer cliente del rst
	 */
	public Vehiculos primero() {
		String 	strSql="select * from vehiculos order by id_vehiculo";
		Vehiculos mivehiculo=null;
		
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		try {
			// se posiciona en el primer registro
			rst.first();
			// crea el cliente
			mivehiculo = new Vehiculos(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4),rst.getString(5),rst.getFloat(6),
					rst.getString(7),rst.getInt(8),rst.getInt(9),rst.getInt(10),rst.getString(11));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// devuelve el cliente 
		return mivehiculo;
	}
	
	/**
	 * busca el registro anterior
	 * @return Objeto cliente que representa el registro anterior
	 */
	public Vehiculos anterior(String matricula) {
		String 	strSql="select * from vehiculos order by id_vehiculo";
		Vehiculos mivehiculo=null;
		int miFila;
		// ejecuta la consulta
		ResultSet rst=super.consultaSQL(strSql);
		miFila=buscaMatricula(matricula);
		try {
			// se posiciona en el primer registro
			rst.absolute(miFila);
			
			rst.previous();
			// crea el cliente
			mivehiculo = new Vehiculos(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4),rst.getString(5),rst.getFloat(6),
					rst.getString(7),rst.getInt(8),rst.getInt(9),rst.getInt(10),rst.getString(11));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// devuelve el cliente 
		return mivehiculo;
	}
	
	/**
	 * Busca una matricula y devuelve la fila en la que está
	 * @param dNI
	 * @return
	 */
	public int buscaMatricula(String matricula) {
		int resultado=1;
		boolean encontrado=false;
		String 	strSql="select matricula from vehiculos order by matricula";
		
		// ejecuta la consultaa
		ResultSet rst=super.consultaSQL(strSql);
		try {
			rst.last();
			rst.first();
			while (!encontrado) {
				if (matricula.equals(rst.getString(2))) {
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

	

}
