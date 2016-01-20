/**
 *
 */
package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.apache.log4j.Logger;

/**
 * The Class UDPServer.
 * 
 * @author Adrien
 */
public class UDPServer extends Thread {

	/** The log. */
	private static Logger log = Logger.getLogger(UDPServer.class);

    /** The UDPServer. */
    private static UDPServer instance = new UDPServer();

    /** The Constant IP. */
    private final String IP = "192.168.1.16";

    /** The Constant PORT. */
    private final int PORT = 5555;

    /** The Constant BUFFER_SIZE. */
    private final int BUFFER_SIZE = 32;

    /** The buffer. */
    private byte buffer[] = new byte[BUFFER_SIZE];

    /** The received message. */
    private String receivedMessage;

    /**
     * Instantiates a new UDPServer.
     */
    private UDPServer() {
    }

    /**
     * Gets the instance.
     *
     * @return the instance
     */
    public static UDPServer getInstance() {
        return instance;
    }

    @Override
    public void run() {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(PORT);
            log.info("Socket opened :");

            while (true) {
                log.info("Reading..........");
                DatagramPacket data = new DatagramPacket(buffer, buffer.length);
                socket.receive(data);
                this.receivedMessage = new String(data.getData(), "UTF-8").substring(0, data.getLength());
                if (receivedMessage.trim().length() > 0) {

                    log.info("Just received : \"" + receivedMessage + "\"");
                    
                    /**
                    String response = NetworkCommunicator.buildResponseForReceivedMessage(receivedMessage);
                    data.setData(response.getBytes("UTF-8"));
                    socket.send(data);
                    */

                    /**
                     * TODO exploit received message
                     */
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets the received message.
     *
     * @return the received message
     */
    public String getReceivedMessage() {
        return receivedMessage;
    }

}