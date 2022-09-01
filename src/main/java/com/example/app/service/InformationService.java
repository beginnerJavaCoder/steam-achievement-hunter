package com.example.app.service;

import com.example.app.api.ApiCall;
import com.example.app.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.List;

public class InformationService {

    private final ObjectMapper objectMapper;
    private final ApiCall apiCall;

    public InformationService() {
        objectMapper = getConfiguredObjectMapper();
        apiCall = ApiCall.getInstance();
    }

    private ObjectMapper getConfiguredObjectMapper() {
        return JsonMapper.builder()
                .build()
                .registerModule(new SimpleModule().addDeserializer(PlayerSummaries.class, new PlayerSummariesDeserializer()))
                .registerModule(new SimpleModule().addDeserializer(OwnedGames.class, new OwnedGamesDeserializer()))
                .registerModule(new SimpleModule().addDeserializer(RecentlyPlayedGames.class, new RecentlyPlayedGamesDeserializer()))
                .registerModule(new SimpleModule().addDeserializer(GameSchema.class, new GameSchemaDeserializer()))
                .registerModule(new SimpleModule().addDeserializer(GlobalAchievementPercentages.class, new GlobalAchievementPercentagesDeserializer()))
                .registerModule(new SimpleModule().addDeserializer(PlayerAchievements.class, new PlayerAchievementsDeserializer()));
    }

    public String getCurrentGame() throws IOException {
        byte[] summaries = apiCall.getPlayerSummaries();
        return objectMapper.readValue(summaries, PlayerSummaries.class).getGameID();
    }

    public List<Integer> getOwnedGames() throws IOException {
        byte[] ownedGames = apiCall.getOwnedGames();
        return objectMapper.readValue(ownedGames, OwnedGames.class).getGames();
    }

    public List<Integer> getRecentlyPlayedGames() throws IOException {
        byte[] recentlyPlayedGames = apiCall.getRecentlyPlayedGames();
        return objectMapper.readValue(recentlyPlayedGames, RecentlyPlayedGames.class).getGames();
    }

    public GameSchema getGameSchema(Integer appId) throws IOException {
        byte[] schemaForGame = apiCall.getSchemaForGame(appId);
        return objectMapper.readValue(schemaForGame, GameSchema.class);
    }

    public GlobalAchievementPercentages getGlobalAchievementPercentages(Integer appId) throws IOException {
        byte[] globalAchievementPercentagesForApp = apiCall.getGlobalAchievementPercentagesForApp(appId);
        return objectMapper.readValue(globalAchievementPercentagesForApp, GlobalAchievementPercentages.class);
    }

    public PlayerAchievements getPlayerAchievements(Integer appId) throws IOException {
        byte[] playerAchievements = apiCall.getPlayerAchievements(appId);
        return objectMapper.readValue(playerAchievements, PlayerAchievements.class);
    }
}
