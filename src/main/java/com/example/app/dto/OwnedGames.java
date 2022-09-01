package com.example.app.dto;

import java.util.List;

public class OwnedGames {

    private final List<Integer> games;

    public OwnedGames(List<Integer> games) {
        this.games = games;
    }

    public List<Integer> getGames() {
        return games;
    }
}
