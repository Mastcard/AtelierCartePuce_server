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
public class ServerAuthenticationManager {

	/** The log. */
	private static Logger log = Logger.getLogger(ServerAuthenticationManager.class);
	
	/** The instance. */
	private static ServerAuthenticationManager instance = new ServerAuthenticationManager();

	/** The salt 1. */
	public final int SALT_1 = 384;
	
	/** The salt 2. */
	public final int SALT_2 = 571;
	
	/** The current seed. */
	public int currentSeed = 111; // default value, never used
	
	/** The current user. */
	public User currentUser;
	
	/**
	 * 
	 * Instantiates a new AuthenticationManager.
	 *
	 */
	private ServerAuthenticationManager() {
	}
	
	/**
	 * Gets the instance.
	 * 
	 * @return the instance
	 */
	public static ServerAuthenticationManager getInstance() {
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
