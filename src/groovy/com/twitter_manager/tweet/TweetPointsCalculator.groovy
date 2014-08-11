package com.twitter_manager.tweet

import com.twitter_manager.TweetCampaign
import com.twitter_manager.sentiment.SentimentValue

class TweetPointsCalculator {

    def MAX_VALUE = 100

    def defaultRangeCalculator = {
        switch (it) {
            case 0: 0
                break
            case (1..100): 1
                break
            case (101..500): 2
                break
            case (501..1000): 3
                break
            case (1001..5000): 4
                break
            default: 5
                break
        }
    }

    def followersRangeCalculator = {
        switch (it) {
            case 0: 0
                break
            case (1..50): 1
                break
            case (51..100): 2
                break
            case (101..500): 3
                break
            case (501..1000): 4
                break
            case (1001..2000): 5
                break
            case (2001..5000): 6
                break
            case (5001..10000): 7
                break
            case (10001..50000): 8
                break
            case (50001..100000): 9
                break
            case (100001..500000): 10
                break
            case (500001..5000000): 11
                break
            default: 12
                break
        }
    }

    private def configurationMap = [
        "RT" : [ weight : 0.5, sizeOfCategories : 5, rangeCalculator : defaultRangeCalculator ],
        "FAVS" : [ weight : 0.3, sizeOfCategories : 5, rangeCalculator : defaultRangeCalculator ],
        "FOLLOWERS": [ weight : 0.2, sizeOfCategories : 12, rangeCalculator : followersRangeCalculator ]
    ]

    Long calculatePointsForTweet(TweetCampaign tweetCampaign) {
        if(SentimentValue.NEUTRAL == tweetCampaign.getSentimentValue()) {
            return 0
        }

        def sentimentMultiplier = SentimentValue.POSITIVE == tweetCampaign.getSentimentValue() ? 1 : -1

        def retweetPoints = pointsFor("RT", tweetCampaign.getRetweets())
        def favoritesPoints = pointsFor("FAVS", tweetCampaign.getFavorites())
        def followersPoints = pointsFor("FOLLOWERS", tweetCampaign.getFollowers())
        return ((retweetPoints + favoritesPoints + followersPoints) * MAX_VALUE * sentimentMultiplier).longValue()
    }

    def pointsFor(configurationName, value) {
        def configuration = this.configurationMap.get(configurationName)

        def rangeCalculator = configuration.rangeCalculator
        def categoryValue = rangeCalculator(value)
        def totalCategories = configuration.sizeOfCategories

        return configuration.weight * (categoryValue/totalCategories)
    }
}
