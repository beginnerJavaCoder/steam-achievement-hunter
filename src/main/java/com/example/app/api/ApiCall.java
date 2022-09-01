package com.example.app.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class ApiCall {

    private static final ApiCall INSTANCE = new ApiCall();
    private final CredentialsManager credentialsManager;

    private ApiCall() {
        credentialsManager = CredentialsManager.getInstance();
    }

    public static ApiCall getInstance() {
        return INSTANCE;
    }

    public byte[] getOwnedGames() {
        String url = "https://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=%s&steamid=%s";
        URI uri = URI.create(String.format(url, credentialsManager.getKey(), credentialsManager.getSteamId()));

        return call(uri);
    }

    public byte[] getRecentlyPlayedGames() {
        String url = "https://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v0001/?key=%s&steamid=%s";
        URI uri = URI.create(String.format(url, credentialsManager.getKey(), credentialsManager.getSteamId()));

        return call(uri);
    }

    public byte[] getPlayerSummaries() {
        String url = "https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=%s&steamids=%s";
        URI uri = URI.create(String.format(url, credentialsManager.getKey(), credentialsManager.getSteamId()));

        return call(uri);
    }

    public byte[] getSchemaForGame(Integer appId) {
        String url = "https://api.steampowered.com/ISteamUserStats/GetSchemaForGame/v0002/?key=%s&appid=%d";
        URI uri = URI.create(String.format(url, credentialsManager.getKey(), appId));

        return call(uri);
    }

    public byte[] getGlobalAchievementPercentagesForApp(Integer appId) {
        String url = "https://api.steampowered.com/ISteamUserStats/GetGlobalAchievementPercentagesForApp/v0002/?gameid=%d";
        URI uri = URI.create(String.format(url, appId));

        return call(uri);
    }

    public byte[] getPlayerAchievements(Integer appId) {
        String url = "https://api.steampowered.com/ISteamUserStats/GetPlayerAchievements/v0001/?key=%s&steamid=%s&appid=%d";
        URI uri = URI.create(String.format(url, credentialsManager.getKey(), credentialsManager.getSteamId(), appId));

        return call(uri);
    }

    //TODO decrease the amount of unnecessary initializations
    private byte[] call(URI uri) {

        HttpRequest request = HttpRequest.newBuilder(uri)
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .GET()
                .build();

        HttpResponse<String> response;

        try {
            response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println("There is an error during sending a request or getting a response: " + e);
            return new byte[0];
        }

        return response.body().getBytes();
    }
}
