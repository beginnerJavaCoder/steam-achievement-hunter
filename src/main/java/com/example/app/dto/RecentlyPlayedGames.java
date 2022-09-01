package com.example.app.dto;

import java.util.List;

public class RecentlyPlayedGames {

    private final List<Integer> games;

    public RecentlyPlayedGames(List<Integer> games) {
        this.games = games;
    }

    public List<Integer> getGames() {
        return games;
    }
}
