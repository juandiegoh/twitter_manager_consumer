package com.twitter_manager.sentiment

import com.twitter_manager.utils.RetryUtil
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder

class Sentiment140APICalculator implements SentimentCalculator {

    public static final def POSITIVE = 4
    public static final def NEUTRAL = 2
    public static final def NEGATIVE = 0

    private static final String SPANISH_LANGUAGE = 'es'
    private static final String ENGLISH_LANGUAGE = 'en'
    private static final String AUTO_LANGUAGE = 'auto'

    private def language = [:]

    private static final String SENTIMENT_140_APP_ID = "jdherenu@gmail.com"

    Sentiment140APICalculator() {
        language.put(SentimentLanguage.SPANISH, SPANISH_LANGUAGE)
        language.put(SentimentLanguage.ENGLISH, ENGLISH_LANGUAGE)
        language.put(SentimentLanguage.AUTO, AUTO_LANGUAGE)
    }

    @Override
    SentimentValue calculateSentimentValue(String text, String query, SentimentLanguage sentimentLanguage) {

        def response = null
        try {
            HTTPBuilder httpBuilder = new HTTPBuilder('http://www.sentiment140.com/')
            response = RetryUtil.retry(3, 100) {
                return httpBuilder.get(
                        path: 'api/classify',
                        contentType: ContentType.JSON,
                        query: [text: text,
                                query: query,
                                language: language.get(sentimentLanguage),
                                appid: SENTIMENT_140_APP_ID])
            }
        } catch (e) {
            println "Hubo un error al leer de la api de sentiment ${e}"
        }
        return createSentimentValueFromResponse(response)
    }

    def createSentimentValueFromResponse(response) {
        if (response) {
            switch (response.results.polarity) {
                case POSITIVE:
                    return SentimentValue.POSITIVE
                    break
                case NEGATIVE:
                    return SentimentValue.NEGATIVE
                    break
                case NEUTRAL:
                    return SentimentValue.NEUTRAL
                    break
                default:
                    return SentimentValue.NEUTRAL
                    break
            }
        }
        return SentimentValue.NEUTRAL
    }
}
