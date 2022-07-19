package pl.henkil.battleship.server.servers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MessageServer implements Runnable {
    private static final Logger logger = LogManager.getLogger(MessageServer.class);

    @Override
    public void run() {
        logger.info("Message Server has started");
    }
}
