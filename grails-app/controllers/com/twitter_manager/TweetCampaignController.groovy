package com.twitter_manager

import com.twitter_manager.exceptions.ParameterErrorException
import com.twitter_manager.utils.DateParserUtils
import grails.converters.JSON

class TweetCampaignController {

    def tweetCampaignService

    private static final String API_DATE_FORMAT = "dd-MM-yyyy HH:mm:ss"

    def index() {
        try {
            def queryJSON = request.getJSON()
            validateQuery(queryJSON)
            def dateFrom = DateParserUtils.parseDateWithPattern(queryJSON.date_from, API_DATE_FORMAT)
            def dateTo = DateParserUtils.parseDateWithPattern(queryJSON.date_to, API_DATE_FORMAT)
            def allTweetDataFromCampaigns = this.tweetCampaignService.findAllFromCampaignBetweenDates(queryJSON.campaign_id, dateFrom, dateTo)
            render(contentType: "application/json", text: allTweetDataFromCampaigns as JSON, status: 200)
        } catch(ParameterErrorException e) {
            render(contentType: "application/json", text: [error: e.getMessage()] as JSON, status: 400)
        } catch(e) {
            render(contentType: "application/json", text: [cause: e.getMessage()], status: 500)
        }
    }

    private def validateQuery(query) {
        if(query.campaign_id == null) {
            missingParameterError("campaign_id")
        }

        if(query.date_from == null) {
            missingParameterError("date_from")
        }

        if(query.date_to == null) {
            missingParameterError("date_to")
        }
    }

    private def missingParameterError(String missingParameter) {
        def error = "The parameter ${missingParameter} is mandatory in the JSON request"
        throw new ParameterErrorException(error)
    }

    private def errorInQuery(String error) {
        throw new ParameterErrorException(error)
    }
}
