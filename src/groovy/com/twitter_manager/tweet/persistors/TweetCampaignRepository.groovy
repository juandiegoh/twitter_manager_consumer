package com.twitter_manager.tweet.persistors

import com.twitter_manager.TweetCampaign

public interface TweetCampaignRepository {

    def save(TweetCampaign tweetCampaign)

    def findAllByCampaignAndTweetDateCreatedBetween(campaign, dateFrom, dateTo)
}