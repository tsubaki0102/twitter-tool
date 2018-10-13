package analytics.infrastructure
import java.text.SimpleDateFormat

import analytics.model.{SessionTimeJST, TweetRepository}
import com.danielasfregola.twitter4s.entities.Tweet
import scalikejdbc._
import scalikejdbc.config.DBs

import scala.concurrent.{ExecutionContext, Future}

object TweetRepositoryImpl extends TweetRepository {

  override def saveAll(sessionTime: SessionTimeJST, tweets: Seq[Tweet])(implicit ec: ExecutionContext): Future[Unit] =
    Future {

      val records: Seq[Seq[Any]] = tweets.map { tweet =>
        Seq(
          sessionTime.value.toString,
          tweet.id,
          new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tweet.created_at),
          tweet.retweet_count,
          tweet.favorite_count,
          tweet.text
        )
      }

      DBs.setupAll()

      DB localTx { implicit session =>
        sql"INSERT INTO tweet VALUES (?, ?, ?, ?, ?, ?)"
          .batch(records: _*)
          .apply()
      }

    }

}
