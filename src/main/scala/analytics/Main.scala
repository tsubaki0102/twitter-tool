package analytics

import analytics.application.TweetETLService
import analytics.model.SessionTimeJST
import com.google.inject.Guice

import scala.util.{Failure, Success}

object Main {

  /*
   * @param args 0:アカウント名 1:セッションタイム("yyyy-MM-dd HH:mm:ss")
   */
  def main(args: Array[String]): Unit = {
    if (args.isEmpty) throw new Exception("引数が空です")

    val sessionTime = SessionTimeJST(args(1)) match {
      case Success(s) => s
      case Failure(e) => println(e); throw e
    }

    val tweetETLService = Guice.createInjector(Module).getInstance(classOf[TweetETLService])
    tweetETLService.etl(args(0), sessionTime)
  }

}
