package Controllers;

public class ControladorPrTaller {
	ControBBDD controbbdd;
	
	public ControladorPrTaller() {
		controbbdd= new ControBBDD();
	}
	
	public void inibbdd() {
		controbbdd.iniciar();
	}
}
