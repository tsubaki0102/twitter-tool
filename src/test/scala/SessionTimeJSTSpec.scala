import analytics.model.SessionTimeJST
import org.specs2.mutable.Specification

class SessionTimeJSTSpec extends Specification {

  "SessionTimeJST" should {
    "yyyy-MM-dd HH:mm:ss形式をパースできる" in {
      val str = "2018-10-01 12:34:56"
      SessionTimeJST(str) must beSuccessfulTry
    }
  }

}
