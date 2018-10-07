package analytics

import analytics.infrastructure.TweetExtractorImpl
import com.danielasfregola.twitter4s.TwitterRestClient

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Main {

  /*
   * @param args 蓄積するアカウントの表示名
   */
  def main(args: Array[String]): Unit = {
    val accountName = args(0)
    val restClient  = TwitterRestClient()

    val fut = TweetExtractorImpl.extract(accountName, restClient)
    //TODO APIエラーを制御する
    Await.result(fut, Duration.Inf)
    restClient.shutdown
  }
}
