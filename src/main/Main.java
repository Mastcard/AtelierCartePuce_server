/**
 *
 */
package main;

import network.UDPServer;

/**
 * The Class Main.
 * 
 * @author Adrien
 */
public class Main {

	/**
	 * MAIN.
	 */
	public static void main(String[] args) {
		UDPServer.getInstance().start();
	}
	
}
