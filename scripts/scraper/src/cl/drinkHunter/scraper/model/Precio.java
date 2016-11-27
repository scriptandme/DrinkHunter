package cl.drinkHunter.scraper.model;
import java.util.Date;

public class Precio {
	private String precio;
	private boolean bajo;
	private boolean oferta;
	private Date fechaLectura;
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public boolean isBajo() {
		return bajo;
	}
	public void setBajo(boolean bajo) {
		this.bajo = bajo;
	}
	public boolean isOferta() {
		return oferta;
	}
	public void setOferta(boolean oferta) {
		this.oferta = oferta;
	}
	public Date getFechaLectura() {
		return fechaLectura;
	}
	public void setFechaLectura(Date fechaLectura) {
		this.fechaLectura = fechaLectura;
	}
	
	
	
}
