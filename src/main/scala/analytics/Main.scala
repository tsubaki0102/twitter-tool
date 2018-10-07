package analytics

import analytics.infrastructure.{TweetExtractorImpl, TweetRepositoryImpl}
import analytics.model.SessionTime
import com.danielasfregola.twitter4s.TwitterRestClient

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}

object Main {

  /*
   * @param args 蓄積するアカウントの表示名
   */
  def main(args: Array[String]): Unit = {
    val accountName = {
      if (args.nonEmpty) args(0)
      else throw new Exception("引数が空です")
    }

    val sessionTime = SessionTime("2018-10-07 20:48:30") match {
      case Success(s) => s
      case Failure(e) => println(e); throw e
    }

    val restClient = TwitterRestClient()

    val fut = for {
      tweets <- TweetExtractorImpl.extract(accountName, restClient)
      _      <- TweetRepositoryImpl.saveAll(sessionTime, tweets)
    } yield ()

    fut.onComplete {
      case Success(_) => println("完了しました")
      case Failure(e) => println(e)
    }

    //TODO APIエラーを制御する
    Await.result(fut, Duration.Inf)
    restClient.shutdown
  }
}
