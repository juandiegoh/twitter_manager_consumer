import com.twitter_manager.Campaign
import com.twitter_manager.TweetCampaign
import com.twitter_manager.rules.AndRule
import com.twitter_manager.sentiment.SentimentValue
import org.codehaus.groovy.grails.web.json.JSONObject

class BootStrap {

    def init = { servletContext ->
        if(Campaign.count() == 0) {
            AndRule andRule = new AndRule().with {
                it.andWords = "dead"
                it
            }

            Campaign campaign = new Campaign()
            campaign.with {
                it.name = "test1"
                it.keywords = "Robin Williams"
                it.turnedOn = true
                it.rules = [andRule]
            }
            campaign.save(flush: true, failOnError: true)
        }

        grails.converters.JSON.registerObjectMarshaller(AndRule) { AndRule andRule ->
            return [
                id: andRule.id,
                and_words : andRule.andWords,
                type: "and_rule"
            ]
        }

        grails.converters.JSON.registerObjectMarshaller(Campaign) { Campaign c ->
            return [
                    id: c.id,
                    name: c.name,
                    keywords : c.keywords,
                    turned_on : c.turnedOn,
                    rules: c.rules
            ]
        }

        grails.converters.JSON.registerObjectMarshaller(TweetCampaign) { TweetCampaign t ->
            return [
                    id: t.id,
                    campaign_id: t.campaign?.id,
                    tweet_id: t.tweetId,
                    text: t.text,
                    sentiment: t.getSentimentValue()?.name(),
                    points: t.points,
                    retweets: t.retweets,
                    favorites: t.favorites,
                    followers: t.followers,
                    user_id: t.userId,
                    country: t.country,
                    country_code: t.countryCode,
                    tweet_date_created: t.tweetDateCreated?.format("dd-MM-yyyy HH:mm:ss")
            ]
        }

        JSONObject.NULL.metaClass.asBoolean = {-> false}
    }

    def destroy = {
    }
}
