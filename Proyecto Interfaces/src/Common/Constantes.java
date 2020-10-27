package Common;

public abstract class Constantes {
	//estados
	public static final String BD = "BDTaller";
	public static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	public static final  String URL = "jdbc:mysql://localhost:3306/"+ BD + "?allowPublicKeyRetrieval=true&useSSL=false";
	public static final String USUARIO = "root";
	public static final String CLAVE = "AdminMysql1211$";
    
	//comportamientos
	public Constantes() {}
}
