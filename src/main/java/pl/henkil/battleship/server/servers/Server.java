package pl.henkil.battleship.server.servers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final Logger logger = LogManager.getLogger(Server.class);
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public void start() {
        logger.info("Server has started");
        executorService.execute(new GameServer());
        executorService.execute(new MessageServer());
    }
}
