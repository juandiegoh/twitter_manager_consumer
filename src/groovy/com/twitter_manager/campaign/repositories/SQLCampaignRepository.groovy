package com.twitter_manager.campaign.repositories

import com.twitter_manager.Campaign

class SQLCampaignRepository implements CampaignRepository {

    @Override
    def getTurnedOnCampaigns() {
        return Campaign.findAllByTurnedOn(true)
    }

    @Override
    def findById(id) {
        return Campaign.findById(id)
    }

    def findAll() {
        return Campaign.findAll()
    }
}
