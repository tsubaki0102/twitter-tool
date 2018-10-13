package analytics.application
import analytics.model.{SessionTimeJST, TweetExtractor, TweetRepository}
import com.danielasfregola.twitter4s.TwitterRestClient
import com.google.inject.Inject

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

class TweetETLServiceImpl @Inject()(tweetExtractor: TweetExtractor, tweetRepository: TweetRepository)
    extends TweetETLService {

  override def etl(accountName: String, sessionTime: SessionTimeJST): Future[Unit] = {

    val restClient = TwitterRestClient()

    import scala.concurrent.ExecutionContext.Implicits.global

    val fut = for {
      tweets <- tweetExtractor.extract(accountName, restClient)
      _      <- tweetRepository.saveAll(sessionTime, tweets)
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
