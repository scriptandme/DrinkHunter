import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.google.gson.Gson;
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;

import cl.drinkHunter.scraper.manager.ProductoManager;
import cl.drinkHunter.scraper.model.Precio;
import cl.drinkHunter.scraper.model.Producto;



public class ScraperBotCopetes {
	public static void main(String args[]){
		
		String url = "http://www.tottus.cl/tottus/productListFragment/Bebidas-y-Licores-Cervezas-Importadas/_/N-lpbtaj?No=0&Nrpp=1000";
		String url2 = "http://www.tottus.cl/tottus/productListFragment/_/N-io8rzi?No=0&Nrpp=1000";
		String url3 = "http://www.tottus.cl/tottus/productListFragment/_/N-1hvbhcb?No=0&Nrpp=1000";
		String url4 = "http://www.tottus.cl/tottus/productListFragment/_/N-so55ux?No=0&Nrpp=1000";
		String url5 = "http://www.tottus.cl/tottus/productListFragment/_/N-121o33h?No=0&Nrpp=1000";
		String url6 = "http://www.tottus.cl/tottus/productListFragment/_/N-1j7326k?No=0&Nrpp=1000";
		String url7 = "http://www.tottus.cl/tottus/productListFragment/_/N-2o8icq?No=0&Nrpp=1000";
		String url8 = "http://www.tottus.cl/tottus/productListFragment/_/N-10apmgd?No=0&Nrpp=1000";
		String url9 = "http://www.tottus.cl/tottus/productListFragment/_/N-192c18h?No=0&Nrpp=1000";
		String url10 = "http://www.tottus.cl/tottus/productListFragment/_/N-1vpqukk?No=0&Nrpp=1000";
		
		
		ScraperBotCopetes t = new ScraperBotCopetes();
		
		
		List<Producto> importadas = t.obtenerListaProductos("Cervezas - Importadas", url);
	
		
		List<Producto> clasicasPremium = t.obtenerListaProductos("Cervezas - Clasicas premium", url2);
		List<Producto> clasicas = t.obtenerListaProductos("Cervezas - Clasicas", url3);
		List<Producto> artesanales = t.obtenerListaProductos("Cervezas - Artesanales", url4);
		List<Producto> coctel = t.obtenerListaProductos("Cervezas - Coctel", url5);
		List<Producto> sinAlcohol = t.obtenerListaProductos("Cervezas - Sin Alcohol", url6);
		List<Producto> destilados = t.obtenerListaProductos("Destilados", url7);
		List<Producto> vinoTinto = t.obtenerListaProductos("Vinos - Tinto", url8);
		List<Producto> vinoBlanco = t.obtenerListaProductos("Vinos - Blanco", url9);
		List<Producto> espumante = t.obtenerListaProductos("Espumante", url10);

		List<Producto> productos = new ArrayList<Producto>();
		productos.addAll(importadas);
		productos.addAll(clasicasPremium);
		productos.addAll(clasicas);
		productos.addAll(artesanales);
		productos.addAll(coctel);
		productos.addAll(sinAlcohol);
		productos.addAll(destilados);
		productos.addAll(vinoBlanco);
		productos.addAll(vinoTinto);
		productos.addAll(espumante);
		
		
		String jsonCHelas = t.listProductosToJson(productos);
		
		System.out.println(jsonCHelas);
		
		t.stringtoFile(jsonCHelas, "chelasydestiladosyvinos.json");
		ProductoManager productoManager  = new ProductoManager();
		productoManager.guardarProductos(productos);
	}
	
	public List<Producto> obtenerListaProductos(String categoria, String url){
		/*if(false){
			Properties props= new Properties(System.getProperties());
			props.put("http.proxySet", "true");
			props.put("http.proxyHost", "proxy1.idev.cl");
			props.put("http.proxyPort", "8080");
			props.put("https.proxySet", "true");
			props.put("https.proxyHost", "proxy1.idev.cl");
			props.put("https.proxyPort", "8080");
			Properties newprops = new Properties(props);
			System.setProperties(newprops);
		}*/
		List<Producto> productos = new ArrayList<Producto>();
		try{
			  UserAgent userAgent = new UserAgent(); 
			  userAgent.visit(url);
			  Elements elements = userAgent.doc.findEvery("div id=item-product-caption_[0-9]{8}");
			  
			  for(Element e : elements){
				  Producto producto = new Producto();
				  String titulo = e.findFirst("h5").getFirst("span").getText();
				  Element volumen = e.findFirst("div class=statement");
				  Element preciov =e.findFirst("span class=active-price").findFirst("span");
				  Element link =e.findFirst("<div class=caption-bottom-wrapper>").findFirst("a");
				  Element imagen = e.findFirst("<span class=thumbnail").findFirst("img");
				  String imagen2 = imagen.getAt("src");
				  
				  producto.setCategoria(this.getCategoria(categoria));
				  producto.setNombre(titulo);
				  producto.setLink(link.getAt("href"));
				  producto.setUltimoPrecio(preciov.innerHTML().replace("$", "").replace(".", "").trim());
				  producto.setVolumen(volumen.innerHTML().trim());
				  producto.setImagen(imagen2);
				  Precio precio = new Precio();
				  precio.setBajo(false);
				  precio.setOferta(false);
				  precio.setPrecio(preciov.innerHTML().trim().replace("$", "").replace(".", "").trim());
				  precio.setFechaLectura(new Date());
				  producto.addPrecio(precio);
				  productos.add(producto);	
				  System.out.println(titulo + "-" + volumen.innerHTML()+ "-" + preciov.innerHTML().trim()+ "-" + link.getAt("href") + " - "+imagen2);
			
			  }
			  
			
			
		
			  return productos;
		}catch(JauntException e){      
			System.err.println(e);
		}
		return null;
	}


	public String listProductosToJson(List<Producto> productos){
			Gson gson = new Gson();
		  
		 
		
		return gson.toJson(productos);
	}

	
	public boolean stringtoFile(String json,String archivo){
		try(  
				PrintWriter out = new PrintWriter( "C:\\eventloop\\"+archivo )  ){
				out.println( json );
				out.close();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public int getCategoria(String categoria){
		/*
1,"Cervezas - Importadas"
2,"Cervezas - Clasicas premium"
3,"Cervezas - Clasicas"
4,"Cervezas - Artesanales"
5,"Cervezas - Coctel"
6,"Cervezas - Sin Alcohol"
7,"Destilados"
8,"Vinos - Tinto"
9,"Vinos - Blanco"
10,"Espumante"


		 * */
		switch (categoria) {
		case "Cervezas - Importadas":
			return 1;
			
		case "Cervezas - Clasicas premium":
			return 2;
			
		case "Cervezas - Clasicas":
			
			return 3;	
		case "Cervezas - Artesanales":
		
			return 4;
			
		case "Cervezas - Coctel":
			
			return 5;
		case "Cervezas - Sin Alcohol":
			
			return 6;	
			
		case "Destilados":
			
			return 7;
		case "Vinos - Tinto":
			
			return 8;
		case "Vinos - Blanco":
			
			return 9;
		case "Espumante":
			
			return 10;
		default:
			return -1;
			
		}
		
		
		
	}
	
}
