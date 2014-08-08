package com.twitter_manager.tweet.persistors

import com.twitter_manager.Tweet

class CsvTweetRepository {

    def persist(Tweet tweet) {
        new File("salida.csv").withWriterAppend { out ->
            out.writeLine(tweet.toCsv())
        }
    }
}
