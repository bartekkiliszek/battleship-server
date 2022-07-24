package pl.henkil.battleship.server.request;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.henkil.battleship.server.request.RequestType.*;
import static pl.henkil.battleship.server.request.RequestType.UNKNOWN;

class RequestTest {

    @ParameterizedTest
    @MethodSource("correctMessages")
    void createValidRequest(String message, RequestType requestType) {

        //when
        //given

        Request request = Request.from(message);

        //then
        assertThat(request.getType()).isEqualTo(requestType);
    }

    private static Stream<Arguments> correctMessages() {
        return Stream.of(
                Arguments.of("{\"type\":\"GAME_INVITATION\",body\":null}", GAME_INVITATION),
                Arguments.of("{\"type\":\"SHOT\",\"body\":{\"row\":\"A\",\"column\":2}}", SHOT),
                Arguments.of("{\"type\":\"SHOT_REQUEST\",\"body\":null}", SHOT_REQUEST),
                Arguments.of("{\"type\":\"RESULT\",\"body\":\"HIT\"}", RESULT),
                Arguments.of("{\"type\":\"BOARD\",\"body\":{\"four\":\"B3-B6\",\"three\":[\"E10-G10\",\"F3-H3\"],\"two\":[\"A9-B9\",\"D7-D8\",\"I6-I7\"],\"one\":[\"D2\",\"D5\",\"F7\",\"I9\"]}}", BOARD)
        );
    }


    @ParameterizedTest
    @MethodSource("incorrectMessages")
    void createInvalidRequest(String message) {

        //when
        //given

        Request request = Request.from(message);

        //then
        assertThat(request.getType()).isEqualTo(UNKNOWN);
    }

    private static Stream<Arguments> incorrectMessages() {
        return Stream.of(
                Arguments.of("{\"type\":\"GAME_INVITATION\",body\":null", GAME_INVITATION),
                Arguments.of("{\"type\":\"SHOT\"\"body\":{\"row\":\"A\",\"column\":2}}", SHOT),
                Arguments.of("{\"type\":\"SHOT_REQUEST\",\"body\"null}", SHOT_REQUEST),
                Arguments.of("{\"type\":\"RESUL\",\"body\":\"HIT\"}", RESULT),
                Arguments.of("{\"typ\":\"BOARD\",\"body\":}", BOARD)
        );
    }
}