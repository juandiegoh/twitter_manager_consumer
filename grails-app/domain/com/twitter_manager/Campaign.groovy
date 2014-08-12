package com.twitter_manager

import com.twitter_manager.rules.Rule
import com.twitter_manager.tweet.TweetDTO

class Campaign {

    String name
    String keywords
    static hasMany = [rules: Rule]
    Boolean turnedOn = true

    def keys
    static transients = ['keys']

    static mapping = {
        rules lazy: false
    }

    boolean isValidTweet(TweetDTO tweet) {
        if(includesKeywords(tweet.getText())) {
            rules.every {
                it.valid(tweet)
            }
        }
    }

    def includesKeywords(String text) {
        getKeysFromKeywords().every {
            text.toLowerCase().contains(it.toLowerCase())
        }
    }

    private def getKeysFromKeywords() {
        if(keys != null) {
            return keys
        } else {
            keys = keywords.split(";")
            return keys
        }
    }


}
