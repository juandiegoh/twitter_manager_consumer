package com.twitter_manager.tweet.persistors

import com.twitter_manager.TweetCampaign

class SQLTweetCampaignRepository implements TweetCampaignRepository {

    @Override
    def save(TweetCampaign tweetCampaign) {
        return tweetCampaign.save(failOnError: true)
    }

    def findAllByCampaignAndTweetDateCreatedBetween(campaign, dateFrom, dateTo) {
        return TweetCampaign.findAllByCampaignAndTweetDateCreatedBetween(campaign, dateFrom, dateTo)
    }
}
