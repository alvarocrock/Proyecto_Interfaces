package Models;

public class vXeData {
	protected float venta;
	protected String yAxis;
	protected String vendedor;
	
	/**
	 * constructor de dataset para gráfico de ventas por empleado
	 * @param venta > total ventas
	 * @param yAxis > nombre del eje Y
	 * @param vendedor > nombre del eje X
	 */
	public vXeData(float venta, String yAxis, String vendedor) {
		super();
		this.venta = venta;
		this.yAxis = yAxis;
		this.vendedor = vendedor;
	}
	
	/**
	 * getters y setters
	 * @return
	 */
	public float getVenta() {
		return venta;
	}

	public void setVenta(float venta) {
		this.venta = venta;
	}

	public String getyAxis() {
		return yAxis;
	}

	public void setyAxis(String yAxis) {
		this.yAxis = yAxis;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	

}
