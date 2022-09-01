package com.example.app.dto;

import java.util.Date;

public class AchievementInfo {

    private final String name;
    private final Boolean achieved;
    private final Date unlockTime;

    public AchievementInfo(String name, Boolean achieved, Date unlockTime) {
        this.name = name;
        this.achieved = achieved;
        this.unlockTime = unlockTime;
    }

    public String getName() {
        return name;
    }

    public Boolean getAchieved() {
        return achieved;
    }

    public Date getUnlockTime() {
        return unlockTime;
    }
}
