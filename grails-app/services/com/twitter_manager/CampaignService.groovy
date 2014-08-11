package com.twitter_manager

import com.twitter_manager.exceptions.ResourceNotFoundException
import grails.transaction.Transactional

@Transactional
class CampaignService {

    def campaignRepository
    def campaignFactory

    def findById(id) {
        findCampaignAndProcessIfExists(id) {
            return it
        }
    }

    def findAll() {
        this.campaignRepository.findAll()
    }

    def findCampaignAndProcessIfExists(id, cls) {
        def campaign = this.campaignRepository.findById(id)
        if(campaign) {
            return cls(campaign)
        } else {
            throw new ResourceNotFoundException()
        }
    }

    def turnOn(id) {
        this.turnCampaignOn(id, true)
    }

    def turnOff(id) {
        this.turnCampaignOn(id, false)
    }

    def createFromJSON(campaignJSON) {
        def campaign = this.campaignFactory.createFromJSON(campaignJSON)
        campaign.save(failOnError: true)
        return campaign
    }

    private def turnCampaignOn(id, Boolean turnOn) {
        findCampaignAndProcessIfExists(id) { Campaign campaign ->
            campaign.setTurnedOn(turnOn)
            campaign.save(failOnError: true)
        }
    }
}
