/**
 *
 */
package network;

import org.apache.log4j.Logger;

import crypto.CryptoManager;
import crypto.EncodingEnum;
import data.Jdbcqueries;
import engine.ServerAuthenticationManager;
import model.User;
import util.Constants;

/**
 * The Class NetworkCommunicator.
 * 
 * @author Adrien
 */
public class ServerCommunicator {

	/** The log. */
	private static Logger log = Logger.getLogger(ServerCommunicator.class);
	
	/** The authentication manager. */
	private static ServerAuthenticationManager authenticationManager = ServerAuthenticationManager.getInstance();
	
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
				if (messageSplitted.length < 2) {
					userId = "0";
				} else {
					userId = messageSplitted[1];
				}
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

				// Retrieves password from database
				String password = Jdbcqueries.findPassword(user.getUserId(), user.getLogin());
				if (password == null) {
					log.error("An error occured during we retrieve password");
					response = Constants.MESSAGE_PREFIX_PASSW + " ko";
					break;
				}
				String passwordWithSeed = authenticationManager.currentSeed + "#" + password;
				String encryptedPasswordWithSeed = CryptoManager.encode(passwordWithSeed, EncodingEnum.SHA_256);
				
				// if equals => retrieve password and check
				if (user.getPassword().equals(encryptedPasswordWithSeed)) {
					log.debug("Passwords matched !");
					String path = Jdbcqueries.findPath(user.getUserId(), user.getLogin(), password);
					response = Constants.MESSAGE_PREFIX_PASSW + " " + path;
				} else {
					// else send "ko"
					log.error("Passwords don't match !");
					response = Constants.MESSAGE_PREFIX_PASSW + " ko";
				}
				break;
			case Constants.MESSAGE_PREFIX_ADMIN_HELLO:
				log.debug("Sending salts");
				response = Constants.MESSAGE_PREFIX_ADMIN_HELLO + " " + CryptoManager.hideSeedAndSalts(
						authenticationManager.currentSeed, 
						authenticationManager.SALT_1, 
						authenticationManager.SALT_2);
				break;
			case Constants.MESSAGE_PREFIX_ADMIN_ADD:
				userLogin = messageSplitted[1];
				userPassword = messageSplitted[2];
				user = new User();
				user.setLogin(userLogin);
				user.setPassword(userPassword);
				log.debug(user.toString());
				
				String userIdInDatabase = Jdbcqueries.findExistantLogin(user.getLogin());
				if (userIdInDatabase != null) {
					log.error("Login " + user.getLogin() + " already exists !");
					response = Constants.MESSAGE_PREFIX_ADMIN_ADD + " ko";
				} else {
					log.debug("Ok for login " + user.getLogin());
					response = Constants.MESSAGE_PREFIX_ADMIN_ADD + " ok";
				}
				break;
			case Constants.MESSAGE_PREFIX_ADMIN_BIO:
				userPath = messageSplitted[1];
				user.setPath(userPath);
				log.debug(user.toString());
				
				log.debug("Add user in database");
				boolean successful = Jdbcqueries.createUser(user.getLogin(), user.getPassword(), user.getPath());
				if (!successful) {
					log.error("Failed to add user");
					response = Constants.MESSAGE_PREFIX_ADMIN_BIO + " -1";
				} else {
					String userId = Jdbcqueries.findId(user.getLogin(), user.getPassword(), user.getPath());
					user.setUserId(userId);
					log.debug(user);
					response = Constants.MESSAGE_PREFIX_ADMIN_BIO + " " + userId;
				}
				break;
			default:
				log.error("Unknown message prefix : " + messagePrefix);
				break;
		}
		
		return response;
	}
	
}
