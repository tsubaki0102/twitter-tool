package analytics.application
import analytics.model.SessionTime

import scala.concurrent.Future

trait TweetETLService {

  def etl(accountName: String, sessionTime: SessionTime): Future[Unit]

}
