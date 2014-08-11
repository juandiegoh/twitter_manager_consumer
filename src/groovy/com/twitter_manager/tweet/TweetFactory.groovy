package com.twitter_manager.tweet

import com.twitter_manager.Campaign
import com.twitter_manager.TweetCampaign

import java.text.SimpleDateFormat

class TweetFactory {

    TweetDTO createTweetFromJSON(jsonTweet) {
        return new TweetDTO().with {
            it.text = jsonTweet.text
            it.tweetId = jsonTweet.id_str ?: ""
            it.retweets = jsonTweet.retweet_count ?: 0
            it.favorites = jsonTweet.favorite_count ?: 0
            it.followers = jsonTweet.user?.followers_count
            it.userId = jsonTweet.user?.id_str ?: ""
            it.country = jsonTweet.place?.country
            it.countryCode = jsonTweet.place?.country_code
            it.tweetDateCreated = getTwitterDate(jsonTweet.created_at)
            return it
        }
    }

    private Date getTwitterDate(String date) {

        final String TWITTER="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(TWITTER);
        sf.setLenient(true);
        return sf.parse(date);
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
