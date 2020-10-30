package Controllers;

import DAO.UsuarioDAO;
import GUI.LoginView;
import GUI.MenuJTallerView;
import GUI.MenuMecanicoView;
import GUI.MenuVentasView;

public class ControladorPrTaller {
	
	//estados
	private ControBBDD controbbdd;
	private LoginView miLoginView;
	private UsuarioDAO miUsuarioDAO;
	
	//comportamientos
	/**
	 * constructor
	 */
	public ControladorPrTaller() {
		// instancia el controlador de BBDD
		controbbdd= new ControBBDD();
		// instancia la loginView
		miLoginView = new LoginView();
		//Instancia el usuarioDAO
		miUsuarioDAO= new UsuarioDAO();
		
		// muestra el Login
		miLoginView.getFrame().setAlwaysOnTop(true);
		miLoginView.getFrame().setVisible(true);
		
		// Aqui habría que tomar el usuarioDAO desde la vista
		
		// según el tipo de usuario iremos a un menú u otro
		
		navegacion();
		
		
		
		System.out.println("fin");
	}
	
	/**
	 * Comprueba el login
	 */
	public boolean comprobarlogin(String user,String contra) {
		navegacion();
		return miUsuarioDAO.compobarlogin(user, contra);
	}
	
	
	
	/**
	 * navegación al menú inicial
	 */
	private void navegacion() {
		switch (miUsuarioDAO.getuser().getRango()) {
			case "vendedor":
				//llamar a la GUI de menú inicial ventas
				MenuVentasView miMenuV = new MenuVentasView();
				miMenuV.getFrame().setVisible(true);
				break;
			case "mecanico":
				//llamar a la GUI de menú inicial taller
				MenuMecanicoView miMenuM = new MenuMecanicoView();
				miMenuM.getFrame().setVisible(true);
				break;
			case "jefe":
				//llamar a la GUI de menú inicial Jefe
				MenuJTallerView miMenuJT = new MenuJTallerView();
				miMenuJT.getFrame().setVisible(true);
				break;
			case "jefeTaller":
				//llamar a la GUI de menú inicial jefe de taller
				
				break;
			default:
				System.out.println("¿Comorrrrrrrr?");
		}			
	}

	/**
	 * Inicia la BBDD
	 */
	public void inibbdd() {
		controbbdd.iniciar();
	}
}
