package com.twitter_manager.sentiment

public enum SentimentLanguage {
    ENGLISH('english'),
    SPANISH('spanish'),
    AUTO('AUTO')

    String description

    SentimentLanguage(String description) {
        this.description = description
    }
}