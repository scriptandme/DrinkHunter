package cl.drinkHunter.scraper.datos;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class Database {

		//private String conexion = "Database=copetes_bd;Data Source=us-cdbr-azure-west-b.cleardb.com;User Id=b609ee83a5cc2b;Password=b6a8a29e";
	    private static Database INSTANCE = null;
	    private BasicDataSource basicDataSource =null;
	    private Connection c;
	    // Private constructor suppresses 
	    private Database(){}

	    // creador sincronizado para protegerse de posibles problemas  multi-hilo
	    // otra prueba para evitar instanciación múltiple 
	    private synchronized static void createInstance() {
	        if (INSTANCE == null) { 
	            INSTANCE = new Database();
	        }
	    }
	    
	    public Connection obtenerConexion() throws SQLException{
	    	
	    	if(c!=null && !c.isClosed()){
	    		return c;
	    	}
	    	 
	    	if(this.basicDataSource==null || !this.basicDataSource.getConnection().isClosed()){
	    	
	    		this.basicDataSource = new BasicDataSource();
		    	this.basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		    	this.basicDataSource.setUsername("root");
		    	this.basicDataSource.setPassword("wololo11");
		    	this.basicDataSource.setUrl("jdbc:mysql://us-cdbr-azure-west-b.cleardb.com/copetes_bd");
		    	this.basicDataSource.setMaxTotal(2);
		    	this.basicDataSource.setMinIdle(1);
	
		    	// Opcional. Sentencia SQL que le puede servir a BasicDataSource
		    	// para comprobar que la conexion es correcta.
		    	this.basicDataSource.setValidationQuery("select 1");
		    	c = this.basicDataSource.getConnection();
	    	}
	    	return c;
	    }
	    

	    public static Database getInstance() {
	        if (INSTANCE == null) createInstance();
	        return INSTANCE;
	    }
	
	
}
