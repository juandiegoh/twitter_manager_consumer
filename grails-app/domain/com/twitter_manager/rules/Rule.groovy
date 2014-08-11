package com.twitter_manager.rules

import com.twitter_manager.tweet.TweetDTO

abstract class Rule {

    abstract Boolean valid(TweetDTO tweet)

    static mapping = {
        version false
    }
}
