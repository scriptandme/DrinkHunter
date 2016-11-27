package cl.drinkHunter.scraper.model;
import java.util.ArrayList;
import java.util.List;

public class Producto {
	
	public Producto(){
		this.precios = new ArrayList<Precio>();
	}
	private Integer id;
	private String nombre;
	private String volumen;
	private String ultimoPrecio;
	private String link;
	private String imagen;
	private int categoria;
	
	private List<Precio> precios;
	
	public List<Precio> getPrecios() {
		return precios;
	}


	public void addPrecio(Precio precio){
		this.precios.add(precio);
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getVolumen() {
		return volumen;
	}
	public void setVolumen(String volumen) {
		this.volumen = volumen;
	}
	public String getUltimoPrecio() {
		return ultimoPrecio;
	}
	public void setUltimoPrecio(String ultimoPrecio) {
		this.ultimoPrecio = ultimoPrecio;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public int getCategoria() {
		return categoria;
	}


	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
