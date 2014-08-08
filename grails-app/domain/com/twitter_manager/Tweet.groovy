package com.twitter_manager

class Tweet {

    List<Long> campaigns = []
    String text

    static constraints = {
    }

    def addCampaign(Campaign campaign) {
        this.campaigns.add campaign.id
    }

    boolean isInCampaign() {
        return campaigns.size() > 0
    }

    def toCsv() {
        return "${text};${campaigns}"
    }
}
