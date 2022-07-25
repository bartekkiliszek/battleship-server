package pl.henkil.battleship.server.servers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.henkil.battleship.server.request.Request;

import java.io.IOException;
import java.net.Socket;

public class GameController implements Runnable {
    private static final Logger logger = LogManager.getLogger(GameController.class);
    ConnectionHandler connectionHandler;

    public GameController(Socket accept) {
        try {
            connectionHandler = new ConnectionHandler(accept);
        } catch (IOException e) {
            logger.error("Error during creating Connection Handler.",e);
            try {
                accept.close();
            } catch (IOException ex) {
                logger.error("Error during closing connection.");
            }
        }
    }

    @Override
    public void run() {
        Request request = getRequest();

        connectionHandler.write("Received request type: " + request.getType());
    }

    private Request getRequest() {
        var message = connectionHandler.read();
        logger.info("Received message: {}", message);
        return Request.from(message);
    }

    public void rejectConnection() {
        connectionHandler.write("Connection rejected...");
    }
}
