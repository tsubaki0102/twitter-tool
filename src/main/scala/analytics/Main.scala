package analytics

import com.danielasfregola.twitter4s.TwitterRestClient
import com.typesafe.scalalogging.LazyLogging
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object Main extends LazyLogging {

  /*
   * @param args 追跡するアカウント表示名
   */
  def main(args: Array[String]): Unit = {
    val accountName = args(0)
    val restClient  = TwitterRestClient()

    val fut = timeline(accountName, restClient)
    //TODO APIエラーを制御する
    Await.result(fut, Duration.Inf)
    restClient.shutdown
  }

  def timeline(accountName: String, restClient: TwitterRestClient): Future[Unit] = {

    restClient
      .userTimelineForUser(screen_name = accountName, trim_user = true, exclude_replies = true, include_rts = false)
      .map { ratedData =>
        val tweets = ratedData.data

        tweets.foreach { tweet =>
          logger.info(
            s"${tweet.id} | ${accountName} |  ${tweet.created_at} | ${tweet.text} | ${tweet.retweet_count} | ${tweet.favorite_count}")
        }
      }
  }
}
