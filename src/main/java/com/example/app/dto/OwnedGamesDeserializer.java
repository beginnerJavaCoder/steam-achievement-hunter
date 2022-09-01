package com.example.app.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class OwnedGamesDeserializer extends StdDeserializer<OwnedGames> {

    public OwnedGamesDeserializer() {
        this(null);
    }

    public OwnedGamesDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public OwnedGames deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.getCodec().readTree(p);
        JsonNode games = jsonNode.findValue("games");
        List<JsonNode> gamesIds = games.findValues("appid");

        return new OwnedGames(gamesIds.stream().mapToInt(JsonNode::asInt).boxed().collect(Collectors.toList()));
    }
}
