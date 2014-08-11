package com.twitter_manager.campaign

import com.twitter_manager.Campaign
import com.twitter_manager.rules.AndRule
import com.twitter_manager.rules.Rule

class CampaignFactory {

    def createFromJSON(campaignJSON) {
        Campaign campaign = new Campaign()

        campaign.setTurnedOn(campaignJSON.turned_on)
        campaign.setName(campaignJSON.name)
        campaign.setKeywords(campaignJSON.keywords)
        campaign.addToRules(createAndRule(campaignJSON))

        return campaign
    }

    Rule createAndRule(campaignJSON) {
        def andKeywords = campaignJSON.and_keywords
        if(andKeywords) {
            return new AndRule(andWords: andKeywords)
        } else {
            null
        }
    }
}
