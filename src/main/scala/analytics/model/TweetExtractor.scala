package analytics.model
import com.danielasfregola.twitter4s.TwitterRestClient
import com.danielasfregola.twitter4s.entities.Tweet

import scala.concurrent.{ExecutionContext, Future}

trait TweetExtractor {

  def extract(accountName: String, restClient: TwitterRestClient)(implicit ec: ExecutionContext): Future[Seq[Tweet]]

}
