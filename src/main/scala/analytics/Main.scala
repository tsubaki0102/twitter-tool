package analytics

import analytics.application.TweetETLService
import analytics.model.SessionTime
import com.google.inject.Guice

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

    val tweetETLService = Guice.createInjector(Module).getInstance(classOf[TweetETLService])
    tweetETLService.etl(accountName, sessionTime)
  }

}
