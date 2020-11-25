package Models;

public class vXmData {
	protected String mes;
	protected float venta;
	
	/**
	 * constructor del dataset para ventas por meses
	 * @param mes > mes de la venta
	 * @param venta > total ventas del mes
	 */
	public vXmData(String mes, float venta) {
		super();
		this.mes = mes;
		this.venta = venta;
	}
	
	/**
	 * getters y setters
	 * @return
	 */
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public float getVenta() {
		return venta;
	}
	public void setVenta(float venta) {
		this.venta = venta;
	}
	
}
