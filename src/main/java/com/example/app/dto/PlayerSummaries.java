package com.example.app.dto;

public class PlayerSummaries {

    //TODO think about Integer or String for appIds (it seems API provides both formats and we have to choose what is
    // more proper for us)
    private final String gameID;

    public PlayerSummaries(String gameID) {
        this.gameID = gameID;
    }

    public String getGameID() {
        return gameID;
    }
}
