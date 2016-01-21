/**
 *
 */
package model;

import org.apache.log4j.Logger;

/**
 * The Class User.
 * 
 * @author Adrien
 */
public class User {

	/** The log. */
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(User.class);
	
	/** The pin code. */
	private String userId;
	
	/** The seed. */
	private int seed;
	
	/** The login. */
	private String login;
	
	/** The password. */
	private String password;
	
	/** The path. */
	private String path;

	/**
	 * 
	 * Instantiates a new User.
	 *
	 */
	public User() {
	}
	
	/**
	 * Gets the pinCode.
	 * 
	 * @return the pinCode
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the pinCode.
	 * 
	 * @param pinCode the pinCode to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the seed.
	 * 
	 * @return the seed
	 */
	public int getSeed() {
		return seed;
	}

	/**
	 * Sets the seed.
	 * 
	 * @param seed the seed to set
	 */
	public void setSeed(int seed) {
		this.seed = seed;
	}

	/**
	 * Gets the login.
	 * 
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login.
	 * 
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the path.
	 * 
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the path.
	 * 
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "user id = " + userId + "\n"
				+ "seed = " + seed + "\n"
				+ "login = " + login + "\n"
				+ "password = " + password + "\n"
				+ "path = " + path;
	}

}
