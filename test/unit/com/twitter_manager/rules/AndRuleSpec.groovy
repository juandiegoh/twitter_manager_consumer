package com.twitter_manager.rules

import com.twitter_manager.tweet.TweetDTO
import spock.lang.Specification

class AndRuleSpec extends Specification {

    Rule andRule
    Rule multiAndRule

    def setup() {
        andRule = new AndRule().with {
            it.andWords = "carlotto"
            it
        }

        multiAndRule = new AndRule().with {
            it.andWords = "carlotto"
            it
        }
    }

    void "if the andWords are not present _ should return false"() {
        given:
        def notValidTweet = new TweetDTO()
        notValidTweet.text = "This game is fun."

        expect:
        false == andRule.valid(notValidTweet)
    }

    void "if the andWords are present _ should return true"() {
        given:
        def validTweet = new TweetDTO()
        validTweet.text = "This game is carlotto."

        expect:
        true == andRule.valid(validTweet)
    }

    void "if there are more words and all are present _ should return true"() {
        given:
        def validTweet = new TweetDTO()
        validTweet.text = "This game is Argentina carlotto."

        expect:
        true == multiAndRule.valid(validTweet)
    }

    void "if there are more words and not all are present _ should return false"() {
        given:
        def notValidTweet = new TweetDTO()
        notValidTweet.text = "This game is carlotto."

        expect:
        true == multiAndRule.valid(notValidTweet)
    }
}
