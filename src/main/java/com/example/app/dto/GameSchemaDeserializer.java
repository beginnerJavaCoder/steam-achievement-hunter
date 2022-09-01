package com.example.app.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameSchemaDeserializer extends StdDeserializer<GameSchema> {

    public GameSchemaDeserializer() {
        this(null);
    }

    protected GameSchemaDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GameSchema deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.getCodec().readTree(p);
        JsonNode gameName = jsonNode.findValue("gameName");
        ArrayNode achievements = (ArrayNode) jsonNode.findValue("achievements");
        List<IconPair> icons = new ArrayList<>();
        Iterator<JsonNode> elements = achievements.elements();
        while (elements.hasNext()) {
            JsonNode achievement = elements.next();
            IconPair pair = new IconPair(achievement.get("name").textValue(),
                    achievement.get("icon").textValue(),
                    achievement.get("icongray").textValue());
            icons.add(pair);
        }

        return new GameSchema(gameName.textValue(), icons);
    }
}
