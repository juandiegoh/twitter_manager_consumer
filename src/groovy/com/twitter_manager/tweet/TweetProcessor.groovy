package com.twitter_manager.tweet

import com.twitter_manager.Tweet
import com.twitter_manager.campaign.repositories.CampaignRepository

class TweetProcessor {

    CampaignRepository campaignsRepository
    def tweetRepository

    def processTweet(Tweet tweet) {
        def campaigns = this.campaignsRepository.getTurnedOnCampaigns()
        campaigns.each { c ->
            c.addTweetToCampaignIfValid(tweet)
        }

        if(tweet.isInCampaign()) {
            tweetRepository.persist(tweet)
        }
    }
}
