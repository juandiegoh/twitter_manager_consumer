package com.twitter_manager.tweet

import com.twitter_manager.Tweet

class TweetFactory {
    Tweet createTweetFromJSON(jsonTweet) {
        return new Tweet().with {
            it.campaigns = []
            it.text = jsonTweet.text

            return it
        }
    }
}
