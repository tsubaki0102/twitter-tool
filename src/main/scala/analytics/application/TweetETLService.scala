package analytics.application
import scala.concurrent.Future

trait TweetETLService {

  def etl(accountName: String, sessionTimeStr: String): Future[Unit]

}
