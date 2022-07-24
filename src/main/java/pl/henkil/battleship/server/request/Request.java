package pl.henkil.battleship.server.request;

import com.google.gson.JsonSyntaxException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.henkil.battleship.server.utils.JsonConverter;

public class Request {
    private static final Logger logger = LogManager.getLogger(Request.class);
    private final RequestType type;
    private final Object body;

    private Request(RequestType type, Object body) {
        this.type = type;
        this.body = body;
    }

    public static Request from(String massage) {
        try {
            var request = JsonConverter.from(massage, Request.class);
            if (request.type == null) {
                logger.error("Unknown request type. Message: {}", massage);
                return unknown();
            }
            return request;
        } catch (JsonSyntaxException e) {
            logger.error("Received wrong syntax message.", e);
            return unknown();
        }
    }

    private static Request unknown() {
        return new Request(RequestType.UNKNOWN, null);
    }

    public RequestType getType() {
        return type;
    }

    public Object getBody() {
        return body;
    }
}
