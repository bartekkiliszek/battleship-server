package pl.henkil.battleship.server.servers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GameServer implements Runnable {
    private static final Logger logger = LogManager.getLogger(GameServer.class);
    private final ExecutorService executorService = new ThreadPoolExecutor(
            1,
            100,
            0,
            TimeUnit.SECONDS,
            new SynchronousQueue<>()
    );

    @Override
    public void run() {
        logger.info("Game Server has started");

        try (ServerSocket serverSocket = new ServerSocket(9000)) {
            while (true) {
                Socket accept = serverSocket.accept();
                GameController gameController = new GameController(accept);
                try {
                    executorService.execute(gameController);
                    logger.info("Connection with address {} was established.", accept.getRemoteSocketAddress());
                } catch (RejectedExecutionException e) {
                    gameController.rejectConnection();
                    logger.warn("Connection with address {} was rejected. Thread pool reached maximum pool size.", accept.getRemoteSocketAddress());
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
