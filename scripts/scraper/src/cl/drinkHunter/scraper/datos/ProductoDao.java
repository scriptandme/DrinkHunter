package cl.drinkHunter.scraper.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import cl.drinkHunter.scraper.model.Precio;
import cl.drinkHunter.scraper.model.Producto;

public class ProductoDao {
	
	public ProductoDao(){
		database = Database.getInstance();
	}
	
	Database database;
	
	public int agregarProducto(Producto producto){
		
		int id = -1;
		try {
			String insertTableSQL = "insert into producto("
					+ "nombre, "                            //1
					+ "volumen, "							//2
					+ "ultimo_precio, "                     //3
					+ "link, "                              //4
					+ "imagen, "                            //5
					+ "id_categoria, "                      //6
					+ "rate, "                              //7
					+ "creado, "                            //8
					+ "actualizado) "                       //9
					+"	values(?,?,?,?,?,?,?,now(),now())";
			Connection c=  database.obtenerConexion();
			PreparedStatement preparedStatement =c.prepareStatement(insertTableSQL,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, producto.getNombre());
			preparedStatement.setString(2, producto.getVolumen());
			preparedStatement.setInt(3, Integer.parseInt(producto.getUltimoPrecio()));
			
			preparedStatement.setString(4, producto.getLink());
			preparedStatement.setString(5, producto.getImagen());
			preparedStatement.setInt(6, producto.getCategoria());
			preparedStatement.setString(7, "0");
			
		
		
			
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
	        if(rs.next())
	        {
	            id = rs.getInt(1);
	        }
	        rs.close();
	        preparedStatement.close();
	       // c.close();
	        
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
			
		}
		System.out.println(id);
		return id;
	}
	
	public boolean actualizarUltimoPrecio(Producto producto){
		
		int r = 0;
		try {
			
			String updateTableSQL = "update producto "+
										"set ultimo_precio = ? ,"+
										"  actualizado=now()" +
										"where id_producto = ?";
										
			Connection c=  database.obtenerConexion();
			PreparedStatement preparedStatement =c.prepareStatement(updateTableSQL);
			preparedStatement.setString(1, producto.getUltimoPrecio());
			preparedStatement.setInt(2, producto.getId());
			
			r = preparedStatement.executeUpdate();
		
	        preparedStatement.close();
	        //c.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
		return r==1 ? true : false;
	}
	
	
	public Producto obtenerProducto(Producto producto){
		String selectSQL = "select "
				+ "nombre, volumen, ultimo_precio, link, imagen, id_categoria, rate, creado, actualizado "
				+" from producto where nombre = ? and link = ?";
		PreparedStatement preparedStatement = null;
		Producto producto2 = null ;
		try {
			Connection c=  database.obtenerConexion();
			preparedStatement = c.prepareStatement(selectSQL);
			preparedStatement.setString(1, producto.getNombre());
			preparedStatement.setString(2, producto.getLink());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				 producto2 = new Producto();
				 producto2.setId(rs.getInt("id"));
				 producto2.setImagen(rs.getString("imagen"));
				 producto2.setLink(rs.getString("link"));
				 producto2.setNombre(rs.getString("nombre"));
				 producto2.setUltimoPrecio(rs.getString("ultimo_precio"));
				 producto2.setVolumen(rs.getString("volumen"));
				 producto2.setCategoria(rs.getInt("id_categoria"));

			}
			rs.close();
			preparedStatement.close();
	       // c.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		}
		
		return producto2;
		
	}
	
	public int agregarPrecio(Producto producto, Precio precio){
		int id = -1;
		try {
		String insertTableSQL = "insert into precio ("
				+ "	id_producto, precio, oferta, bajo, fecha_lectura) "
				+ " values ( ?, ? , ? ,?, now())";
		Connection c=  database.obtenerConexion();
		PreparedStatement preparedStatement = c.prepareStatement(insertTableSQL,Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, producto.getId());
		preparedStatement.setString(2, precio.getPrecio());
		preparedStatement.setBoolean(3, precio.isOferta());
		preparedStatement.setBoolean(4, precio.isBajo());
		preparedStatement.executeUpdate();
		ResultSet rs = preparedStatement.getGeneratedKeys();
	        if(rs.next()){
	            id = rs.getInt(1);
	        }
	        
	        rs.close();
			preparedStatement.close();
	      //  c.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
			
		}
		System.out.println(id);
		return id;
	}
	
	
	public static void main(String args[]){
		
		ProductoDao productoDao = new ProductoDao();
		Producto producto = new Producto();
		producto.setLink("a");
		producto.setCategoria(1);
		producto.setImagen("b");
		producto.setLink("c");
		producto.setNombre("d");
		producto.setUltimoPrecio("1");
		producto.setVolumen("f");
		productoDao.agregarProducto(producto);
		
	}
}
