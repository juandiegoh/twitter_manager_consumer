package com.twitter_manager.tweet

import com.twitter_manager.Campaign
import com.twitter_manager.TweetCampaign
import com.twitter_manager.utils.DateParserUtils
import org.codehaus.groovy.grails.web.json.JSONObject

class TweetFactory {

    final String TWITTER_DATE_PATTERN = "EEE MMM dd HH:mm:ss ZZZZZ yyyy"

    TweetDTO createTweetFromJSON(JSONObject jsonTweet) {
        return new TweetDTO().with {
            it.text = nullCheckerChanger(jsonTweet.text)
            it.tweetId = nullCheckerChanger(jsonTweet.id_str)
            it.retweets = nullCheckerChanger(jsonTweet.retweet_count)
            it.favorites = nullCheckerChanger(jsonTweet.favorite_count)

            if(jsonTweet.place.asBoolean()) {
                it.followers = nullCheckerChanger(jsonTweet.user?.followers_count)
                it.userId = nullCheckerChanger(jsonTweet.user?.id_str)
            }

            if(jsonTweet.place.asBoolean()) {
                it.country = nullCheckerChanger(jsonTweet.place?.country)
                it.countryCode = nullCheckerChanger(jsonTweet.place?.country_code)
            }

            it.tweetDateCreated = DateParserUtils.parseDateWithPattern(jsonTweet.created_at, TWITTER_DATE_PATTERN)
            return it
        }
    }

    def nullCheckerChanger(value) {
        value == JSONObject.NULL ? null : value
    }

    TweetCampaign createTweetCampaign(TweetDTO tweetDTO, Campaign campaign) {
        TweetCampaign tweetCampaign = new TweetCampaign()
        tweetCampaign.with {
            it.campaign = campaign
            it.text = tweetDTO.getText()
            it.tweetId = tweetDTO.getTweetId()
            it.retweets = tweetDTO.getRetweets()
            it.favorites = tweetDTO.getFavorites()
            it.followers = tweetDTO.getFollowers()
            it.userId = tweetDTO.getUserId()
            it.country = tweetDTO.getCountry()
            it.countryCode = tweetDTO.getCountryCode()
            it.tweetDateCreated = tweetDTO.getTweetDateCreated()
            return it
        }
    }
}
