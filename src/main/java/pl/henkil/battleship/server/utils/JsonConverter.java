package pl.henkil.battleship.server.utils;

import com.google.gson.Gson;

public class JsonConverter {
    private JsonConverter() {
    }

    public static <T> T from(String message, Class<T> classType) {
        return new Gson().fromJson(message, classType);
    }
}
