package data;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
/**
 * 
 * @author lalatiana
 *
 */

public class Jdbcqueries {
	
	/** The log. */
	private static Logger log = Logger.getLogger(Jdbcqueries.class);
	
	/**
	 * findLogin
	 * pour récupérer le login de l'utilisateur
	 * @param id
	 * @return le login
	 */
	public static String findLogin(String id) {
		String login = null;
		
		try {
			String query = "SELECT login FROM cap.user WHERE idUser='" + id + "';";
			log.debug(query);
			ResultSet resultSet = getStatement().executeQuery(query);
			if (resultSet.next()) {
				login = resultSet.getString("login");
				log.debug("Login found " + login);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return login;
	}
	
	/**
	 * findPassword
	 * pour récupérer le mot de passe de l'utilisateur
	 * @param id
	 * @param login
	 * @return le password
	 */
	public static String findPassword(String id,String login) {
		String password = null;
		
		try {
			String query = "SELECT password FROM cap.user WHERE idUser='" + id + "'AND login ='"+login+"' ;";
			log.debug(query);
			ResultSet resultSet = getStatement().executeQuery(query);
			if (resultSet.next()) {
				password = resultSet.getString("password");
				log.debug("Password found " + password);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return password;
	}
	
	/**
	 * findPath
	 * pour récupérer le chemin du fichier contenant les données biométriques
	 * @param id
	 * @param login
	 * @param password
	 * @return le path
	 */
	public static String findPath(String id,String login,String password) {
		String path = null;
		
		try {
			String query = "SELECT pathbiometric FROM cap.user WHERE idUser='" + id + "'AND login ='"+login+"' AND password='"+password+"' ;";
			log.debug(query);
			ResultSet resultSet = getStatement().executeQuery(query);
			if (resultSet.next()) {
				path = resultSet.getString("pathbiometric");
				log.debug("Path found " + path);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return path;
	}
	
	/**
	 *findId
	 *pour récupérer l'id d'un nouvel utilisateur inséré en base
	 * @param login
	 * @param password
	 * @param path
	 * @return l'id
	 */
	public static String findId(String login,String password,String path) {
		String id = null;
		
		try {
			String query = "SELECT iduser FROM cap.user WHERE login ='"+login+"' AND password='"+password+"' AND pathbiometric='"+path+"' ;";
			log.debug(query);
			ResultSet resultSet = getStatement().executeQuery(query);
			if (resultSet.next()) {
				id = resultSet.getString("iduser");
				log.debug("Id found : " + id);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return id;
	}
	
	/**
	 * findExistantLogin
	 * pour récupérer l'id d'un utilisateur si le login existe. Vérifier si le login appartient déjà à un utilisateur
	 * @param login
	 * @param password
	 * @param path
	 * @return l'id
	 */
	public static String findExistantLogin(String login) {
		String id = null;
		
		try {
			String query = "SELECT iduser FROM cap.user WHERE login ='"+login+"';";
			log.debug(query);
			ResultSet resultSet = getStatement().executeQuery(query);
			
			// At least there's one data
			if (resultSet.next()) {
				id = resultSet.getString("iduser");
				log.debug("Id found with same login : " + id);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return id;
	}
	
	/**
	 * createUser
	 * pour inserer un nouvel utilisteur en base
	 * @param login
	 * @param password
	 * @param path
	 */
	
	public static boolean createUser(String login,String password,String path) {
		int res;
		boolean state = false;
		
		try {
			String query = "INSERT INTO cap.user (login,password,pathbiometric) "
					+ "VALUES ('"+login+"','"+password+"','"+path+"');";
			log.debug(query);
			Statement statement = getStatement();
			res =statement.executeUpdate(query);
			statement.close();
			log.debug("statement: "+res);
			if(res == 1) {
				state = true;
				log.info("New user added!");
			}
		} catch (SQLException se) {
		}
		
		return state;
	}
	
	/**
	 * getStatement
	 * pour se connecter à la base
	 * @return connection
	 */
	private static Statement getStatement() {
		try {
			return JdbcConnection.getConnection().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
		} catch (SQLException e) {
			log.error(e.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
