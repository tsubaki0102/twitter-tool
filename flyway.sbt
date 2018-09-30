import com.typesafe.config.ConfigFactory
val conf = ConfigFactory.parseFile(new File("./src/main/resources/application.conf"))

flywayLocations := Seq("filesystem:./src/main/resources/db/migration/")
flywayUrl := conf.getString("db.production.url")
flywayUser := conf.getString("db.production.username")
flywayPassword := conf.getString("db.production.password")
