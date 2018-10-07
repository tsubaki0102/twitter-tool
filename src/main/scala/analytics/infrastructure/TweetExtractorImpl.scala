package analytics.infrastructure
import analytics.model.TweetExtractor
import com.danielasfregola.twitter4s.TwitterRestClient
import com.danielasfregola.twitter4s.entities.Tweet
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.{ExecutionContext, Future}

object TweetExtractorImpl extends TweetExtractor with LazyLogging {
  override def extract(accountName: String, restClient: TwitterRestClient)(
      implicit ec: ExecutionContext): Future[Seq[Tweet]] = {

    restClient
      .userTimelineForUser(screen_name = accountName, trim_user = true, exclude_replies = true, include_rts = false)
      .map { ratedData =>
        val tweets = ratedData.data

        tweets.foreach { tweet =>
          logger.debug(
            s"${tweet.id} | $accountName |  ${tweet.created_at} | ${tweet.text} | ${tweet.retweet_count} | ${tweet.favorite_count}")
        }

        tweets
      }
  }

}
