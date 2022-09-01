package com.example.app.dto;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GlobalAchievementPercentagesDeserializer extends StdDeserializer<GlobalAchievementPercentages> {

    public GlobalAchievementPercentagesDeserializer() {
        this(null);
    }

    public GlobalAchievementPercentagesDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GlobalAchievementPercentages deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.getCodec().readTree(p);
        ArrayNode achievements = (ArrayNode) jsonNode.findValue("achievements");
        Map<String, Double> map = new HashMap<>();
        achievements.forEach(a -> map.put(a.get("name").textValue(), a.get("percent").doubleValue()));

        return new GlobalAchievementPercentages(map);
    }
}
