package com.twitter_manager.campaign.repositories

import com.twitter_manager.Campaign
import com.twitter_manager.rules.AndRule

class CampaignDefaultInMemoryRepository implements CampaignRepository {

    def getTurnedOnCampaigns() {
        AndRule andRule1 = new AndRule(andWords : "libertadores;San Lorenzo")
        def campaign1 = new Campaign().with {
            it.id = -1
            it.rules = [andRule1]
            it.keywords = "San Lorenzo"
            it
        }

        AndRule andRule2 = new AndRule(andWords : "Coca cola")
        def campaign2 = new Campaign().with {
            it.id = -2
            it.rules = [andRule2]
            it.keywords = "Coca cola"
            it
        }
        return [campaign1, campaign2]
    }
}
