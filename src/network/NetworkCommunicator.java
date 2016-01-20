/**
 *
 */
package network;

import org.apache.log4j.Logger;

import crypto.CryptoManager;
import engine.AuthenticationManager;
import model.User;

/**
 * The Class NetworkCommunicator.
 * 
 * @author Adrien
 */
public class NetworkCommunicator {

	/** The log. */
	private static Logger log = Logger.getLogger(NetworkCommunicator.class);
	
	/** The authentication manager. */
	private static AuthenticationManager authenticationManager = AuthenticationManager.getInstance();
	
	/**
	 * Build response for received message.
	 */
	public static String buildResponseForReceivedMessage(String message) {
		String response = "";
		String[] messageSplitted = message.split(" ");
		String messagePrefix = messageSplitted[0];
		User user;
		
		switch (messagePrefix) {
			case Constants.MESSAGE_PREFIX_HELLO:
				// Init communication
				authenticationManager.newUser();
				user = authenticationManager.currentUser;
				String userId = messageSplitted[1];
				user.setUserId(userId);
				
				// Hide seed and salts
				response = CryptoManager.hideSeedAndSalts(authenticationManager.currentSeed, 
						authenticationManager.salt_1, authenticationManager.salt_2);
				break;
			case Constants.MESSAGE_PREFIX_LOGIN:
				String userLogin = messageSplitted[1];
				user = authenticationManager.currentUser;
				user.setLogin(userLogin);
			case Constants.MESSAGE_PREFIX_PASSW:
				String userPassword = messageSplitted[1];
				user = authenticationManager.currentUser;
				user.setPassword(userPassword);
				/**
				 * TODO retrieve password from database
				 */
				//String passwordWithSeed = authenticationManager.currentSeed + "#" + "<password>";
				/**
				 * TODO if equals :
				 * 1. retrieve biometry information
				 * 2. send them as response
				 */
				/**
				 * TODO else
				 * 1. generates rendom information
				 * 2. send them as response
				 */
			default:
				log.error("Unknown message prefix : " + messagePrefix);
				break;
		}
		
		return response;
	}
	
}
