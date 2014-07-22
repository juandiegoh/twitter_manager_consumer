package com.twitter_manager

import grails.converters.JSON

class ConsumerService {

    static rabbitQueue = 'twitter_feed'

    def handleMessage(String textMessage) {
        println "handling message: "
        def data = JSON.parse textMessage
        // simulate work
        Thread.sleep(Math.round(Math.random()*100))
        print data.text
    }
}
