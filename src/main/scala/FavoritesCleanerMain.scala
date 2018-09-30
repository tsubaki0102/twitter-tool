import java.time.{LocalDateTime, ZoneId}
import java.util.Date

import com.danielasfregola.twitter4s.TwitterRestClient
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object FavoritesCleanerMain extends LazyLogging {

  /**
    * ツイートの投稿日時が現在から1週間以上前のツイートに対して、いいねを外します。
    * @param args 自分のアカウント表示名
    */
  def main(args: Array[String]): Unit = {
    val accountName = args(0)
    val restClient  = TwitterRestClient()

    val fut = unfavoriteOldTweets(accountName, restClient)
    //TODO APIエラーを制御する
    Await.result(fut, Duration.Inf)
    restClient.shutdown
  }

  def unfavoriteOldTweets(accountName: String, restClient: TwitterRestClient): Future[Unit] = {
    restClient
      .favoriteStatusesForUser(screen_name = accountName, count = 200)
      .map { ratedData =>
        val tweets    = ratedData.data
        val oldTweets = tweets.withFilter(_.created_at.before(oneWeekAgo))

        oldTweets.map { tweet =>
          logger.info(s"UNFAVORITE: ${tweet.created_at} | ${tweet.text}")

          val futUnfavorite = restClient.unfavoriteStatus(tweet.id)
          Await.result(futUnfavorite, Duration.Inf)
        }
      }
  }

  def oneWeekAgo: Date = {
    val oneWeekAgoInstant = LocalDateTime.now
      .minusDays(7)
      .atZone(ZoneId.systemDefault)
      .toInstant

    Date.from(oneWeekAgoInstant)
  }
}
