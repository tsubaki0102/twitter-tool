package analytics.infrastructure
import java.time.format.DateTimeFormatter
import java.time.{LocalDateTime, ZoneId}

import analytics.model.{SessionTimeJST, TweetRepository}
import com.danielasfregola.twitter4s.entities.Tweet
import scalikejdbc._
import scalikejdbc.config.DBs

import scala.concurrent.{ExecutionContext, Future}

object TweetRepositoryImpl extends TweetRepository {

  override def saveAll(sessionTime: SessionTimeJST, tweets: Seq[Tweet])(implicit ec: ExecutionContext): Future[Unit] =
    Future {

      val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

      val records: Seq[Seq[Any]] = tweets.map { tweet =>
        Seq(
          sessionTime.value.format(formatter),
          tweet.id,
          LocalDateTime.ofInstant(tweet.created_at.toInstant, ZoneId.of("Asia/Tokyo")),
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
