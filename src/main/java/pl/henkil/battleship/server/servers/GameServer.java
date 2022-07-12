package pl.henkil.battleship.server.servers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameServer implements Runnable {
    private static final Logger logger = LogManager.getLogger(GameServer.class);

    @Override
    public void run() {
        logger.info("Game Server has started");
    }
}
