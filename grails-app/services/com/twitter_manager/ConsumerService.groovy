package com.twitter_manager

import com.twitter_manager.tweet.TweetFactory
import com.twitter_manager.tweet.TweetProcessor
import grails.converters.JSON

class ConsumerService {

    TweetProcessor tweetProcessor
    TweetFactory tweetFactory

    static rabbitQueue = 'twitter_feed'

    def handleMessage(String textMessage) {
        def data = JSON.parse textMessage
        Tweet tweet = tweetFactory.createTweetFromJSON(data)
        tweetProcessor.processTweet(tweet)
    }
}
