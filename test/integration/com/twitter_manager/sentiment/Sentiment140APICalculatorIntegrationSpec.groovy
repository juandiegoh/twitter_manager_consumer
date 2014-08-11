package com.twitter_manager.sentiment

import grails.test.spock.IntegrationSpec

class Sentiment140APICalculatorIntegrationSpec extends IntegrationSpec {

    SentimentCalculator sentimentCalculator

    void setup() {
        this.sentimentCalculator = new Sentiment140APICalculator()
    }

    void "testSentiStrengthPositive"() {
        given:
        def result = this.sentimentCalculator.calculateSentimentValue('me encantan los perros', 'perro', SentimentLanguage.AUTO)
        expect:
        result == SentimentValue.POSITIVE
    }

    void "testSentiStrengthNegative"() {
        given:
        def result = this.sentimentCalculator.calculateSentimentValue('I hate dogs', 'dogs', SentimentLanguage.AUTO)

        expect:
        result == SentimentValue.NEGATIVE
    }

    void "testSentiStrengthNeutral"() {
        given:
        def result = this.sentimentCalculator.calculateSentimentValue('Dogs are running', 'dogs', SentimentLanguage.AUTO)

        expect:
        result == SentimentValue.NEUTRAL
    }
}
