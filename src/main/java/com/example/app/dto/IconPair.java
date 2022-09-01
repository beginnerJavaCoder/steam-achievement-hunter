package com.example.app.dto;

public class IconPair {

    private final String name;
    private final String achieved;
    private final String locked;

    public IconPair(String name, String achieved, String locked) {
        this.name = name;
        this.achieved = achieved;
        this.locked = locked;
    }

    public String getName() {
        return name;
    }

    public String getAchieved() {
        return achieved;
    }

    public String getLocked() {
        return locked;
    }
}
