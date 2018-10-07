import com.typesafe.config.ConfigFactory
val conf = ConfigFactory.parseFile(new File("./src/main/resources/application.conf"))

flywayLocations := Seq("filesystem:./src/main/resources/db/migration/")
flywayUrl := conf.getString("db.default.url")
flywayUser := conf.getString("db.default.username")
flywayPassword := conf.getString("db.default.password")
