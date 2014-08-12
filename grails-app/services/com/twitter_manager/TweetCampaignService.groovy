package com.twitter_manager

class TweetCampaignService {

    def tweetCampaignRepository
    def campaignRepository

    def findAllFromCampaignBetweenDates(campaignId, dateFrom, dateTo) {
        Campaign campaign = this.campaignRepository.findById(campaignId)
        if(campaign) {
            return this.tweetCampaignRepository.findAllByCampaignAndTweetDateCreatedBetween(campaign, dateFrom, dateTo)
        } else {
            return []
        }
    }
}
