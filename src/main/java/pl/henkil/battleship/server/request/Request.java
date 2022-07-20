package pl.henkil.battleship.server.request;

public class Request {
    private final RequestType type;
    private final Object body;

    private Request(RequestType type, Object body) {
        this.type = type;
        this.body = body;
    }

    public RequestType getType() {
        return type;
    }

    public Object getBody() {
        return body;
    }
}