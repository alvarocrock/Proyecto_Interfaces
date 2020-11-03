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
		
		System.out.println("fin ControladorTaller");
	}
	
	/**
	 * Comprueba el login
	 */
	public boolean comprobarlogin(String user,String contra) {
		return miUsuarioDAO.compobarlogin(user, contra);
	}
	
	

	/**
	 * Inicia la BBDD
	 */
	public void inibbdd() {
		controbbdd.iniciar();
	}
}
