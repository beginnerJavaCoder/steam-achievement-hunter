package com.example.app.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class PlayerSummariesDeserializer extends StdDeserializer<PlayerSummaries> {

    public PlayerSummariesDeserializer() {
        this(null);
    }

    public PlayerSummariesDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public PlayerSummaries deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.getCodec().readTree(p);
        JsonNode gameId = jsonNode.findValue("gameid");

        return new PlayerSummaries(gameId == null ? null : gameId.textValue());
    }
}
