/**
 *
 */
package engine;

import org.apache.log4j.Logger;

import crypto.CryptoManager;
import model.User;

/**
 * The Class AuthentificationManager.
 * 
 * @author Adrien
 */
public class AuthenticationManager {

	/** The log. */
	private static Logger log = Logger.getLogger(AuthenticationManager.class);
	
	/** The instance. */
	private static AuthenticationManager instance = new AuthenticationManager();

	/** The salt 1. */
	public final int SALT_1 = 384;
	
	/** The salt 2. */
	public final int SALT_2 = 571;
	
	/** The current seed. */
	public int currentSeed;
	
	/** The current user. */
	public User currentUser;
	
	/**
	 * 
	 * Instantiates a new AuthenticationManager.
	 *
	 */
	private AuthenticationManager() {
	}
	
	/**
	 * Gets the instance.
	 * 
	 * @return the instance
	 */
	public static AuthenticationManager getInstance() {
		return instance;
	}
	
	/**
	 * Update the current seed.
	 */
	public void newUser() {
		log.debug("Communication started with a User");
		currentUser = new User();
		currentSeed = CryptoManager.generateSeed();
	}
	
}
