package com.twitter_manager

import com.twitter_manager.sentiment.SentimentValue

class TweetCampaign {

    static belongsTo = [ campaign : Campaign ]

    String tweetId
    String text
    SentimentValue sentimentValue

    Long points
    Long retweets
    Long favorites
    Long followers
    String userId
    String country
    String countryCode
    Date tweetDateCreated

    static constraints = {
    }
}
