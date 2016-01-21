/**
 *
 */
package network;

import org.apache.log4j.Logger;

import crypto.CryptoManager;
import crypto.EncodingEnum;
import engine.AuthenticationManager;
import model.User;
import util.Constants;

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
	
	/** The user. */
	private static User user;
	
	/** The user id. */
	private static String userId;
	
	/** The user login. */
	private static String userLogin;
	
	/** The user password. */
	private static String userPassword;
	
	/** The user path. */
	private static String userPath;
	
	/**
	 * Build response for received message.
	 */
	public static synchronized String executeActionAndBuildResponse(String message) {
		String response = "";
		String[] messageSplitted = message.split(" ");
		String messagePrefix = messageSplitted[0];
		
		switch (messagePrefix) {
			case Constants.MESSAGE_PREFIX_HELLO:
				// Init communication
				authenticationManager.newUser();
				user = authenticationManager.currentUser;
				userId = messageSplitted[1];
				user.setUserId(userId);
				
				// Hide seed and salts
				response = Constants.MESSAGE_PREFIX_HELLO + " " + CryptoManager.
						hideSeedAndSalts(authenticationManager.currentSeed, 
						authenticationManager.SALT_1, authenticationManager.SALT_2);
				break;
			case Constants.MESSAGE_PREFIX_LOGIN:
				userLogin = messageSplitted[1];
				user = authenticationManager.currentUser;
				user.setLogin(userLogin);
				response = Constants.MESSAGE_PREFIX_LOGIN + " received well";
				break;
			case Constants.MESSAGE_PREFIX_PASSW:
				userPassword = messageSplitted[1];
				user = authenticationManager.currentUser;
				user.setPassword(userPassword);
				log.debug(user.toString());
				/**
				 * TODO retrieve password from database
				 */
				String passwordWithSeed = authenticationManager.currentSeed + "#" + "<password>";
				String encryptedPasswordWithSeed = CryptoManager.encode(passwordWithSeed, EncodingEnum.SHA_256);
				/**
				 * TODO if equals :
				 * a. retrieve path from database
				 * 
				 * else
				 * b. return ko
				 */
				/**
				 * TODO response = Constants.MESSAGE_PREFIX_PASSW + path;
				 */
				break;
			case Constants.MESSAGE_PREFIX_ADMIN_ADD:
				userLogin = messageSplitted[1];
				userPassword = messageSplitted[2];
				user = new User();
				user.setLogin(userLogin);
				user.setPassword(userPassword);
				log.debug(user.toString());
				
				/**
				 * TODO check if login already exists
				 */
				response = Constants.MESSAGE_PREFIX_ADMIN_ADD + " received well";
				break;
			case Constants.MESSAGE_PREFIX_ADMIN_BIO:
				userPath = messageSplitted[1];
				user.setPath(userPath);
				log.debug(user.toString());
				/**
				 * TODO save user in database
				 */
				/**
				 * TODO retrieve the user id
				 */
				/**
				 * TODO response = Constants.MESSAGE_PREFIX_ADMIN_BIO + " userid"
				 * TODO if addition failed : userid = -1
				 */
			default:
				log.error("Unknown message prefix : " + messagePrefix);
				break;
		}
		
		return response;
	}
	
}
