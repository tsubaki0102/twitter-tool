name := "twitter-tool"

scalaVersion := "2.12.7"

enablePlugins(FlywayPlugin)

libraryDependencies ++= Seq(
  "com.danielasfregola" %% "twitter4s"           % "5.5",
  "org.scalikejdbc"     %% "scalikejdbc"         % "3.3.0",
  "org.scalikejdbc"     %% "scalikejdbc-config"  % "3.3.0",
  "mysql"               % "mysql-connector-java" % "8.0.12",
  "ch.qos.logback"      % "logback-classic"      % "1.2.3"
)

scalafmtOnCompile in ThisBuild := true
