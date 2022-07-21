package pl.henkil.battleship.server.utils;

import org.junit.jupiter.api.Test;
import pl.henkil.battleship.server.request.Request;
import pl.henkil.battleship.server.request.RequestType;

import static org.assertj.core.api.Assertions.assertThat;

class JsonConverterTest {

    @Test
    public void convertToInvitationGameRequest() {

        //given
        String message = """
                {
                  "type":"GAME_INVITATION",
                  "body":null
                }""";

        //when
        Request request = JsonConverter.from(message, Request.class);

        //then
        assertThat(request.getType()).isEqualTo(RequestType.GAME_INVITATION);
        assertThat(request.getBody()).isNull();

    }
}