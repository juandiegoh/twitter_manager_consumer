package com.twitter_manager.sentiment

public enum SentimentValue {

    NEUTRAL('NEUTRAL'),
    POSITIVE('POSITIVE'),
    NEGATIVE('NEGATIVE')

    String description

    SentimentValue(String description) {
        this.description = description
    }
}