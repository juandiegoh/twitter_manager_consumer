package com.twitter_manager.tweet

import com.twitter_manager.TweetCampaign
import spock.lang.Unroll

import static com.twitter_manager.sentiment.SentimentValue.*
import spock.lang.Specification

class TweetPointsCalculatorSpec extends Specification {

    TweetPointsCalculator tweetPointsCalculator

    void setup() {
        tweetPointsCalculator = new TweetPointsCalculator()
    }

    @Unroll
    void "calculatePointsForTweet _ #name _ given a Tweet zero _ calculate points"() {
        given:
        def cls = condition
        def tweet = createTweetCampaign(retweets, favorites, followers, sentiment)

        when:
        def points = this.tweetPointsCalculator.calculatePointsForTweet(tweet)

        then:
        assert cls(points)

        where:
        name    | retweets    | favorites | followers | sentiment | condition
        "1"     | 0           | 0         | 0         | POSITIVE  | { result -> result == new BigDecimal(0) }
        "2"     | 10000       | 10000     | 6000000   | POSITIVE  | { result -> result == new BigDecimal(100) }
        "3"     | 10000       | 10000     | 6000000   | NEGATIVE  | { result -> result == new BigDecimal(-100) }
        "4"     | 502         | 1002      | 54345     | POSITIVE  | { result -> result >= 0 && result <= 100 }
        "5"     | 124         | 327423    | 3423      | NEUTRAL   | { result -> result == new BigDecimal(0) }
    }

    def createTweetCampaign(retweets, favorites, followers, sentimentValue) {
        TweetCampaign tweetCampaign = new TweetCampaign()
        tweetCampaign.with {
            it.favorites = favorites
            it.retweets = retweets
            it.followers = followers
            it.sentimentValue = sentimentValue
        }
        return tweetCampaign
    }
}
