package com.example.app.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CredentialsManager {

    private static final CredentialsManager INSTANCE = new CredentialsManager();

    private String key;
    private String steamId;

    private CredentialsManager() {

    }

    public static CredentialsManager getInstance() {
        return INSTANCE;
    }

    public void init(String path) throws IOException, CredentialsManagerInitializationException {

        if (key != null) {
            return;
        }

        Path filePath = Paths.get(path);
        List<String> properties = Files.readAllLines(filePath);
        for (String property : properties) {
            if (property.startsWith("API KEY")) {
                key = property.substring(8);
            }
            if (property.startsWith("ACCOUNT ID")) {
                steamId = property.substring(11);
            }
        }

        if (key == null || steamId == null) {
            throw new CredentialsManagerInitializationException((key == null ? "Key" : "Steam ID") + " is not initialized");
        }
    }

    public String getKey() {
        return key;
    }

    public String getSteamId() {
        return steamId;
    }
}
