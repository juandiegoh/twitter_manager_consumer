package com.twitter_manager.sentiment

public interface SentimentCalculator {

    SentimentValue calculateSentimentValue(String text, String query, SentimentLanguage sentimentLanguage)
}