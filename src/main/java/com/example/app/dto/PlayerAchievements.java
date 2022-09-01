package com.example.app.dto;

import java.util.List;

public class PlayerAchievements {

    private final List<AchievementInfo> achievementInfoList;

    public PlayerAchievements(List<AchievementInfo> achievementInfoList) {
        this.achievementInfoList = achievementInfoList;
    }

    public List<AchievementInfo> getAchievementInfoList() {
        return achievementInfoList;
    }
}
