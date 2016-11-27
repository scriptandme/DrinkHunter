package cl.drinkHunter.scraper.manager;
import java.util.List;

import cl.drinkHunter.scraper.datos.ProductoDao;
import cl.drinkHunter.scraper.model.*;
public class ProductoManager {

	
	public void guardarProductos(List<Producto> productos){
		
		ProductoDao productoDao = new ProductoDao();
		for(Producto p : productos){
			Producto pTemp = productoDao.obtenerProducto(p);
			int id=0;
			if(pTemp==null){
				id = productoDao.agregarProducto(p);
				p.setId(id);
			}else{
				productoDao.actualizarUltimoPrecio(p);
				id=pTemp.getId();
			}
			productoDao.agregarPrecio(p, p.getPrecios().get(0));
			
		}
		
	}
	
	
}
