package Common;


public abstract class Constantes{
	//estados
	public static final String BD = "proyecto";
	public static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	public static final  String URL = "jdbc:mysql://localhost:3306/"+ BD + "?allowPublicKeyRetrieval=true&useSSL=false";
	public static final String USUARIO = "root";
	public static final String CLAVE = "AdminMysql1211$";
	public static final String SQLCREATE = "src/Scripts/scriptBBDD.sql";
	public static final String SQLCARGA = "src/Scripts/rellenaDummy.sql";
	public static int idCliGlobal=0;
	public static int idVehGlobal=0;

	//comportamientos
	public Constantes() {}
	
	/**
	 * enumerado para usar en el cálculo de DNI
	 * @author Roque
	 *
	 */
	public enum DigitoDni {
		T,R,W,A,G,M,Y,F,P,D,X,B,N,J,Z,S,Q,V,H,L,C,K,E
		}
	/**
	 * enumerado permitidos en un NIE
	 * @author Roque
	 *
	 */
	public enum DigitoNie {
		X,Y,Z
		}
	/**
	 * enumerado tipos de vehículos
	 * 	 * @author Roque
	 *
	 */
	public enum TiposVeh {
		COCHE,MOTOCICLETA,CICLOMOTOR
		}
}
