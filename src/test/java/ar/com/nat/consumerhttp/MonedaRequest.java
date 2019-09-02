package ar.com.nat.consumerhttp;

public class MonedaRequest {
	
	private String cod_moneda;
	private String fecha_desde;
	private String fecha_hasta;
	
	public MonedaRequest(String cod_moneda, String fecha_desde, String fecha_hasta) {
		super();
		this.cod_moneda = cod_moneda;
		this.fecha_desde = fecha_desde;
		this.fecha_hasta = fecha_hasta;
	}
	public String getCod_moneda() {
		return cod_moneda;
	}
	public void setCod_moneda(String cod_moneda) {
		this.cod_moneda = cod_moneda;
	}
	public String getFecha_desde() {
		return fecha_desde;
	}
	public void setFecha_desde(String fecha_desde) {
		this.fecha_desde = fecha_desde;
	}
	public String getFecha_hasta() {
		return fecha_hasta;
	}
	public void setFecha_hasta(String fecha_hasta) {
		this.fecha_hasta = fecha_hasta;
	}

}
