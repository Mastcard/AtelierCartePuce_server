package data;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

/**
 * 
 * @author lalatiana
 *
 */

public class JdbcConnection {
	
	/** The log. */
	private static Logger log = Logger.getLogger(Jdbcqueries.class);
	
	private static String host="localhost:5432";
	private static String base="carteApuce";
	private static String user="postgres";
	private static String password="password";

	private static String url;

	/**
	 * Singleton instance.
	 */
	private static Connection connection;

	public static Connection getConnection() throws FileNotFoundException {	
		try
		{	    
			url="jdbc:postgresql://" + host + "/" + base;
			    
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(url, user, password);
				log.debug("Data access successful");
			} catch (Exception e) {
				log.error("Connection failed : " + e.getMessage());			
			}
		}
		return connection;
	}

}
