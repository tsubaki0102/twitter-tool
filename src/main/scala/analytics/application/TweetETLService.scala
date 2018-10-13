package analytics.application
import analytics.model.SessionTimeJST

import scala.concurrent.Future

trait TweetETLService {

  def etl(accountName: String, sessionTime: SessionTimeJST): Future[Unit]

}
