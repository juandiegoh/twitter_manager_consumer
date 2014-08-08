import com.twitter_manager.ConsumerService
import com.twitter_manager.campaign.repositories.CampaignDefaultInMemoryRepository
import com.twitter_manager.campaign.repositories.CampaignDefaultInMemoryRepository
import com.twitter_manager.campaign.repositories.CampaignDefaultInMemoryRepository
import com.twitter_manager.tweet.TweetFactory
import com.twitter_manager.tweet.TweetProcessor
import com.twitter_manager.tweet.persistors.CsvTweetRepository
import com.twitter_manager.tweet.persistors.CsvTweetRepository

// Place your Spring DSL code here
beans = {

    consumerService(ConsumerService) {
        tweetProcessor = ref('tweetProcessor')
        tweetFactory = ref('tweetFactory')
    }

    tweetProcessor(TweetProcessor) {
        campaignsRepository = ref('defaultInMemoryCampaingRepository')
        tweetRepository = ref('csvTweetRepository')
    }

    defaultInMemoryCampaingRepository(CampaignDefaultInMemoryRepository)

    csvTweetRepository(CsvTweetRepository)

    tweetFactory(TweetFactory)
}
