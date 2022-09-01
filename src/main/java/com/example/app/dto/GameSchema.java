package com.example.app.dto;

import java.util.List;

public class GameSchema {

    private final String name;
    private final List<IconPair> icons;

    public GameSchema(String name, List<IconPair> icons) {
        this.name = name;
        this.icons = icons;
    }

    public String getName() {
        return name;
    }

    public List<IconPair> getIcons() {
        return icons;
    }
}
