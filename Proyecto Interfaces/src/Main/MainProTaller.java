package Main;

import Controllers.ControladorPrTaller;
import DAO.AbstractDAO;

public class MainProTaller {

	private static ControladorPrTaller contro;
	
	public static void main(String[] args) {
		contro= new ControladorPrTaller();
		contro.inibbdd();

	}
}
