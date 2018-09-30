package analytics.model
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import scala.util.Try

class SessionTime(value: LocalDateTime)

object SessionTime {

  def apply(str: String): Try[SessionTime] = Try {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val ldt       = LocalDateTime.parse(str, formatter)
    new SessionTime(ldt)
  }

}
