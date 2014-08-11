import com.twitter_manager.ConsumerService
import com.twitter_manager.campaign.repositories.CampaignDefaultInMemoryRepository
import com.twitter_manager.campaign.repositories.CampaignDefaultInMemoryRepository
import com.twitter_manager.campaign.repositories.CampaignDefaultInMemoryRepository
import com.twitter_manager.campaign.repositories.CampaignRepository
import com.twitter_manager.campaign.repositories.SQLCampaignRepository
import com.twitter_manager.sentiment.Sentiment140APICalculator
import com.twitter_manager.sentiment.SentimentCalculator
import com.twitter_manager.tweet.TweetFactory
import com.twitter_manager.tweet.TweetFactory
import com.twitter_manager.tweet.TweetFactory
import com.twitter_manager.tweet.TweetPointsCalculator
import com.twitter_manager.tweet.TweetProcessor
import com.twitter_manager.tweet.persistors.CsvTweetCampaignRepository
import com.twitter_manager.tweet.persistors.CsvTweetCampaignRepository
import com.twitter_manager.tweet.persistors.CsvTweetCampaignRepository
import com.twitter_manager.tweet.persistors.SQLTweetCampaignRepository

// Place your Spring DSL code here
beans = {

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

    sqlCampaignRepository(SQLCampaignRepository)
    defaultInMemoryCampaingRepository(CampaignDefaultInMemoryRepository)

    sqlTweetCampaignRepository(SQLTweetCampaignRepository)
    csvTweetRepository(CsvTweetCampaignRepository)

    tweetFactory(TweetFactory)

    sentiment140APICalculator(Sentiment140APICalculator)

    tweetPointsCalculator(TweetPointsCalculator)
}
