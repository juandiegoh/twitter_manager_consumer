package com.twitter_manager

import com.twitter_manager.tweet.TweetDTO
import com.twitter_manager.tweet.TweetFactory
import com.twitter_manager.tweet.TweetProcessor
import grails.converters.JSON

class ConsumerService {

    TweetProcessor tweetProcessor
    TweetFactory tweetFactory

    static rabbitQueue = 'twitter_feed'

    def handleMessage(String textMessage) {
        try {
            def data = JSON.parse textMessage
            TweetDTO tweet = tweetFactory.createTweetFromJSON(data)
            tweetProcessor.processTweet(tweet)
            null
        } catch(e) {
            log.error("Que anda pasando por aqui???", e)
            null
        }
    }
}
