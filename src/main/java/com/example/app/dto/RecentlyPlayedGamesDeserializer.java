package com.example.app.dto;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class RecentlyPlayedGamesDeserializer extends StdDeserializer<RecentlyPlayedGames> {

    public RecentlyPlayedGamesDeserializer() {
        this(null);
    }

    protected RecentlyPlayedGamesDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public RecentlyPlayedGames deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.getCodec().readTree(p);
        JsonNode games = jsonNode.findValue("games");
        List<JsonNode> gamesIds = games.findValues("appid");

        return new RecentlyPlayedGames(gamesIds.stream().mapToInt(JsonNode::asInt).boxed().collect(Collectors.toList()));
    }
}
