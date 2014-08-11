package com.twitter_manager.sentiment

import com.twitter_manager.RetryUtil
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder

class SentimentAPICalculator implements SentimentCalculator {

    public static final String POSITIVE = 'pos'
    public static final String NEGATIVE = 'neg'

    @Override
    SentimentValue calculateSentimentValue(String text, String query, SentimentLanguage sentimentLanguage) {

        def response = null
        try {
            HTTPBuilder httpBuilder = new HTTPBuilder('http://text-processing.com/api/sentiment/')
            response = RetryUtil.retry(3, 100) {
                httpBuilder.post(
                        body: [text: text, language: sentimentLanguage.getDescription()],
                        requestContentType: ContentType.URLENC)
            }
        } catch (e) {
            println "Hubo un error al leer de la api de sentiment ${e}"
        }
        return createSentiValueFromResponse(response)
    }

    private def createSentiValueFromResponse(response) {
        if (response) {
            switch (response.label) {
                case POSITIVE: return SentimentValue.POSITIVE
                    break
                case NEGATIVE: return SentimentValue.NEGATIVE
                    break
                default: return SentimentValue.NEUTRAL
                    break
            }
        }
        return SentimentValue.NEUTRAL
    }
}
