import analytics.model.SessionTime
import org.specs2.mutable.Specification

class SessionTimeSpec extends Specification {

  "SessionTime" should {
    "yyyy-MM-dd HH:mm:ss形式をパースできる" in {
      val str = "2018-10-01 12:34:56"
      SessionTime(str) must beSuccessfulTry
    }
  }
}
