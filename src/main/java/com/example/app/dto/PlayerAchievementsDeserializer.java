package com.example.app.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlayerAchievementsDeserializer extends StdDeserializer<PlayerAchievements> {

    public PlayerAchievementsDeserializer() {
        this(null);
    }

    public PlayerAchievementsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public PlayerAchievements deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.getCodec().readTree(p);
        ArrayNode achievements = (ArrayNode) jsonNode.findValue("achievements");
        List<AchievementInfo> achievementInfoList = new ArrayList<>();
        achievements.forEach(a -> achievementInfoList.add(new AchievementInfo(a.get("apiname").textValue(),
                a.get("achieved").intValue() == 1,
                new Date(a.get("unlocktime").asInt())))); //TODO time goes wrong

        return new PlayerAchievements(achievementInfoList);
    }
}
