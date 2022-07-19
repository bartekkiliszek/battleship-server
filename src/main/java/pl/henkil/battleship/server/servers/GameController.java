package pl.henkil.battleship.server.servers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.Socket;

public class GameController implements Runnable {
    private static final Logger logger = LogManager.getLogger(GameController.class);
    ConnectionHandler connectionHandler;

    public GameController(Socket accept) {
        try {
            connectionHandler = new ConnectionHandler(accept);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    @Override
    public void run() {
            String s = connectionHandler.read();
            connectionHandler.write(s);
    }

    public void rejectConnection() {
        connectionHandler.write("Connection rejected...");
    }
}
