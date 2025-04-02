package com.example.taskeight.utils;

import java.util.ArrayList;
import java.util.List;

public class SharedDataManager {
    private static SharedDataManager instance;
    private String userName = "";
    private String currentBibleVersion = "NIV";
    private String lastReadPassage = "";
    private int readingStreak = 0;
    private List<String> favoriteVerses = new ArrayList<>();
    private String dailyVerse = "Romans 10:9 - If you declare with your mouth, ‘Jesus is Lord,’ and believe in your heart that God raised Him from the dead, you will be saved.";


    private boolean darkMode = false;
    private boolean notificationsEnabled = true;
    private int fontSize = 16;
    private String sharedMessage = "";

    private SharedDataManager() {
    }

    public static SharedDataManager getInstance() {
        if (instance == null) {
            instance = new SharedDataManager();
        }
        return instance;
    }

    public String getCurrentBibleVersion() {
        return currentBibleVersion;
    }

    public void setCurrentBibleVersion(String version) {
        this.currentBibleVersion = version;
    }

    public String getLastReadPassage() {
        return lastReadPassage;
    }

    public void setLastReadPassage(String passage) {
        this.lastReadPassage = passage;
    }

    public int getReadingStreak() {
        return readingStreak;
    }

    public void incrementReadingStreak() {
        this.readingStreak++;
    }
    public List<String> getFavoriteVerses() {
        return favoriteVerses;
    }

    public void addFavoriteVerse(String verse) {
        if (!favoriteVerses.contains(verse)) {
            favoriteVerses.add(verse);
        }
    }

    public void removeFavoriteVerse(String verse) {
        favoriteVerses.remove(verse);
    }
    public String getDailyVerse() {
        return dailyVerse;
    }

    public void setDailyVerse(String verse) {
        this.dailyVerse = verse;
    }
    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int size) {
        this.fontSize = size;
    }

    public String getSharedMessage() {
        return sharedMessage;
    }

    public void setSharedMessage(String sharedMessage) {
        this.sharedMessage = sharedMessage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public boolean isNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setNotificationsEnabled(boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }

    public String getDataSummary() {
        StringBuilder builder = new StringBuilder();

        if (!userName.isEmpty()) {
            builder.append("User: ").append(userName).append("\n\n");
        }

        builder.append("Daily Verse: ").append(dailyVerse).append("\n\n");

        if (!lastReadPassage.isEmpty()) {
            builder.append("Last Read: ").append(lastReadPassage).append("\n\n");
        }

        builder.append("Bible Version: ").append(currentBibleVersion).append("\n");
        builder.append("Reading Streak: ").append(readingStreak).append(" days\n");
        builder.append("Font Size: ").append(fontSize).append("sp\n");

        if (!favoriteVerses.isEmpty()) {
            builder.append("\nFavorite Verses:\n");
            for (String verse : favoriteVerses) {
                builder.append("- ").append(verse).append("\n");
            }
        }

        if (!sharedMessage.isEmpty()) {
            builder.append("\nMessage: ").append(sharedMessage).append("\n");
        }

        return builder.toString();
    }
}