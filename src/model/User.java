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
	
	/** The iris shade. */
	private int irisShade;
	
	/** The average R. */
	private int averageR;
	
	/** The average G. */
	private int averageG;
	
	/** The average B. */
	private int averageB;

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
	 * Gets the irisShade.
	 * 
	 * @return the irisShade
	 */
	public int getIrisShade() {
		return irisShade;
	}

	/**
	 * Sets the irisShade.
	 * 
	 * @param irisShade the irisShade to set
	 */
	public void setIrisShade(int irisShade) {
		this.irisShade = irisShade;
	}

	/**
	 * Gets the averageR.
	 * 
	 * @return the averageR
	 */
	public int getAverageR() {
		return averageR;
	}

	/**
	 * Sets the averageR.
	 * 
	 * @param averageR the averageR to set
	 */
	public void setAverageR(int averageR) {
		this.averageR = averageR;
	}

	/**
	 * Gets the averageG.
	 * 
	 * @return the averageG
	 */
	public int getAverageG() {
		return averageG;
	}

	/**
	 * Sets the averageG.
	 * 
	 * @param averageG the averageG to set
	 */
	public void setAverageG(int averageG) {
		this.averageG = averageG;
	}

	/**
	 * Gets the averageB.
	 * 
	 * @return the averageB
	 */
	public int getAverageB() {
		return averageB;
	}

	/**
	 * Sets the averageB.
	 * 
	 * @param averageB the averageB to set
	 */
	public void setAverageB(int averageB) {
		this.averageB = averageB;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "user id = " + userId + "\n"
				+ "seed = " + seed + "\n"
				+ "login = " + login + "\n"
				+ "password = " + password + "\n"
				+ "irisShade = " + irisShade + "\n"
				+ "averageR = " + averageR + "\n"
				+ "averageG = " + averageG + "\n"
				+ "averageB = " + averageB;
	}

}
