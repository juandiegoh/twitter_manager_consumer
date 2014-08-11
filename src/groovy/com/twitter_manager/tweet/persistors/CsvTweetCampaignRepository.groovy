package com.twitter_manager.tweet.persistors

import com.twitter_manager.TweetCampaign

class CsvTweetCampaignRepository implements TweetCampaignRepository {

    def save(TweetCampaign tweet) {
        new File("salida.csv").withWriterAppend { out ->
            out.writeLine(tweet.toString())
        }
    }
}
