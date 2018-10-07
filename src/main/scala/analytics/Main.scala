package analytics

import analytics.application.TweetETLServiceImpl
import analytics.infrastructure.{TweetExtractorImpl, TweetRepositoryImpl}
import analytics.model.SessionTime

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

    val service = new TweetETLServiceImpl(TweetExtractorImpl, TweetRepositoryImpl)
    service.etl(accountName, sessionTime)
  }
}
