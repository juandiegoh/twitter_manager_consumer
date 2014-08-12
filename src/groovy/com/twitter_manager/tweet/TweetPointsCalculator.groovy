package com.twitter_manager.tweet

import com.twitter_manager.TweetCampaign
import com.twitter_manager.sentiment.SentimentValue

class TweetPointsCalculator {

    def MAX_VALUE = 100

    def defaultRangeCalculator = {
        switch (it) {
            case 0: 0
                break
            case { it >= 1 && it <= 100 }: 1
                break
            case { it >= 101 && it <= 500 }: 2
                break
            case { it >= 501 && it <= 1000 }: 3
                break
            case { it >= 1001 && it <= 5000 }: 4
                break
            default: 5
                break
        }
    }

    def followersRangeCalculator = {
        switch (it) {
            case 0: 0
                break
            case { it >= 1 && it <= 50 }: 1
                break
            case { it >= 51 && it <= 100 }: 2
                break
            case { it >= 101 && it <= 500 }: 3
                break
            case { it >= 501 && it <= 1000 }: 4
                break
            case { it >= 1001 && it <= 2000 }: 5
                break
            case { it >= 2001 && it <= 5000 }: 6
                break
            case { it >= 5001 && it <= 10000 }: 7
                break
            case { it >= 10001 && it <= 50000 }: 8
                break
            case { it >= 50001 && it <= 100000 }: 9
                break
            case { it >= 100001 && it <= 500000 }: 10
                break
            case { it >= 500001 && it <= 5000000 }: 11
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
