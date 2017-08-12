package com.teamnexters.volleytalk.player;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by MIN on 2017. 8. 8..
 */

public class PlayerListDeserializer implements JsonDeserializer<List<Map<Integer, List<Player>>>> {
    @Override
    public List<Map<Integer, List<Player>>> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
