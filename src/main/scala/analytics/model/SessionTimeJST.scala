package analytics.model
import java.time.format.DateTimeFormatter
import java.time.{LocalDateTime, ZoneId, ZonedDateTime}

import scala.util.Try

case class SessionTimeJST(value: ZonedDateTime)

object SessionTimeJST {

  def apply(str: String): Try[SessionTimeJST] = Try {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val ldt       = LocalDateTime.parse(str, formatter)
    val zdt       = ZonedDateTime.of(ldt, ZoneId.of("Asia/Tokyo"))
    new SessionTimeJST(zdt)
  }

  def now: SessionTimeJST = SessionTimeJST(ZonedDateTime.now(ZoneId.of("Asia/Tokyo")))

}
