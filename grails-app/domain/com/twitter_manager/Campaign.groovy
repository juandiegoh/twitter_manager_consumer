package com.twitter_manager

import com.twitter_manager.rules.Rule

class Campaign {

    String name
    String keywords
    static hasMany = [rules: Rule]
    Boolean on = true

    def keys
    static transients = ['keys']

    Campaign() {
        rules = new HashSet<Rule>()
    }

    def addTweetToCampaignIfValid(Tweet tweet) {
        if (this.isValidTweet(tweet)) {
            tweet.addCampaign(this)
            return tweet
        }
        return false
    }

    boolean isValidTweet(Tweet tweet) {
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
