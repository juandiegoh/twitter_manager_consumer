package com.twitter_manager.rules

import com.twitter_manager.Tweet

abstract class Rule {

    abstract Boolean valid(Tweet tweet)

    static mapping = {
        version false
    }
}
