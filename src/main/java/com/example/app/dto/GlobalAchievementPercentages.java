package com.example.app.dto;

import java.util.Map;

public class GlobalAchievementPercentages {

    private final Map<String, Double> percentages;

    public GlobalAchievementPercentages(Map<String, Double> percentages) {
        this.percentages = percentages;
    }

    public Map<String, Double> getPercentages() {
        return percentages;
    }
}
