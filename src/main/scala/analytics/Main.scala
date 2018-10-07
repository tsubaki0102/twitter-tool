package analytics

import analytics.application.TweetETLService
import com.google.inject.Guice

object Main {

  /*
   * @param args 0:アカウント名 1:セッションタイム("yyyy-MM-dd HH:mm:ss")
   */
  def main(args: Array[String]): Unit = {
    if (args.isEmpty) throw new Exception("引数が空です")

    val tweetETLService = Guice.createInjector(Module).getInstance(classOf[TweetETLService])
    tweetETLService.etl(args(0), args(1))
  }

}
