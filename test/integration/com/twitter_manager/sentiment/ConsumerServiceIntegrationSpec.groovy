package com.twitter_manager.sentiment

import com.twitter_manager.Campaign
import com.twitter_manager.TweetCampaign
import com.twitter_manager.rules.AndRule
import grails.converters.JSON
import grails.test.spock.IntegrationSpec
import spock.lang.Unroll

import static com.twitter_manager.sentiment.SentimentValue.*

class ConsumerServiceIntegrationSpec extends IntegrationSpec {

    def consumerService

    @Unroll
    void "handleMessage _ a message that #belongs to campaign _ #result"() {
        given:
        AndRule andRule = new AndRule().with {
            it.andWords = "ninja"
            it
        }

        Campaign campaign = new Campaign()
        campaign.with {
            it.name = "test1"
            it.keywords = "turtles"
            it.turnedOn = true
            it.rules = [andRule]
        }
        campaign.save(flush: true, failOnError: true)

        def json = [
            text : text,
            id_str : "1234567890",
            retweet_count : 10,
            favorites_count : 1500,
            user : [ followers_count : 23000, id_str: 987654321],
            place : [ country : "Argentina", country_code : "AR"],
            created_at :"Wed Aug 27 13:08:45 +0000 2014"
        ] as JSON

        def jsonString = json.toString()

        when:
        consumerService.handleMessage(jsonString)

        then:
        def all = TweetCampaign.findAll()
        all.size() == resultSize
        if(resultSize > 0) {
            assert all.first().getSentimentValue() == sentiment
            def points = all.first().getPoints()
            assert points >= min && points <= max
            assert all.first().getTweetDateCreated() != null
        }

        where:
        belongs         | result                                   | resultSize | min   | max       | sentiment  | text
        "belongs"       | "persist that Positive TweetCampaign"    | 1          | 1     | 99        | POSITIVE   | "Everyone loves Teenage Mutant Ninja Turtles!"
        "belongs"       | "persist that Negative TweetCampaign"    | 1          | -99   | -1        | NEGATIVE   | "The Teenage Mutant Ninja Turtles are horrible!"
        "belongs"       | "persist that Neutral TweetCampaign"     | 1          | 0     | 0         | NEUTRAL    | "I have two ninja turtles"
        "do not belong" | "do not persist anything"                | 0          | null  | null      | null       | "I just care about Transformers"
    }
}
