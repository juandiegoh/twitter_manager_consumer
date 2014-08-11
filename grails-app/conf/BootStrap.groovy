import com.twitter_manager.Campaign
import com.twitter_manager.rules.AndRule
import com.twitter_manager.rules.Rule
import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->
        if(Campaign.count() == 0) {
            AndRule andRule = new AndRule().with {
                it.andWords = "ninja"
                it
            }

            Campaign campaign = new Campaign()
            campaign.with {
                it.name = "test1"
                it.keywords = "turtles"
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
    }

    def destroy = {
    }
}
