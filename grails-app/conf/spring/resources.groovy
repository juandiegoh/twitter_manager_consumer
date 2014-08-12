import com.twitter_manager.CampaignService
import com.twitter_manager.ConsumerService
import com.twitter_manager.TweetCampaignService
import com.twitter_manager.campaign.CampaignFactory
import com.twitter_manager.campaign.repositories.SQLCampaignRepository
import com.twitter_manager.sentiment.Sentiment140APICalculator
import com.twitter_manager.tweet.TweetFactory
import com.twitter_manager.tweet.TweetPointsCalculator
import com.twitter_manager.tweet.TweetProcessor
import com.twitter_manager.tweet.persistors.SQLTweetCampaignRepository

// Place your Spring DSL code here
beans = {

    tweetCampaignService(TweetCampaignService) {
        tweetCampaignRepository = ref('sqlTweetCampaignRepository')
        campaignRepository = ref('sqlCampaignRepository')
    }

    campaignService(CampaignService) {
        campaignRepository = ref('sqlCampaignRepository')
        campaignFactory = ref('campaignFactory')
    }

    consumerService(ConsumerService) {
        tweetProcessor = ref('tweetProcessor')
        tweetFactory = ref('tweetFactory')
    }

    tweetProcessor(TweetProcessor) {
        campaignsRepository = ref('sqlCampaignRepository')
        tweetRepository = ref('sqlTweetCampaignRepository')
        tweetFactory = ref('tweetFactory')
        sentimentCalculator = ref('sentiment140APICalculator')
        tweetPointsCalculator = ref('tweetPointsCalculator')
    }

    campaignFactory(CampaignFactory)

    sqlCampaignRepository(SQLCampaignRepository)
    sqlTweetCampaignRepository(SQLTweetCampaignRepository)

    sqlTweetCampaignRepository(SQLTweetCampaignRepository)

    tweetFactory(TweetFactory)

    sentiment140APICalculator(Sentiment140APICalculator)

    tweetPointsCalculator(TweetPointsCalculator)

}
