package com.twitter_manager.campaign.repositories

import com.twitter_manager.Campaign

class CampaignSQLRepository implements CampaignRepository {

    @Override
    def getTurnedOnCampaigns() {
        return Campaign.findAllByOn(true)
    }
}
