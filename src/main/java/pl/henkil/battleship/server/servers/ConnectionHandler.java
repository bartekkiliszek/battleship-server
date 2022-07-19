package pl.henkil.battleship.server.servers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler {
    private static final Logger logger = LogManager.getLogger(ConnectionHandler.class);
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;

    public ConnectionHandler(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintWriter(this.socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    public void write(String message) {
        out.println(message);
    }

    public String read() {
        try {
            return in.readLine();
        } catch (IOException e) {
            logger.error(e);
            return "";
        }
    }

    public void close() {
        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
