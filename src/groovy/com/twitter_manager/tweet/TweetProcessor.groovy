package com.twitter_manager.tweet

import com.twitter_manager.Campaign
import com.twitter_manager.TweetCampaign
import com.twitter_manager.campaign.repositories.CampaignRepository
import com.twitter_manager.sentiment.SentimentCalculator
import com.twitter_manager.sentiment.SentimentLanguage

class TweetProcessor {

    CampaignRepository campaignsRepository
    def tweetRepository
    def tweetFactory
    SentimentCalculator sentimentCalculator
    def tweetPointsCalculator

    def processTweet(TweetDTO tweetDTO) {
        def campaigns = this.campaignsRepository.getTurnedOnCampaigns()
        campaigns.each { Campaign campaign ->
            if(campaign.isValidTweet(tweetDTO)) {
                TweetCampaign tweetCampaign = tweetFactory.createTweetCampaign(tweetDTO, campaign)
                addSentimentToTweet(tweetCampaign, campaign)
                addPointToTweet(tweetCampaign)
                tweetRepository.save(tweetCampaign)
            }
        }
    }

    def addSentimentToTweet(TweetCampaign tweet, Campaign campaign) {
        def sentimentValue = this.sentimentCalculator.calculateSentimentValue(tweet.getText(), campaign.getKeywords(), SentimentLanguage.AUTO)
        tweet.setSentimentValue(sentimentValue)
    }

    def addPointToTweet(TweetCampaign tweetCampaign) {
        def tweetPoints = this.tweetPointsCalculator.calculatePointsForTweet(tweetCampaign)
        tweetCampaign.setPoints(tweetPoints)
    }
}
