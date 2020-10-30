package Main;

import Controllers.ControladorPrTaller;
import DAO.AbstractDAO;
import GUI.LoginView;

public class MainProTaller {

	private static ControladorPrTaller contro;
	private static LoginView login;
	
	public static void main(String[] args) {
		contro= new ControladorPrTaller();
		//contro.inibbdd();
		//login= new LoginView();

	}
}
