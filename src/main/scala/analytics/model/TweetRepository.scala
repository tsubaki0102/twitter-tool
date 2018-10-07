package analytics.model
import com.danielasfregola.twitter4s.entities.Tweet

import scala.concurrent.{ExecutionContext, Future}

trait TweetRepository {

  def saveAll(sessionTime: SessionTime, tweets: Seq[Tweet])(implicit ec: ExecutionContext): Future[Unit]

}
