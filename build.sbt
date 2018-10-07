name := "twitter-tool"

scalaVersion := "2.12.7"

enablePlugins(FlywayPlugin)

libraryDependencies ++= Seq(
  "com.danielasfregola" %% "twitter4s"           % "5.5",
  "org.scalikejdbc"     %% "scalikejdbc"         % "3.3.0",
  "org.scalikejdbc"     %% "scalikejdbc-config"  % "3.3.0",
  "mysql"               % "mysql-connector-java" % "8.0.12",
  "ch.qos.logback"      % "logback-classic"      % "1.2.3",
  "com.google.inject"   % "guice"                % "4.2.1",
  "org.specs2"          %% "specs2-core"         % "4.3.4" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")

scalafmtOnCompile in ThisBuild := true
