package com.twitter_manager

import com.twitter_manager.exceptions.ResourceNotFoundException
import grails.converters.JSON

class CampaignsController {

    def campaignService

    // GET /campaigns
    def index() {
        def allCampaigns = this.campaignService.findAll()
        render(contentType: "application/json", text: allCampaigns as JSON, status: 200)
    }

    // GET /campaigns/$id
    def show() {
        basicFlow {
            def campaign = campaignService.findById(params.id)
            def campaignJSON = campaign as JSON
            response.setContentType("application/json")
            render(contentType: "application/json", text: campaignJSON, status: 200)
        }
    }

    // PUT /campaigns/$id/turnon
    def turnOn() {
        basicFlow {
            this.campaignService.turnOn(params.id)
            render(contentType: "application/json", text: [result: "Campaign turned on"] as JSON, status: 200)
        }
    }

    // PUT /campaigns/$id/turnon
    def turnOff() {
        basicFlow {
            this.campaignService.turnOff(params.id)
            render(contentType: "application/json", text: [result: "Campaign turned off"] as JSON, status: 200)
        }
    }

    // POST /campaigns '{name: "name", keywords: "keyword", "turned_on": true, "and_keywords": "word1;word2"}'
    def create() {
        try {
            def campaignJSON = request.getJSON()
            def campaign = this.campaignService.createFromJSON(campaignJSON)
            render(contentType: "application/json", text: [id : campaign.id] as JSON, status: 201)
        } catch (e) {
            render(contentType: "application/json", text: [cause: e.getCause()], status: 500)
        }
    }

    private basicFlow(cls) {
        try {
            cls()
        } catch(ResourceNotFoundException e) {
            render(contentType: "application/json", status: 404)
        } catch (e) {
            render(contentType: "application/json", text: [cause: e.getCause()], status: 500)
        }
    }
}
