package com.twitter_manager.rules

import com.twitter_manager.Tweet

class AndRule extends Rule {

    String andWords

    def andWordsKeys
    static transients = ['andWordsKeys']

    static mapping = {
        discriminator "AND"
    }

    @Override
    Boolean valid(Tweet tweet) {
        def lowerCaseText = tweet.getText().toLowerCase()
        getAndWordsSeparatedByToken().every {
            lowerCaseText.contains(it.toLowerCase())
        }
    }

    private getAndWordsSeparatedByToken() {
        if(andWordsKeys != null) {
            return andWordsKeys
        } else {
            andWordsKeys = andWords.split(";")
            return andWordsKeys
        }
    }
}
